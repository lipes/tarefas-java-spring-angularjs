package com.tarefas.domain.service;

public enum StatusTarefa {
	PENDENTE("Pendente", "label-danger"),
	INICIADA("Iniciada", "label-info"),
	FINALIZADA("Finalizada", "label-success");
	
	private String descricao;
	private String color;
	
	StatusTarefa(String descricao, String color){
		this.descricao = descricao;
		this.color = color;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public String getColor(){
		return color;
	}
}