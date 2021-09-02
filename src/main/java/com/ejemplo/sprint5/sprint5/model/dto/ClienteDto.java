package com.ejemplo.sprint5.sprint5.model.dto;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteDto
{

    @NotBlank(message = "no debe estar vacío")
    private String nombres;
    @NotBlank(message = "no debe estar vacío")
    private String apellidos;

    @NotBlank(message = "no debe estar vacío")
    private String direccion;


    public Cliente toClient(){
        Cliente cliente = new Cliente();
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        return cliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

}
