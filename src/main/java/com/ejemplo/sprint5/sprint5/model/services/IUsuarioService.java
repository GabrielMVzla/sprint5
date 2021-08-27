package com.ejemplo.sprint5.sprint5.model.services;


import com.ejemplo.sprint5.sprint5.model.entity.Usuario;

public interface IUsuarioService
{
	Usuario findByUsername(String username);
}
