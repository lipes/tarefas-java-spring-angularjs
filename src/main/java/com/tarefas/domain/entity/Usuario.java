package com.tarefas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	public Usuario(){}
	public Usuario(String _usuarioNome){this.usuarioNome = _usuarioNome;}
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String usuarioNome;

	public String getUsuarioNome() {
		return usuarioNome;
	}
	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long _id) {
		this.id = _id;
	}
	
}
