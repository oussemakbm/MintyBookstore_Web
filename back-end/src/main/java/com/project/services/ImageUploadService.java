package com.project.services;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadService {
	
	private final String profilePicsUploadDir = "profilePics/";
	private final String bookCoversUploadDir = "bookCovers/";
	
	
	public String saveProfilePic(MultipartFile image) {
		
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		
//		String uploadDirPath = profilePicsUploadDir + LocalDate.now().toString();
		
		
		return "ksljfs";
	}
	

}
