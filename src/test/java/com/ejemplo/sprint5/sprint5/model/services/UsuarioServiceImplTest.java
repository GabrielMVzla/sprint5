package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.IUsuarioDao;
import com.ejemplo.sprint5.sprint5.model.entity.Role;
import com.ejemplo.sprint5.sprint5.model.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.*;


class UsuarioServiceImplTest {

    @Autowired
    private UsuarioServiceImpl service;

    private IUsuarioDao reposirotyMock = Mockito.mock(IUsuarioDao.class);

    Usuario usuario = new Usuario();


    @BeforeEach
    void setUp(){
        usuario.setIdUsuarioPk((long)100);
        usuario.setUsername("Adiel");
        usuario.setNombres("Adiel");
        usuario.setApellidos("Lara Ledesma");
        usuario.setEmail("adiel.lara@hotmail.com");
        usuario.setEnabled(true);
        usuario.setPassword("pepito");

        List<Role> roles = new ArrayList<>();
        usuario.setRoles(roles);

        service = new UsuarioServiceImpl(reposirotyMock);

        Mockito.when(reposirotyMock.findByUsername("Adiel")).thenReturn(usuario);
    }



    @Test
    void whenGivenUserName_shouldLoadUser() {
        UserDetails response = service.loadUserByUsername("Adiel");


        Assertions.assertEquals(usuario.getUsername(), response.getUsername());
        Assertions.assertEquals(usuario.getPassword(), response.getPassword());

    }

    @Test
    void whenGivenUserName_shouldReturnAError() {

        UsernameNotFoundException error = Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> { service.loadUserByUsername("Pepito"); });


        Assertions.assertEquals("Error en el login: no existe el usuario 'null' en el sistema!", error.getMessage());
    }


    @Test
    void whenGivenUserName_shouldReturnUser() {

        Usuario response = service.findByUsername("Adiel");

        Assertions.assertEquals(usuario.getUsername(), response.getUsername());
        Assertions.assertEquals(usuario.getPassword(), response.getPassword());

    }
}