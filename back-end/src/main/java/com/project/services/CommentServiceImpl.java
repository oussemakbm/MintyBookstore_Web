package com.project.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.Comment;
import com.project.entities.User;
import com.project.repos.BookRepo;
import com.project.repos.CommentRepo;
import com.project.security.UserUtilities;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;



@Service
public class CommentServiceImpl implements commentService {
	
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	BookRepo bookRepo;
	@Autowired
	UserUtilities userUtil;
	private static final String POST_COMMENTPROFANITY_URL = "https://neutrinoapi-bad-word-filter.p.rapidapi.com/bad-word-filter";
	
	public boolean addComment(String body, long bookId) throws UnsupportedOperationException, IOException, ParseException {
		Book b = bookRepo.findById(bookId).get();
		User u = userUtil.getCurrentAuthenticatedUser();
		
//		body = body.replace(" ", "+");


		
//		Before adding the comment we need to process it because bad words aren't allowed
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(POST_COMMENTPROFANITY_URL);
		
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("censor-character", "*"));
	    params.add(new BasicNameValuePair("content", body));
	    
	    httpPost.setEntity(new UrlEncodedFormEntity(params));
	    httpPost.addHeader("x-rapidapi-key", "8ab149114dmsh80111528716ca1cp1857e9jsn2dd75a72da34");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
        String res =null;
		if (entity != null) {

		  InputStream instream = entity.getContent();

		  byte[] bytes = IOUtils.toByteArray(instream);

		  res = new String(bytes, "UTF-8");

		  instream.close();

		}

		System.out.println("Response: ");
		JSONParser parser = new JSONParser(); 
		JSONObject json = (JSONObject) parser.parse(res);  
		System.out.println(json.toString());
		System.out.println();
		System.out.println(json.get("is-bad"));
		
		Comment c = null;
		
		if((boolean)json.get("is-bad")) {
			String censoredBody = (String)json.get("censored-content");
			 c = new Comment(censoredBody,b,u);
		} else {
			 c = new Comment(body,b,u);
		}
		
		
		Long savedCommentId = commentRepo.save(c).getId();
		return commentRepo.findById(savedCommentId).isPresent();
	}
	
	public boolean removeComment(long bookId) {
		commentRepo.deleteById(bookId);
		return !commentRepo.findById(bookId).isPresent();
	}
	
	public List<Comment> getBookComments(long bookId) {
		return commentRepo.getCommentsByBookId(bookId);
	}
	
	public boolean updateComment(long commentId, String body) {
		Comment newComment = commentRepo.findById(commentId).get();
		newComment.setBody(body);
		Long savedCommentId = commentRepo.save(newComment).getId();
		return commentRepo.findById(commentId).get().getBody().equals(body);
	}
}
