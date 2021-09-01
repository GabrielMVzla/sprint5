package com.ejemplo.sprint5.sprint5.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class ClienteDto {

    @NotBlank(message = "no debe estar vacío")
    private String nombres, apellidos;

    @NotBlank(message = "no debe estar vacío")
    @Pattern(regexp = "[\\w ]{1,10}", message = "debe tener una longitud máxima de 10, solo permite letras")
    private String direccion;



}
