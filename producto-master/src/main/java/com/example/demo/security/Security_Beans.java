package com.example.demo.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Services.UserImpl;
import com.example.demo.Services.UserService;
import com.example.demo.securityJwt.AuthTolken;


@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled =true
		)
public class Security_Beans{
	
	@Autowired
	UserService userimpl;
	
	@Autowired
	private AuthenticationEntryPoint authen;
	
	@Bean
	public AuthTolken authenticationJwtToke() {
		return new AuthTolken();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
		authprovider.setUserDetailsService(userimpl);
		authprovider.setPasswordEncoder(passwordencoder());
	
	return authprovider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authen).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/app/login/**").permitAll()
		.anyRequest().authenticated();
		
		http.authenticationProvider(authenticationProvider());
	 
		http.addFilterBefore(authenticationJwtToke(), UsernamePasswordAuthenticationFilter.class);
	 return http.build();
	
	}
	
}