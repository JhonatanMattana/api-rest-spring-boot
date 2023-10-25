package com.api.clinica.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.clinica.dto.consulta.DadosAgendamentoConsulta;
import com.api.clinica.infra.exception.ValidacaoException;
import com.api.clinica.repository.MedicoRepository;

public class ValidadorMedicoAtivo {

	@Autowired
	private MedicoRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		// escolha di médico opcional
		if (dados.idMedico() == null) {
			return;
		}
		
		var medicoEstaAtivo = repository.findAtivoById(dados.idMedico()); 
		if (!medicoEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com médico excluído.");
		}
	}
	
}