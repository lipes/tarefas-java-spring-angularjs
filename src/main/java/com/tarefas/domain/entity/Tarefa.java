package com.tarefas.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.tarefas.domain.service.StatusTarefa;

@Entity
@Table(name="tarefa")
public class Tarefa{
	
	public Tarefa(){}
	
	public Tarefa(String descricao, Usuario usuario){
		this.tarefaDescricao = descricao;
		this.usuario = usuario;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	@Column
	@NotNull(message = "Descrição é obrigatória")
	private String tarefaDescricao;
	
	@NotNull(message = "Data de inicio é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicioTarefa;

	@NotNull(message = "Data fim prevista é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataFimTarefa;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	private StatusTarefa status;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long _id) {
		this.id = _id;
	}

	public String getTarefaDescricao() {
		return tarefaDescricao;
	}

	public void setTarefaDescricao(String tarefaDescricao) {
		this.tarefaDescricao = tarefaDescricao;
	}
	
	public void setDataInicioTarefa(Date dataInicioTarefa) {
		this.dataInicioTarefa = dataInicioTarefa;
	}

	public Date getDataFimTarefa() {
		return dataFimTarefa;
	}
	
	public Date getDataInicioTarefa() {
		return dataInicioTarefa;
	}

	public void setDataFimTarefa(Date dataFimTarefa) {
		this.dataFimTarefa = dataFimTarefa;
	}
	
	public String getColor() {
		return status.getColor();
	}
	
	public boolean isPendente(){
		return StatusTarefa.PENDENTE.equals(this.status);
	}
	public boolean isFinalizada(){
		return StatusTarefa.FINALIZADA.equals(this.status);
	}
	public boolean isIniciada(){
		return StatusTarefa.INICIADA.equals(this.status);
	}
}
