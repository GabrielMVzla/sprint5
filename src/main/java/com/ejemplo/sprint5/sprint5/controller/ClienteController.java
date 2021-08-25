package com.ejemplo.sprint5.sprint5.controller;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import com.ejemplo.sprint5.sprint5.model.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api") //aquí generamos la URL

public class ClienteController
{
    @Autowired
    IClienteService clienteService;

    @GetMapping("clientes")
    public List<Cliente> index(){

        return clienteService.getAllClientes();

    }
}
