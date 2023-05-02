package com.api.clinica.medico.dto;

import com.api.clinica.medico.enums.Especialidade;
import com.api.clinica.model.Endereco;
import com.api.clinica.model.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
	}
	
}