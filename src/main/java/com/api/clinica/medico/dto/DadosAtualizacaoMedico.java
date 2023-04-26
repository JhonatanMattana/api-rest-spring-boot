package com.api.clinica.medico.dto;

import com.api.clinica.endereco.dto.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone, DadosEndereco dadosEndereco) {

}