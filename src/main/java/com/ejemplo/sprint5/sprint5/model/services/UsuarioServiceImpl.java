package com.ejemplo.sprint5.sprint5.model.services;

import java.util.List;
import java.util.stream.Collectors;

import com.ejemplo.sprint5.sprint5.model.dao.IUsuarioDao;
import com.ejemplo.sprint5.sprint5.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional

//Interfaz proveída por Spring para el proceso de autenticación
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService
{
	private IUsuarioDao iUsuarioDao;

	@Autowired
	UsuarioServiceImpl(IUsuarioDao iUsuarioDao){
		this.iUsuarioDao = iUsuarioDao;
	}


	
	@Override
	@Transactional(readOnly=true)//como es una consulta tiene que ser solo de lectura "readOnly"
	public UserDetails loadUserByUsername(String username)
	{		
		Usuario usuario = iUsuarioDao.findByUsername(username);

		if(usuario == null)
		{
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+usuario+"' en el sistema!");
		}

		List<GrantedAuthority> authorities = 
				usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
//				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(username, usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
		
	@Transactional(readOnly=true)//por defecto el crudRepository ya provee el manejo de transacciones de forma automática y por defecto
	public Usuario findByUsername(String username)
	{
		return iUsuarioDao.findByUsername(username);
	}
}
