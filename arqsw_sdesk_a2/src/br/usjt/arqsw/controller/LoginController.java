package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

/**
 * 
 * @author roger.oliveira-816117932
 *
 */

@Controller
public class LoginController {

	private UsuarioService usuarioService;
 
	@Autowired
	public LoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/** exibe o formulario de login **/
	@RequestMapping("loginForm")
	public String loginForm() {
		return "Login";
	}

	/** verifica se o usuario esta logado **/
	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Usuario usuario, HttpSession session) throws IOException {
		if (usuarioService.verificarUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "index";
		}
		return "redirect:Login";
	}
}
