package com.ejemplo.sprint5.sprint5.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Data
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idCliente;

    private String nombres, apellidos;

    @Email(message = "problemas con el correo")
    private String email;
}
