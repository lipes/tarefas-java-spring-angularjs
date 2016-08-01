package com.tarefas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.domain.entity.Usuario;
import com.tarefas.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Transactional
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario create(Usuario usuario) {
		usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public Usuario findOne(Long codigo) {
		Usuario Usuario = usuarioRepository.findOne(codigo);
		return Usuario;
	}

	@Override
	public List<Usuario> findAllToUser(Long id) {
		List<Usuario> Usuario = usuarioRepository.findById(id);
		return Usuario;
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.delete(id);
	}
}
