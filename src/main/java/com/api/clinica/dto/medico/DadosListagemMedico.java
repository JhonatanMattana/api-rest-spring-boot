package com.api.clinica.dto.medico;

import com.api.clinica.medico.enums.Especialidade;
import com.api.clinica.model.Medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

	public DadosListagemMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}