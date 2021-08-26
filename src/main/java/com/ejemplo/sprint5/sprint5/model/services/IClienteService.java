package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


public interface IClienteService
{
    List<Cliente> obtenerClientes();
    Page<Cliente> obtenerClientes(Pageable pageable);
    Cliente obtenerCliente(Long idCliente);

    ResponseEntity<Map<String, Object>> guardarCliente(@Valid Cliente cliente, BindingResult result);

    ResponseEntity<Map<String, Object>> eliminarCliente(Long idCliente);

    ResponseEntity<Map<String, Object>> actualizarCliente( Long idCliente, Cliente clienteActualizado, BindingResult result);

}
