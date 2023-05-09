package br.com.fiap.seguroautomotivo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.seguroautomotivo.models.Credencial;
import br.com.fiap.seguroautomotivo.models.Usuario;
import br.com.fiap.seguroautomotivo.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/api/registrar")
	public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario) {
		
		usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<Object> logar(@RequestBody @Valid Credencial credencial) {
		
		manager.authenticate(credencial.toAuthentication());
		return ResponseEntity.ok().build();
	}
	
}
