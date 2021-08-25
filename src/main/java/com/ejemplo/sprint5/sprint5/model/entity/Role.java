package com.ejemplo.sprint5.sprint5.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "roles")
public class Role implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role_pk")
	private Long idRolePk;
	
	@NotBlank
	@Column( unique = true, length = 20)
	private String nombre;
	
//	de esta manera tenemos mapeado también al usuario en la relación ManytoMany, pero en realidad no es necesaria aquí
//	@ManyToMany(mappedBy = "roles")
//	private List<Usuario> usuarios;
}
