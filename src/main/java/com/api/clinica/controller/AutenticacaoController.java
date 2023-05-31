package com.api.clinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.clinica.dto.autenticacao.DadosAutenticacao;
import com.api.clinica.dto.security.DadosTokenJWT;
import com.api.clinica.infra.security.TokenService;
import com.api.clinica.model.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController  {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var authenticateToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authenticate = manager.authenticate(authenticateToken);
		
		String tokenJWT = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
}