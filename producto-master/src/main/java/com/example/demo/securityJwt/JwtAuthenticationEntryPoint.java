package com.example.demo.securityJwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		logger.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		
		//indica que el cuerpo de la respuesta es de tipo JSON 
	    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	    
	    //indica que se requiere autenticacion para acceder al recursos solicitado 
	    //y qie la solicitud no ha sido autorizada
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	     
	    //MAP CREA un objeto y se lo utiliza para construir una respuesta 
	    //en formato JSON en la cual contiene informacion de error de autenticacion no autorizada
	    final Map<String, Object> body = new HashMap<>();
	    //agrega una entrada al mapa con una clave y un valore asociado
	    //clave -> diferentes aspectos del error de autentificacion
	    //valores -> son los correspondientes valores asociados a cada clave.
	    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);//establece la clave de status en el estado no autorizado
	    body.put("error", "Unauthorized");//establece clave "error" en una cada de texto que describe el error como no autorizado
	    body.put("message", authException.getMessage()); //mensaje de exception
	    body.put("path", request.getServletPath());// establece la clave path en rita de servlet
	    final ObjectMapper mapper = new ObjectMapper();
	    mapper.writeValue(response.getOutputStream(), body);
	}

}
