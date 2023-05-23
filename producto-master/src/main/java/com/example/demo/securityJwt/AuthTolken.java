package com.example.demo.securityJwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Services.UserImpl;
import com.example.demo.Services.UserService;

import org.slf4j.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthTolken extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwutils;
	
	@Autowired
	private UserService userimple;
	
	private static final Logger logger  = LoggerFactory.getLogger(AuthTolken.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			   String jwt = parseJwt(request);
			      if (jwt != null && jwutils.validateJwtToken(jwt)) {
			        String username = jwutils.getUserNameFromJwtToken(jwt);

			        UserDetails userDetails = userimple.loadUserByUsername(username);
			        UsernamePasswordAuthenticationToken authentication =
			            new UsernamePasswordAuthenticationToken(
			                userDetails,
			                null,
			                userDetails.getAuthorities());
			        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			        SecurityContextHolder.getContext().setAuthentication(authentication);
			      }
			    } catch (Exception e) {
			      logger.error("Cannot set user authentication: {}", e);
			    }

			    filterChain.doFilter(request, response);
			  }

			  private String parseJwt(HttpServletRequest request) {
			    String headerAuth = request.getHeader("Authorization");

			    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			      return headerAuth.substring(7, headerAuth.length());
			    }

			    return null;
			  }
}
