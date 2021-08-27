package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.ClienteDao;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import static com.ejemplo.sprint5.sprint5.utils.Utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteServiceImpl implements IClienteService
{
    @Autowired
    ClienteDao clienteDao;
    Map<String, Object> response;
    StringBuilder stringBuilder;

    @Override
    public List<Cliente> obtenerClientes()
    {
        return clienteDao.findAll();
    }
    public Page<Cliente> obtenerClientes(Pageable pageable)
    {
        return clienteDao.findAll(pageable);
    }

    @Override
    public Cliente obtenerCliente(Long idCliente) {
        return clienteDao.findById(idCliente).orElse(null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> guardarCliente(@Valid Cliente cliente, BindingResult result)
    {
        response = new HashMap<>();
        stringBuilder = new StringBuilder();

        if(erroresBinding(result))
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        try
        {
            cliente = clienteDao.save(cliente);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el insert de 'cliente' en la base de datos!");
            response.put("error", stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()).toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", cliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarCliente(Long idCliente)
    {
        response = new HashMap<>();
        stringBuilder = new StringBuilder();

        Cliente clienteAEliminar = clienteDao.findById(idCliente).orElse(null);

        if(clienteAEliminar == null)
        {
            response.put("mensaje", stringBuilder.append("El cliente con id '").append(idCliente).append("' que quieres eliminar no existe!").toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try
        {
            clienteDao.delete(clienteAEliminar);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar 'cliente' en la base de datos!");
            response.put("error", stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()).toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido eliminado con éxito!");
        response.put("cliente", stringBuilder.append(clienteAEliminar.getNombres()).append(" ").append(clienteAEliminar.getApellidos()).toString());

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarCliente( Long idCliente, Cliente clienteActualizado, BindingResult result) {
        response = new HashMap<>();
        stringBuilder = new StringBuilder();

        Cliente clienteAActualizar = clienteDao.findById(idCliente).orElse(null);

        if(clienteAActualizar == null)
        {
            response.put("mensaje", stringBuilder.append("El cliente con id '").append(idCliente).append("' que quieres actualizar no existe!").toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(erroresBinding(result))
        {
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try
        {
            clienteAActualizar.setNombres(clienteActualizado.getNombres());
            clienteAActualizar.setApellidos(clienteActualizado.getApellidos());
            clienteAActualizar.setDireccion(clienteActualizado.getDireccion());

            clienteActualizado = clienteDao.save(clienteAActualizar);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar actualización de 'cliente' en la base de datos!");
            response.put("error", stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()).toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido actualizado con éxito!");
        response.put("cliente", clienteActualizado);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    public boolean erroresBinding( BindingResult result)
    {
        if(result.hasErrors())
        {
            stringBuilder = new StringBuilder();

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> stringBuilder.append("El campo '").append(err.getField()).append("' ").append(err.getDefaultMessage()).toString())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return true;
        }
        return false;
    }
}
