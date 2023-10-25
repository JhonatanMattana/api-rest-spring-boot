package com.api.clinica.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.clinica.dto.consulta.DadosAgendamentoConsulta;
import com.api.clinica.infra.exception.ValidacaoException;
import com.api.clinica.repository.ConsultaRepository;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoAndData(dados.idMedico(), dados.data());
		
		if (medicoPossuiOutraConsultaNoMesmoHorario) {
			throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário.");
		}
	}
	
}