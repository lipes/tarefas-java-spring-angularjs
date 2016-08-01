package com.tarefas.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tarefas.domain.entity.Tarefa;
import com.tarefas.domain.service.StatusTarefa;

@Transactional
public interface TarefaRepository extends CrudRepository<Tarefa, Long> {
	
	public Tarefa findByTarefaDescricao(String descricao);
	public List<Tarefa> findByStatus(StatusTarefa status);
	public Tarefa findByTarefaDescricaoIgnoreCase(String descricao);
	public Tarefa findByTarefaDescricaoAndId(String descricao, Long id);
	public List<Tarefa> findAllByOrderByTarefaDescricaoAsc();
	public List<Tarefa> findByUsuarioId(Long usuarioId);
	public List<Tarefa> findAll();
}
