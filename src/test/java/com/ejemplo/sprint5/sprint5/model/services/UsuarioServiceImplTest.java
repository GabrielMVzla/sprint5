package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.IClienteDao;
import com.ejemplo.sprint5.sprint5.model.dao.IUsuarioDao;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import com.ejemplo.sprint5.sprint5.model.entity.Role;
import com.ejemplo.sprint5.sprint5.model.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
    void whenGivenUserName_shouldReturnUser() {

        Usuario response = service.findByUsername("Adiel");

        Assertions.assertEquals(usuario.getUsername(), response.getUsername());
        Assertions.assertEquals(usuario.getPassword(), response.getPassword());

    }
}