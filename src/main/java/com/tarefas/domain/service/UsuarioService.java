package com.tarefas.domain.service;

import java.util.List;

import com.tarefas.domain.entity.Usuario;

public interface UsuarioService {
	List<Usuario> findAll();
	Usuario create(Usuario usuario);
	Usuario findOne(Long codigo);
	List<Usuario> findAllToUser(Long pkUsuario);
	void delete(Long id);
}
