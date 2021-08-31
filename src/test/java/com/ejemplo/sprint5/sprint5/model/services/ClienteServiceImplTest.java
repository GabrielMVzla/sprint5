package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.IClienteDao;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Map;



class ClienteServiceImplTest {

    @Autowired
    private ClienteServiceImpl service;

    private IClienteDao reposirotyMock = Mockito.mock(IClienteDao.class);

    Cliente cliente = new Cliente();


    @BeforeEach
    void setUp(){
        cliente = new Cliente();
        cliente.setIdCliente((long) 100);
        cliente.setNombres("Adiel");
        cliente.setApellidos("Lara Ledesma");
        cliente.setDireccion("Calle los sacates #409");

        service = new ClienteServiceImpl(reposirotyMock);

        Mockito.when(reposirotyMock.save(cliente)).thenReturn(cliente);

        Mockito.when(reposirotyMock.findById(cliente.getIdCliente())).thenReturn(java.util.Optional.ofNullable(cliente)).thenReturn(null);



    }


    @Test
    void guardarCliente() {
        BindingResult result = new BeanPropertyBindingResult(new Object(), "");


        ResponseEntity<Map<String, Object>> response = service.guardarCliente(cliente, result);


        Assertions.assertEquals("El cliente ha sido creado con éxito!", response.getBody().get("mensaje"));
        Assertions.assertEquals(cliente.toString(), response.getBody().get("cliente").toString());



    }

    @Test
    void eliminarCliente() throws Exception{

        service.eliminarCliente((long) 100);
        Mockito.verify(reposirotyMock).delete(cliente);

    }

    @Test
    void actualizarCliente() {
        BindingResult result = new BeanPropertyBindingResult(new Object(), "");

        ResponseEntity<Map<String, Object>> response = service.actualizarCliente(cliente.getIdCliente(), cliente, result);


        Assertions.assertEquals("El cliente ha sido actualizado con éxito!", response.getBody().get("mensaje"));
        Assertions.assertEquals(cliente.toString(), response.getBody().get("cliente").toString());

    }
}