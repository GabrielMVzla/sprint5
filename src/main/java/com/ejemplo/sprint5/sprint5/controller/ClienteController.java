package com.ejemplo.sprint5.sprint5.controller;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import com.ejemplo.sprint5.sprint5.model.services.IClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api") //aquí generamos la URL

public class ClienteController
{
    @Autowired
    IClienteService clienteService;

    @GetMapping("clientes/{idCliente}")
    public Cliente obtenerClientes(@PathVariable Long idCliente){        return clienteService.obtenerCliente(idCliente);    }

    @GetMapping("clientes")
    public List<Cliente> obtenerClientes(){        return clienteService.obtenerClientes();    }

    @GetMapping("clientes/page/{page}")
    public Page<Cliente> obtenerClientes(@PathVariable Integer page)
    {
        Pageable pageable = PageRequest.of(page, 5);
        return clienteService.obtenerClientes(pageable);
    }

    @PostMapping("clientes")
    public ResponseEntity<Map<String,Object>> guardarCliente(@Valid @RequestBody Cliente cliente, BindingResult result)
    {
        return clienteService.guardarCliente(cliente, result);
    }

    @DeleteMapping("cliente/{idCliente}")
    public ResponseEntity<Map<String,Object>> eliminarCliente(@Valid @PathVariable Long idCliente)
    {
        return clienteService.eliminarCliente(idCliente);
    }

    @PutMapping("cliente/{idCliente}")
    public ResponseEntity<Map<String,Object>> actualizarCliente(@Valid  @RequestBody Cliente cliente, BindingResult result, @PathVariable Long idCliente)
    {
        return clienteService.actualizarCliente(idCliente, cliente, result);
    }
}
