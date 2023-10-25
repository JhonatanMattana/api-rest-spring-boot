package com.api.clinica.dto.consulta;

import java.time.LocalDateTime;

import com.api.clinica.medico.enums.Especialidade;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
		Long idMedico, 
		@NotNull 
		Long idPaciente, 
		@NotNull @Future
		LocalDateTime data,
		Especialidade especialidade) {

}