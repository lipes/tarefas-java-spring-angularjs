package com.tarefas.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tarefas.domain.entity.Usuario;
import com.tarefas.domain.service.UsuarioService;

@Controller
public class UsuarioResource {
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(path="/usuarios/lista", method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Usuario> list(){
		Iterable<Usuario> usuarios = usuarioService.findAll();
		return usuarios;
	}
	
	@RequestMapping(path="/usuarios", method=RequestMethod.GET)
	public String listaTarefas(){
		return "usuarios";
	}
	
	@RequestMapping(path="/usuarios", method=RequestMethod.POST)
	public String create(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes){
		if (errors.hasErrors() || usuario.getUsuarioNome().equals("")) {
			attributes.addFlashAttribute("mensagem", "Ocorreu um erro ao inserir o usuario!");
			attributes.addFlashAttribute("erro", true);
			return "redirect:/usuarios/novo";
		}
		usuarioService.create(usuario);
		attributes.addFlashAttribute("mensagem", "Usuario cadastrado com Sucesso!");
		return "redirect:/usuarios";
	}
	
	//Novo Cadastro
	@RequestMapping(path="/usuarios/novo", method=RequestMethod.GET)
	public String novo(Map<String, Object> model){
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "CadastroUsuarios";
	}
	
	//Editar
	@RequestMapping(path="/usuarios/{codigo}", method=RequestMethod.GET)
	public String editar(Map<String, Object> model, @PathVariable Long codigo){
		Usuario usuario = usuarioService.findOne(codigo);
		model.put("usuario", usuario);
		return "CadastroUsuarios";
	}
}
