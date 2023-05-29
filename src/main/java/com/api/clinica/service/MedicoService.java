package com.api.clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.clinica.dto.medico.DadosAtualizacaoMedico;
import com.api.clinica.model.Medico;
import com.api.clinica.repository.MedicoRepository;

import jakarta.validation.Valid;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public Page<Medico> listarTodos(Pageable pageable) {
		return medicoRepository.findAllByAtivoTrue(pageable);
	}
	
	@Transactional
	public void save(Medico medico) {
		medicoRepository.save(medico);
	}

	@Transactional
	public Medico atualizar(@Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
		var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
		return medico;
	}

	@Transactional
	public void excluir(Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();
	}

	public Medico getById(Long id) {
		return medicoRepository.getReferenceById(id);
	}
	
}