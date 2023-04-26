package com.api.clinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.clinica.medico.dto.DadosCadastroMedico;
import com.api.clinica.model.Medico;
import com.api.clinica.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		medicoService.save(new Medico(dados));
	}
	
}