package com.api.clinica.medico.dto;

import com.api.clinica.endereco.dto.DadosEndereco;
import com.api.clinica.medico.enums.Especialidade;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {

}
