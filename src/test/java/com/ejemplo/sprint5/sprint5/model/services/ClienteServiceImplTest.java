package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.IClienteDao;
import com.ejemplo.sprint5.sprint5.model.dto.ClienteDto;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@Slf4j
class ClienteServiceImplTest
{
    @Autowired
    private ClienteServiceImpl service;

    private final IClienteDao reposirotyMock = mock(IClienteDao.class);

    private Cliente cliente;

    private ClienteDto clienteDto;

    private List<Cliente> clientes;
    Pageable pageable;

    @BeforeEach
    void setUp()
    {
        cliente = new Cliente();
        //cliente.setIdCliente((long) 100);
        cliente.setNombres("Adiel");
        cliente.setApellidos("Lara Ledesma");
        cliente.setDireccion("Calle los sacates #409");

        clienteDto = new ClienteDto();
        clienteDto = cliente.toClient(clienteDto);

        service = new ClienteServiceImpl(this.reposirotyMock);

        when(this.reposirotyMock.save(cliente)).thenReturn(cliente);

        when(this.reposirotyMock.findById( (long) 100)).thenReturn(java.util.Optional.ofNullable(cliente)).thenReturn(null);

        clientes = new ArrayList<>();
        clientes.add(cliente);
        when(this.reposirotyMock.findAll()).thenReturn(clientes).thenReturn(null);

        pageable = PageRequest.of(0, 2);
        final Page<Cliente> page = new PageImpl<>(clientes);
        when(this.reposirotyMock.findAll(pageable)).thenReturn(page);


    }

    @Test
    void shoudReturnClients() {

        Page<Cliente> response = service.obtenerClientes(pageable);


        assertEquals(clientes, response.getContent());

    }

    @Test
    void shoudReturnClient() {
        Cliente response = service.obtenerCliente((long) 100);

        assertEquals(cliente, response);

    }


    @Test
    void shoudSaveClient()
    {
        BindingResult result = new BeanPropertyBindingResult(new Object(), "");

        ResponseEntity<Map<String, Object>> response = service.guardarCliente(clienteDto, result);

        assertEquals("El cliente ha sido creado con éxito!", Objects.requireNonNull(response.getBody()).get("mensaje"));



        assertEquals( cliente.toString(), response.getBody().get("cliente").toString());


        log.info("idCliente: {}, {}, {}, {}", cliente.getIdCliente(), cliente.getNombres(), cliente.getApellidos(), cliente.getDireccion());
    }

    @Test
    void whenGivenId_shouldDeleteUser_ifFound()
    {
        service.eliminarCliente((long) 100);
        verify(this.reposirotyMock).delete(cliente); //veces que esperamos se invoque
    }

    @Test
    void whenGivenId_shouldUpdateUser_ifFound()
    {
        BindingResult result = new BeanPropertyBindingResult(new Object(), "");

        ResponseEntity<Map<String, Object>> response = service.actualizarCliente((long) 100, clienteDto, result);

        assertEquals("El cliente ha sido actualizado con éxito!", Objects.requireNonNull(response.getBody()).get("mensaje"));
        assertEquals(cliente.toString(), response.getBody().get("cliente").toString());
    }
}