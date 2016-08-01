package com.tarefas.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tarefas.domain.entity.Usuario;

@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsuarioNome(String nome);
	public Usuario findByUsuarioNomeIgnoreCase(String usuarioNome);
	public Usuario findByUsuarioNomeAndId(String usuarioNome, Long id);
	public List<Usuario> findAllByOrderByUsuarioNomeAsc();
	public List<Usuario> findById(Long Id);
	public List<Usuario> findAll();
}
