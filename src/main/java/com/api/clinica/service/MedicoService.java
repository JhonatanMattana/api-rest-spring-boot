package com.api.clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.clinica.medico.dto.DadosAtualizacaoMedico;
import com.api.clinica.model.Medico;
import com.api.clinica.repository.MedicoRepository;

import jakarta.validation.Valid;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Transactional
	public void save(Medico medico) {
		medicoRepository.save(medico);
	}

	public Page<Medico> listarTodos(Pageable pageable) {
		return medicoRepository.findAll(pageable);
	}

	@Transactional
	public void atualizar(@Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
		var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
	}

	@Transactional
	public void excluir(Long id) {
		medicoRepository.deleteById(id);
	}
	
}