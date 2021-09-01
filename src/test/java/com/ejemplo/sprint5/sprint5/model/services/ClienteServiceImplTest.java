package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.IClienteDao;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@Slf4j
class ClienteServiceImplTest
{
    @Autowired
    private ClienteServiceImpl service;

    private IClienteDao reposirotyMock = mock(IClienteDao.class);

    private Cliente cliente;

    @BeforeEach
    void setUp()
    {
        cliente = new Cliente();
        cliente.setIdCliente((long) 100);
        cliente.setNombres("Adiel");
        cliente.setApellidos("Lara Ledesma");
        cliente.setDireccion("Calle los sacates #409");

        service = new ClienteServiceImpl(this.reposirotyMock);

        when(this.reposirotyMock.save(cliente)).thenReturn(cliente);

        when(this.reposirotyMock.findById(cliente.getIdCliente())).thenReturn(java.util.Optional.ofNullable(cliente)).thenReturn(null);
    }

    @Test
    void shoudSaveClient()
    {
        BindingResult result = new BeanPropertyBindingResult(new Object(), "");

        ResponseEntity<Map<String, Object>> response = service.guardarCliente(cliente, result);

        assertEquals("El cliente ha sido creado con éxito!", response.getBody().get("mensaje"));
        assertEquals( cliente.toString(), response.getBody().get("cliente").toString());

        log.info("idCliente: {}, {}, {}, {}", cliente.getIdCliente(), cliente.getNombres(), cliente.getApellidos(), cliente.getDireccion());
    }

    @Test
    void whenGivenId_shouldDeleteUser_ifFound() throws Exception
    {
        service.eliminarCliente((long) 100);
        verify(this.reposirotyMock).delete(cliente); //veces que esperamos se invoque
    }

    @Test
    void whenGivenId_shouldUpdateUser_ifFound()
    {
        BindingResult result = new BeanPropertyBindingResult(new Object(), "");

        ResponseEntity<Map<String, Object>> response = service.actualizarCliente(cliente.getIdCliente(), cliente, result);

        assertEquals("El cliente ha sido actualizado con éxito!", response.getBody().get("mensaje"));
        assertEquals(cliente.toString(), response.getBody().get("cliente").toString());
    }
}