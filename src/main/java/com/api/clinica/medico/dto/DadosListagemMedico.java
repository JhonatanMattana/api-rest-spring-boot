package com.api.clinica.medico.dto;

import com.api.clinica.medico.enums.Especialidade;
import com.api.clinica.model.Medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

	public DadosListagemMedico(Medico medico) {
		this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}