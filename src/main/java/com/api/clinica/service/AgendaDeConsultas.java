package com.api.clinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.clinica.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import com.api.clinica.dto.consulta.DadosAgendamentoConsulta;
import com.api.clinica.dto.consulta.DadosDetalhamentoConsulta;
import com.api.clinica.infra.exception.ValidacaoException;
import com.api.clinica.model.Consulta;
import com.api.clinica.model.Medico;
import com.api.clinica.repository.ConsultaRepository;
import com.api.clinica.repository.MedicoRepository;
import com.api.clinica.repository.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadores;
	
	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
		if (!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("ID do paciente informado não existe!");
		}
		
		if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("ID do médico informado não existe!");
		}
		
		validadores.forEach(v -> v.validar(dados));
		
		var paciente = pacienteRepository.findById(dados.idPaciente()).get();
		var medico = escolherMedico(dados);
		if (medico == null) {
			throw new ValidacaoException("Não existe médico disponível nessa data!");
		}
		var consulta = new Consulta(null, medico, paciente, dados.data());
		
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if (dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		
		if (dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}
	
}