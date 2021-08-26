package com.ejemplo.sprint5.sprint5.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter 
{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	InfoAdicionalToken infoAdicionalToken;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
	{	
		security.tokenKeyAccess("permitAll()") //todo usuario puede autenticarse en el endpoint oauth/token, es nuestra ruta de login para iniciar sesión en nuestro autorizationService
		.checkTokenAccess("isAuthenticated()"); //cada que queremos acceder a una pagina protegida debemos enviar nuestro token, y solo los clientes autenticados pueden acceder a esta ruta
		//solo tenemos 2 endpoints o rutas en nuestro authorizationService, están protegidos por authentication basic utilizando las credenciales del cliente es decir de la app, se envia en cliente id con su secret
	}

	//Sirve para el cliente acceda a nuestra apirest, en este caso es nuestro frontend con angular, en caso de tener varias aplicaciones que consumen nuestro servicio rest tenemos que registrar 1 x 1
	//a estos clientes con su clientID y su código secreto o contraseña
	//La idea de oauth es proporcionar mayor seguridad, no solo nos autenticamos con los usuarios de nuestro backend sino también con las credenciales de la app que se va a conectar, 1 el cliente por el lado de la aplicación
	//y otro por el usuario que iniciará sesión
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception 
	{
		clients.inMemory()
				.withClient("vueapp")
				.secret(passwordEncoder.encode("12345"))
				.scopes("read", "write") //para permitir al usuario leer y escribir
				.authorizedGrantTypes("password", "refresh_token") //utilizamos password cuando es con credenciales, cuando nuestros users existen en nuestro sistema de backend, para autenticar requiere username y passwd
												  //además tenemos que enviar las credenciales del usuario que iniciará sesión en el backend y en nuestra app cliente
												  //también está otro tipo, el authorizationCode no es para autenticar un usuario con sus credenciales si no que es a través de un codigo que nos entrega el backend, es un intercambio entre el código de autorización por un token de acceso via redireccionamineto via de una url
												  //el otro es más básico donde solo enviamos username y contraseña del usuario, es más para aplicaciones publicas que no requieran mucha seguridad
				.accessTokenValiditySeconds(3600)
				.refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception 
	{
		//unimos los 2 objetos para que se añadan al token que mostrará en headers
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.tokenEnhancer(tokenEnhancerChain)
		
		;
	}
	
	@Bean
	public JwtTokenStore tokenStore() { return new JwtTokenStore(accessTokenConverter()); }

	@Bean
	public JwtAccessTokenConverter accessTokenConverter()
	{
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

		//Llave secreta tipo RSA - aquí quien firma es la llave PRIVADA
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		//quien verifica es la Llava RSA PUBLICA
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);

		return jwtAccessTokenConverter;
	}
	
	
}
