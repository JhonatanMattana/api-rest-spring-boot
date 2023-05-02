package com.api.clinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.clinica.medico.dto.DadosAtualizacaoMedico;
import com.api.clinica.medico.dto.DadosCadastroMedico;
import com.api.clinica.medico.dto.DadosDetalhamentoMedico;
import com.api.clinica.medico.dto.DadosListagemMedico;
import com.api.clinica.model.Medico;
import com.api.clinica.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		
		medicoService.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
		var page = medicoService.listarTodos(pageable).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
		Medico medico = medicoService.atualizar(dadosAtualizacaoMedico);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable("id") Long id) {
		medicoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}