package com.ejemplo.sprint5.sprint5.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
	//este método es por el lado de oAuth
	@Override
	public void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/**").permitAll() //nuestras rutas publicas que cualquier usuario puede acceder independiente si ha iniciado sesión o no
//	        	.antMatchers(HttpMethod.POST, "/api/registrar").permitAll()
	        	.anyRequest()
	        	.authenticated()
	        	;
	    }
}