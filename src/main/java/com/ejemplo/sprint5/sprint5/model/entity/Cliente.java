package com.ejemplo.sprint5.sprint5.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Data
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente_pk")
    private String idCliente;

    @NotBlank
    private String nombres, apellidos, direccion;
}
