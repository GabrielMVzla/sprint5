package com.ejemplo.sprint5.sprint5.model.services;

import com.ejemplo.sprint5.sprint5.model.dao.IClienteDao;
import com.ejemplo.sprint5.sprint5.model.dto.ClienteDto;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import com.ejemplo.sprint5.sprint5.utils.UtilString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClienteServiceImpl implements IClienteService
{
    IClienteDao iClienteDao;

    ClienteServiceImpl(IClienteDao iClienteDao){
        this.iClienteDao = iClienteDao;
    }


    Map<String, Object> response;
    StringBuilder stringBuilder;


    Cliente cliente;



    @Override
    public Page<Cliente> obtenerClientes(Pageable pageable)
    {
        return iClienteDao.findAll(pageable);
    }

    @Override
    public Cliente obtenerCliente(Long idCliente) {
        return iClienteDao.findById(idCliente).orElse(null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> guardarCliente(@Valid ClienteDto clienteDto, BindingResult result)
    {
        response = new HashMap<>();
        stringBuilder = new StringBuilder();

        cliente = new Cliente();
        cliente = clienteDto.toClient();


        if(erroresBinding(result))
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        try
        {
            cliente = iClienteDao.save(cliente);


        }
        catch (DataAccessException e)
        {
            response.put(UtilString.MENSAJE, UtilString.CLIENTE_ERROR_CREADO);
            response.put(UtilString.ERROR, stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()).toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(UtilString.MENSAJE, UtilString.CLIENTE_CREADO);
        response.put(UtilString.CLIENTE, cliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarCliente(Long idCliente)
    {
        response = new HashMap<>();
        stringBuilder = new StringBuilder();

        Cliente clienteAEliminar = iClienteDao.findById(idCliente).orElse(null);

        if(clienteAEliminar == null)
        {
            response.put(UtilString.MENSAJE, stringBuilder.append("El cliente con id '").append(idCliente).append("' que quieres eliminar no existe!").toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try
        {
            iClienteDao.delete(clienteAEliminar);
        }
        catch (DataAccessException e)
        {
            response.put(UtilString.MENSAJE, UtilString.CLIENTE_ERORR_ELIMINAR);
            response.put(UtilString.ERROR, stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()).toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(UtilString.MENSAJE, UtilString.CLIENTE_ELIMINAR);
        response.put(UtilString.CLIENTE, stringBuilder.append(clienteAEliminar.getNombres()).append(" ").append(clienteAEliminar.getApellidos()).toString());

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarCliente( Long idCliente, ClienteDto clienteActualizado, BindingResult result) {
        response = new HashMap<>();
        stringBuilder = new StringBuilder();

        Cliente clienteAActualizar = iClienteDao.findById(idCliente).orElse(null);

        if(clienteAActualizar == null)
        {
            response.put(UtilString.MENSAJE, stringBuilder.append("El cliente con id '").append(idCliente).append("' que quieres actualizar no existe!").toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(erroresBinding(result))
        {
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try
        {

            cliente = clienteActualizado.toClient();

            clienteAActualizar.setNombres(clienteActualizado.getNombres());
            clienteAActualizar.setApellidos(clienteActualizado.getApellidos());
            clienteAActualizar.setDireccion(clienteActualizado.getDireccion());

            cliente = iClienteDao.save(clienteAActualizar);
        }
        catch (DataAccessException e)
        {
            response.put(UtilString.MENSAJE, UtilString.CLIENTE_ERORR_ACTUALIZAR);
            response.put(UtilString.ERROR, stringBuilder.append(e.getMessage()).append(": ").append(e.getMostSpecificCause().getMessage()).toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(UtilString.MENSAJE, UtilString.CLIENTE__ACTUALIZAR);
        response.put(UtilString.CLIENTE, cliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    public boolean erroresBinding( BindingResult result)
    {
        if(result.hasErrors())
        {
            stringBuilder = new StringBuilder();

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> {
                        stringBuilder = new StringBuilder();
                        return stringBuilder.append("El campo '").append(err.getField()).append("' ").append(err.getDefaultMessage()).toString();
                    })
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return true;
        }
        return false;
    }
}
