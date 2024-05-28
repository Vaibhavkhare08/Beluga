package com.beluga.belugaproject.Security;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.qos.logback.core.joran.conditional.ElseAction;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = (Logger) LoggerFactory.getLogger(OncePerRequestFilter.class);
	@Autowired
	private JwtHelper jwtHelper;

	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Authorization = Bearer kjfbadjqfaei;
		String requestHeader = request.getHeader("Authorization");
		logger.info("Header : {}", requestHeader);	
		String username = null;
		String token = null;
		
		if(requestHeader!=null && requestHeader.startsWith("bearer"));
		 token = requestHeader.substring(7);
		 try {
			 
			 username = this.jwtHelper.getUsernameFromToken(token);
			
		} catch ( IllegalArgumentException e) {
			logger.info("Illegal argument while fetching the username !!");
			e.printStackTrace();
		} catch (ExpiredJwtException e) {
			logger.info("Given jwt token is expired !!");
			e.printStackTrace();
		} catch ( MalformedJwtException e) {
			logger.info("Some changes has done in token !! Invalid Token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		if (username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
			
			UserDetails usernDetails = this.userDetailsService.loadUserByUsername(username);
			Boolean validateToken = this.jwtHelper.validateToken(token, usernDetails);
			if (validateToken) {
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usernDetails, validateToken);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				logger.info("Validation fails");
			}
		}
	}

}
