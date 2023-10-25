package com.api.clinica.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.clinica.dto.consulta.DadosAgendamentoConsulta;
import com.api.clinica.infra.exception.ValidacaoException;
import com.api.clinica.repository.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		
		if (!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído.");
		}
	}
	
}