package com.tarefas.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tarefas.domain.entity.Tarefa;
import com.tarefas.domain.entity.Usuario;
import com.tarefas.domain.service.StatusTarefa;
import com.tarefas.domain.service.TarefaService;
import com.tarefas.domain.service.UsuarioService;

@Controller
public class TarefaResource {
	@Autowired
	private TarefaService tarefaService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path="/tarefas/lista", method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Tarefa> list(){
		Iterable<Tarefa> tarefas = tarefaService.findAll();
		return tarefas;
	}
	
	@RequestMapping(path="/tarefas/{codigo}", method=RequestMethod.GET)
	@ResponseBody
	public Tarefa listaCodigo(@PathVariable Long codigo){
		Tarefa tarefa = tarefaService.findOne(codigo);
		return tarefa;
	}
	
	@RequestMapping(path="/tarefas", method=RequestMethod.GET)
	public String listaTarefas(){
		return "tarefas";
	}
	
	@RequestMapping(value="/tarefas/{codigo}/finalizar", method=RequestMethod.PUT)
	public String finalizar(@PathVariable Long codigo){
		tarefaService.finalizar(codigo);
		return "/tarefas";
	}
	
	@RequestMapping(value="/tarefas/{codigo}/iniciar", method=RequestMethod.PUT)
	public String iniciar(@PathVariable Long codigo){
		tarefaService.iniciar(codigo);
		return "/tarefas";
	}
	
	@RequestMapping(path="/tarefas/user/{codigo}", method=RequestMethod.GET)
	@ResponseBody
	public List<Tarefa> listaCodigoUsuario(@PathVariable Long codigo){
		List<Tarefa> tarefa = tarefaService.findAllToUser(codigo);
		return tarefa;
	}
	
	@RequestMapping(path="/tarefas", method=RequestMethod.POST)
	public String create(@Validated Tarefa tarefa, Errors errors, RedirectAttributes attributes){
		if (errors.hasErrors()) {
			attributes.addFlashAttribute("erro", true);
			attributes.addFlashAttribute("mensagem", "Ocorreu um erro ao inserir a tarefa!");
			return "redirect:/tarefas/novo";
		}
		
		if(tarefa.getDataFimTarefa().before(tarefa.getDataInicioTarefa())){
			attributes.addFlashAttribute("erro", true);
			attributes.addFlashAttribute("mensagem", "Data Inicio é maior que a data fim da tarefa");
			return "redirect:/tarefas/novo";
		}
		
		tarefaService.create(tarefa);
		attributes.addFlashAttribute("mensagem", "Tarefa Inserida com Sucesso!");
		return "redirect:/tarefas";
	}
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String index(){
		return "redirect:/tarefas";
	}
	
	@RequestMapping(path="/tarefas/novo", method=RequestMethod.GET)
	public String novo(Map<String, Object> model){
		Tarefa tarefa = new Tarefa();
		model.put("tarefa", tarefa);
		return "CadastroTarefas";
	}
	
	@RequestMapping(path="/tarefas/totais", method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> totais(){
		HashMap<String, Object> retorno = new HashMap<>();
		retorno.put("totalTarefas", ((List<Tarefa>) tarefaService.findAll()).size());
		retorno.put("totalUsuarios", usuarioService.findAll().size());
		retorno.put("tarefasEmAtraso", tarefaService.emAtraso());
		retorno.put("tarefasEntregues", tarefaService.findByStatus(StatusTarefa.FINALIZADA).size());
		
		return retorno;
	}
	
	@RequestMapping(value="/tarefas/{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		tarefaService.delete(id);
		
		attributes.addFlashAttribute("mensagem", "Tarefa Excluida com Sucesso!");
		return "redirect:/tarefas";
	}
	
	@ModelAttribute("todosUsuarios")
	public List<Usuario> todosUsuarios(){
		return usuarioService.findAll();
	}
	
	@ModelAttribute("todasStatusTarefa")
	public List<StatusTarefa> todosStatusTarefa(){
		return Arrays.asList(StatusTarefa.values());
	}
	
	
}
