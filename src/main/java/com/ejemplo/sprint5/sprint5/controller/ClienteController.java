package com.ejemplo.sprint5.sprint5.controller;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import com.ejemplo.sprint5.sprint5.model.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") //aquí generamos la URL

public class ClienteController
{
    @Autowired
    IClienteService clienteService;

    @GetMapping("clientes")
    public List<Cliente> obtenerClientes(){        return clienteService.getAllClientes();    }

    @PostMapping("clientes")
    public ResponseEntity<Map<String,Object>> guardarCliente(@RequestBody Cliente cliente, BindingResult result)
    {
        return clienteService.guardarCliente(cliente, result);
    }
}
