package com.ejemplo.sprint5.sprint5.auth;

import java.util.Arrays;

//import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//sirve para desde el controlador integrarle seguridad, cuando sean publicos no se hace nada
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService usuarioService;
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "authenticationManager")//por defecto se registra con el nombre del método pero también se puede especificar entre los paréntesis
	@Override
	protected AuthenticationManager authenticationManager() throws Exception 
	{
		return super.authenticationManager();
	}
	
	//este método es por el lado de spring
	@Override
	public void configure(HttpSecurity http) throws Exception 
	{
        http.authorizeRequests()//.antMatchers(HttpMethod.GET, "/api/clientes").permitAll() //nuestras rutas publicas que cualquier usuario puede acceder independiente si ha iniciado sesión o no
        	.anyRequest()
        	.authenticated()
        	.and().csrf().disable() //protege nuestro formulario a través de un token, evita ataques, como trabajamos con un frontend separado no necesitamos la seguridad csrf, por lo tanto tenemos que desabilitarlo
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//desabilita manejo de sesión por el lado de Spring Security ya que trabajaremos con token, a diferencia de cuando trabajemos en un proyecto todo incluido por spring front y back, toda la autenticacion de los usuarios es através de sesiones, la información del usuario se almacena en el servidor de manera persistente y activa entre las peticiones que el usuario vaya realizando a nuestra aplicación, pero con tokens no es encesario ya que la información del usuario no se guarda en la sesión si no en el token, por el lado del cliente y no por el lado del servidor
        	.and().cors().configurationSource(corsConfigurationSource()) //permite que un clienteq ue está en un lugar distinto al back-end tenga permisos de acceder a los recursos protegidos del back-end
        	;
    }
	
	//permite que un clienteq ue está en un lugar distinto al back-end tenga permisos de acceder a los recursos protegidos del back-end
	@Bean
	public CorsConfigurationSource corsConfigurationSource() 
	{
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://web/*", "http://frontend/*")); //asterisco para todos los dominios, también aplica para el método de abajo, aunque la idea es ser más especificos
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));//pq en algunos navegadores cuando enviamos una solicitud para autenticarnos y solciitar el token a la ruta oauth token, esa petición se envía como OPTIONS 
		config.setAllowCredentials(true); //permite credenciales
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); //permite headers
		
		//Ahora a registrar estas configuraciones a todas nuestras rutas del backend, importar no reactiva
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter()
	{
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
