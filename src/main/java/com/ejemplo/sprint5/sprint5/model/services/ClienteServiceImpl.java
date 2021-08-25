package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.ClienteDao;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import static com.ejemplo.sprint5.sprint5.utils.Utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public ResponseEntity<Map<String, Object>> guardarCliente(Cliente cliente, BindingResult result)
    {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors())
        {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> stringBuilder.append("El campo \"").append(err.getField()).append("\" ").append(err.getDefaultMessage()).toString())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try
        {
            cliente = clienteDao.save(cliente);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el insert de \"cliente\" en la base de datos!");
            response.put("error", stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", cliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

}
