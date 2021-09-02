package com.ejemplo.sprint5.sprint5.controller;

import com.ejemplo.sprint5.sprint5.model.dto.ClienteDto;
import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import com.ejemplo.sprint5.sprint5.model.services.IClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ClienteController
{
    @Autowired
    IClienteService clienteService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("cliente/{idCliente}")
    public Cliente obtenerCliente(@PathVariable Long idCliente){        return clienteService.obtenerCliente(idCliente);    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("clientes")
    public List<Cliente> obtenerClientes(){     return clienteService.obtenerClientes();    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("clientes/page/{page}")
    public Page<Cliente> obtenerClientes(@PathVariable Integer page)
    {
        page = page < 0 ? 0 : page;
        Pageable pageable = PageRequest.of(page, 10);
        return clienteService.obtenerClientes(pageable);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("cliente")
    public ResponseEntity<Map<String,Object>> guardarCliente(@Valid @RequestBody ClienteDto cliente, BindingResult result)
    {
        return clienteService.guardarCliente(cliente, result);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("cliente/{idCliente}")
    public ResponseEntity<Map<String,Object>> eliminarCliente(@Valid @PathVariable Long idCliente)
    {
        return clienteService.eliminarCliente(idCliente);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("cliente/{idCliente}")
    public ResponseEntity<Map<String,Object>> actualizarCliente(@Valid  @RequestBody ClienteDto cliente, BindingResult result, @PathVariable Long idCliente)
    {
        return clienteService.actualizarCliente(idCliente, cliente, result);
    }
}
