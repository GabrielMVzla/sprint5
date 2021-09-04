package com.ejemplo.sprint5.sprint5.model.dao;


import com.ejemplo.sprint5.sprint5.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>
{
	Usuario findByUsername(String username);
}
