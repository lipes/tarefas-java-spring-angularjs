package com.tarefas.domain.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.domain.entity.Tarefa;
import com.tarefas.domain.repository.TarefaRepository;

@Service
public class TarefaServiceImpl implements TarefaService{
	@Autowired
	TarefaRepository tarefaRepository;
	
	@Transactional
	public List<Tarefa> findAll(){
		return tarefaRepository.findAll();
	}

	@Override
	public Tarefa create(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
		return tarefa;
	}

	@Override
	public Tarefa findOne(Long codigo) {
		Tarefa tarefa = tarefaRepository.findOne(codigo);
		return tarefa;
	}

	@Override
	public List<Tarefa> findAllToUser(Long id) {
		List<Tarefa> tarefa = tarefaRepository.findByUsuarioId(id);
		return tarefa;
	}

	@Override
	public void delete(Long id) {
		tarefaRepository.delete(id);
	}

	@Override
	public Long emAtraso() {
		List<Tarefa> tarefas = tarefaRepository.findAll();
		Date dataAtual = new Date();
		Long totalAtraso = 0L;
		for (Tarefa t : tarefas) {
			if(!t.getStatus().equals(StatusTarefa.FINALIZADA) && t.getDataFimTarefa().before(dataAtual)){
				totalAtraso += 1L;
			}
		}
		return totalAtraso;
	}

	@Override
	public List<Tarefa> findByStatus(StatusTarefa status) {
		List<Tarefa> tarefas = tarefaRepository.findByStatus(status);
		return tarefas;
	}

	@Override
	public void finalizar(Long codigo) {
		Tarefa tarefa = tarefaRepository.findOne(codigo);
		tarefa.setStatus(StatusTarefa.FINALIZADA);
		tarefaRepository.save(tarefa);
	}
	
	@Override
	public void iniciar(Long codigo) {
		Tarefa tarefa = tarefaRepository.findOne(codigo);
		tarefa.setStatus(StatusTarefa.INICIADA);
		tarefaRepository.save(tarefa);
	}
}
