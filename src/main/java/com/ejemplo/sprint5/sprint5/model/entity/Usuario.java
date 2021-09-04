package com.ejemplo.sprint5.sprint5.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "usuarios")
public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_pk")
	private Long idUsuarioPk;
	
	@Column(unique = true, length = 20)
	@NotBlank
	private String username;
	
	@Column(length = 100)
	@NotBlank
	private String password;
	
	private Boolean enabled;

	private String nombres;

	private String apellidos;
	
	@Email(message = "problemas con el correo")
	@NotBlank
	@Column(unique = true)
	private String email;

	
	//solo estamos haciendo esta relación manytoMany aquí en user, porque no tiene sentido añadirla en Role, aunque sí se puede
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//cacade ALL, cada vez que se elimine al usuario también eliminará sus roles o cada vez que agreguemos un user, también guardará esos roles
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario_fk"),
	inverseJoinColumns = @JoinColumn(name = "id_role_fk"),
	uniqueConstraints = {@UniqueConstraint(	columnNames = {"id_usuario_fk","id_role_fk"}	)	}	) 
	private List<Role> roles;
}
