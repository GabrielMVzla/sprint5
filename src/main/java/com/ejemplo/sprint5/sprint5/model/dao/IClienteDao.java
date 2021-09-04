package com.ejemplo.sprint5.sprint5.model.dao;

import com.ejemplo.sprint5.sprint5.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteDao extends JpaRepository<Cliente, Long>
{
    //Incluye métodos de guardado, consulta, borrar, actualizar, etc.

    //Personalizados
    Cliente findByUsername(String username);

    Cliente findByUsernameAndEmail(String username, String email);

    @Query("select u from Cliente c where c.username = ?1")
    Cliente findByUsernameConQuery(String username);
}


