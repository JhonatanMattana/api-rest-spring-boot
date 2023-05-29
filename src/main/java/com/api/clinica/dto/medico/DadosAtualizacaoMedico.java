package com.api.clinica.dto.medico;

import com.api.clinica.dto.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone, DadosEndereco dadosEndereco) {

}