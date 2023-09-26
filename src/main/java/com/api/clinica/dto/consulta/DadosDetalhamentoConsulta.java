package com.api.clinica.dto.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
		Long id,
		Long idMedico,
		Long idPaciente,
		LocalDateTime data) {

}