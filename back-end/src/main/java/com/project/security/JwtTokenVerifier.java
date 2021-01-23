package com.project.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifier extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
			)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
//		If there is no token (null) or it's empty ("") or it doesn't start with Bearer 
//		Then there is an error my friend XDD
		if ( Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer")) {
			
			filterChain.doFilter(request, response);
			return;
		}
		
//		Getting the token
//		So all we do is to replace the prefix which is Bearer with an empty string		
		String token = authorizationHeader.replace("Bearer", "");
		
		
		System.out.println("Token: "+ token);
		
		try {
			
			String secretKey = "Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3Mna3na3";
			
			Jws<Claims> claimsJws = Jwts.parser()
				.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.parseClaimsJws(token);
			
			Claims body = claimsJws.getBody();
			
			String username = body.getSubject();
			
			List<Map<String,String>> authorities = (List<Map<String,String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
					.map(m -> new SimpleGrantedAuthority(m.get("authority")))
					.collect(Collectors.toSet());
					
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					username,
					null,
					simpleGrantedAuthorities
					); 
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println("IsAuthenticated: "+ authentication.isAuthenticated() + " Authorities: " + authentication.getAuthorities() );
			
		} catch (JwtException e) {
			throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
		}
		
//		After the filter complete it's logic we must pass the request and response to the next Filter using the filterChain
		
		filterChain.doFilter(request, response);
		
	}
		
	
	
}
