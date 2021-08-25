package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IClienteService
{
    List<Cliente> getAllClientes();
}
