package com.leonardodossantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leonardodossantos.model.Contato;
import com.leonardodossantos.repository.ContatoRepository;
import com.leonardodossantos.service.ContatoService;

@Controller
public class ContatoController {

	@Autowired
	ContatoRepository contatoRepository;

	@Autowired
	ContatoService contatoService;

	@GetMapping("/inserirContato")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView();
		Contato obj = new Contato();
		mv.addObject("contato", obj);
		mv.setViewName("Contato/inserirContato");
		return mv;
	}

	@PostMapping("/inserirContato")
	public ModelAndView inserirContato(Contato contato, Long id) {
		ModelAndView mv = new ModelAndView();

		String out = contatoService.cadastrarContato(contato, id);

		if (out != null) {
			mv.addObject("contato", new Contato());
			mv.addObject("msg", out);
			mv.setViewName("Contato/inserirContato");
		} else {
			mv.addObject("contatos", contatoRepository.findAllOrderedById());
			mv.setViewName("redirect:/listarContatos");
		}
		return mv;
	}

	@GetMapping("/listarContatos")
	public ModelAndView listarContatos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("contatos", contatoRepository.findAllOrderedById());
		mv.setViewName("Contato/listarContatos");
		return mv;
	}

	@GetMapping("/listarExcluirContato")
	public ModelAndView listarExcluirContatos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("contatos", contatoRepository.findAllOrderedById());
		mv.setViewName("Contato/listarExcluir");
		return mv;
	}

	@GetMapping("/excluirContato/{id}")
	public String excluirContato(@PathVariable("id") Long id) {
		// Verifique se o contato existe antes de tentar excluí-lo
		if (contatoRepository.existsById(id)) {
			contatoRepository.deleteById(id);
		}
		return "redirect:/listarExcluirContato"; // Corrigindo o nome da URL aqui também
	}

	@GetMapping("/listarAlterarContatos")
	public ModelAndView listarAlterarContatos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Contato/listarAlterar");
		mv.addObject("contatos", contatoRepository.findAllOrderedById());
		return mv;
	}

	@GetMapping("alterarContato/{id}")
	public ModelAndView alterarProfessor(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Contato contato = contatoRepository.findById(id).get();
		mv.addObject("contato", contato);
		mv.setViewName("Contato/alterar");
		return mv;
	}

	@PostMapping("alterarContato")
	public ModelAndView alterarContato(Contato contato) {
		ModelAndView mv = new ModelAndView();
		String out = contatoService.alterarContato(contato, contato.getId());
		if (out != null) {// nao consegui atualizar o contato, email ja é de outro contato
			mv.addObject("msg", out);
			mv.addObject("Contato", contato);
			mv.setViewName("Contato/alterar");
		} else {// conseguir atualizar o professor
			mv.addObject("contatos", contatoRepository.findAllOrderedById());
			mv.setViewName("Contato/listarAlterar");
		} // fim else
		return mv;
	}// fim metodo

}
