package com.api.clinica.consulta.validacoes;

import com.api.clinica.dto.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
	
	void validar(DadosAgendamentoConsulta dados);
	
}