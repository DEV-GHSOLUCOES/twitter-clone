package br.com.twitterclone.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.twitterclone.domain.mensagem.Mensagem;
import br.com.twitterclone.domain.mensagem.MensagemRepository;
import br.com.twitterclone.domain.usuario.Usuario;
import br.com.twitterclone.domain.usuario.UsuarioRepository;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(path = "/nova")
	public String nova(Model model) {
		Mensagem mensagem = new Mensagem();
		model.addAttribute("mensagem", mensagem);
		
		List<Usuario> usuarios = usuarioRepository.findAll(new Sort(Direction.ASC, "nome"));
		model.addAttribute("usuarios", usuarios);
		
		return "mensagem-criar";
	}
	
	@PostMapping(path = "/criar")
	public String criar(Mensagem mensagem) {
		if (!mensagem.getTexto().isEmpty()) {
			mensagem.setDataHora(LocalDateTime.now());
			mensagemRepository.save(mensagem);
		}
		
		return "redirect:/mensagem/listar";
	}
	
	@GetMapping(path = "/listar")
	public String listar(Model model) {
		List<Mensagem> mensagens = mensagemRepository.findAll(new Sort(Direction.DESC, "dataHora"));
		model.addAttribute("mensagens", mensagens);
		
		return "mensagem-listar";
	}
	
	@GetMapping(path = "/curtir")
	public String curtir(
			@RequestParam("msgId") Integer msgId) {
		
		Mensagem mensagem = mensagemRepository.findById(msgId).orElseThrow();
		mensagem.curtir();
		mensagemRepository.save(mensagem);
		return "redirect:/mensagem/listar";
	}
}