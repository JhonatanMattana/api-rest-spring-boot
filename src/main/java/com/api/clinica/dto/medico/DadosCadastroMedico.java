package com.api.clinica.dto.medico;

import com.api.clinica.dto.endereco.DadosEndereco;
import com.api.clinica.medico.enums.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
		@NotBlank 
		String nome, 
		@Email
		@NotBlank 
		String email, 
		@NotBlank
		String telefone,
		@NotBlank 
		@Pattern(regexp = "\\d{4,6}") 
		String crm, 
		@NotNull 
		Especialidade especialidade, 
		@Valid
		@NotNull
		DadosEndereco endereco) {

}