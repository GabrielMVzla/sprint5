package com.ejemplo.sprint5.sprint5.model.dao;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long>
{

}
