package com.tarefas.domain.service;

import java.util.List;

import com.tarefas.domain.entity.Tarefa;

public interface TarefaService {
	List<Tarefa> findAll();
	List<Tarefa> findByStatus(StatusTarefa status);
	Tarefa create(Tarefa tarefa);
	Tarefa findOne(Long codigo);
	List<Tarefa> findAllToUser(Long pkUsuario);
	Long emAtraso();
	void delete(Long id);
	void finalizar(Long codigo);
	void iniciar(Long codigo);
}
