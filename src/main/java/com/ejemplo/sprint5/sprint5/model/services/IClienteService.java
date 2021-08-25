package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;


public interface IClienteService
{
    List<Cliente> getAllClientes();

    ResponseEntity<Map<String, Object>> guardarCliente(Cliente cliente, BindingResult result);
}
