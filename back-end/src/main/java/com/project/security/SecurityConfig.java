package com.project.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.project.services.UserServiceImpl;

import jwt.JwtUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	private final UserServiceImpl applicationUserService;
	
	@Autowired
	public SecurityConfig(PasswordEncoder passwordEncoder, UserServiceImpl applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
		
			
	}
	
	
	public JwtUsernamePasswordAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
		final JwtUsernamePasswordAuthenticationFilter filter = new JwtUsernamePasswordAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/api/login");
		return filter;
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		For the antMatchers lezem temchi mel the most restricted to the least restricted
		
		http	
				.csrf()
				.disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/api/admin/**").hasRole("ADMIN")
				.antMatchers("/api/client/**").hasAnyRole("CLIENT","ADMIN")
				.antMatchers("/api/**").permitAll()
				.anyRequest()
				.authenticated();
		
		http.addFilterBefore(new JwtUsernamePasswordAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(new JwtTokenVerifier(), JwtUsernamePasswordAuthenticationFilter.class);		
	}
	


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	
	@Bean DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		
		return provider;
	}
	
	
	
}
