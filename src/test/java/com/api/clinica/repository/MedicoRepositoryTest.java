package com.api.clinica.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.api.clinica.dto.endereco.DadosEndereco;
import com.api.clinica.dto.medico.DadosCadastroMedico;
import com.api.clinica.dto.paciente.DadosCadastroPaciente;
import com.api.clinica.medico.enums.Especialidade;
import com.api.clinica.model.Consulta;
import com.api.clinica.model.Medico;
import com.api.clinica.model.Paciente;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	@DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario1() {
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		
		var medico = cadastrarMedico("Medico nome", "email@medico", "123465", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente("Paciente nome", "paciente@email", "132546678");
		cadastrarConsulta(medico, paciente, proximaSegundaAs10);
		
		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		assertThat(medicoLivre).isNull();
	}
	
	@Test
	@DisplayName("Deveria devolver Medico quando ele estiver disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario2() {
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		
		var medico = cadastrarMedico("Medico nome", "email@medico", "123465", Especialidade.CARDIOLOGIA);

		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		assertThat(medicoLivre).isEqualTo(medico);
	}
	
	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime date) {
		em.persist(new Consulta(null, medico, paciente, date));
	}
	
	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
		var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
		em.persist(medico);
		return medico;
	}
	
	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
		var paciente = new Paciente(dadosPaciente(nome, email, cpf));
		em.persist(paciente);
		return paciente;
	}
	
	private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
		return new DadosCadastroMedico(nome, email, email, crm, especialidade, dadosEndereco());
	}
	
	private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
		return new DadosCadastroPaciente(nome, email, email, cpf, dadosEndereco());
	}
	
	private DadosEndereco dadosEndereco() {
		return new DadosEndereco("Rua Teste", "Bairro Teste", "0000", "Cidade Teste", "UF", null, null);
	}
}