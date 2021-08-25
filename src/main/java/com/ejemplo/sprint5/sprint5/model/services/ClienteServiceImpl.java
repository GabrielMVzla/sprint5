package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.ClienteDao;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService
{
    @Autowired
    ClienteDao clienteDao;

    @Override
    public List<Cliente> getAllClientes()
    {
        return clienteDao.findAll();
    }
}
