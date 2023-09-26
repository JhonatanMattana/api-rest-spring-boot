package com.api.clinica.dto.paciente;

import com.api.clinica.dto.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
		@NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}