package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dto.ClienteDto;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Map;


public interface IClienteService
{
    Page<Cliente> obtenerClientes(Pageable pageable);

    Cliente obtenerCliente(Long idCliente);

    ResponseEntity<Map<String, Object>> guardarCliente(@Valid ClienteDto cliente, BindingResult result);

    ResponseEntity<Map<String, Object>> eliminarCliente(Long idCliente);

    ResponseEntity<Map<String, Object>> actualizarCliente( Long idCliente, ClienteDto clienteActualizado, BindingResult result);
}
