package com.api.clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.clinica.model.Medico;
import com.api.clinica.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void save(Medico medico) {
		medicoRepository.save(medico);
	}
	
}