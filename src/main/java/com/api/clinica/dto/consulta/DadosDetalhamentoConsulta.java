package com.api.clinica.dto.consulta;

import java.time.LocalDateTime;

import com.api.clinica.model.Consulta;

public record DadosDetalhamentoConsulta(
		Long id,
		Long idMedico,
		Long idPaciente,
		LocalDateTime data) {

	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
	}

}