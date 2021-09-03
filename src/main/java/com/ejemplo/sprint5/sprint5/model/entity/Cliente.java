package com.ejemplo.sprint5.sprint5.model.entity;

import com.ejemplo.sprint5.sprint5.model.dto.ClienteDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Data
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente_pk")
    private Long idCliente;

    @NotBlank(message = "no debe estar vacío")
    private String nombres, apellidos;

    @NotBlank(message = "no debe estar vacío")
    @Pattern(regexp = "[\\w ]{1,10}", message = "debe tener una longitud máxima de 10, solo permite letras")
    private String direccion;

    public ClienteDto toClient(ClienteDto clienteDto){
        ClienteDto cliente = new ClienteDto();
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
