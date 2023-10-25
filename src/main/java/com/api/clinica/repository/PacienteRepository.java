package com.api.clinica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.clinica.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
    		SELECT p.ativo FROM Paciente p
    		WHERE p.id = :idPaciente
    		""")    
	Boolean findAtivoById(Long idPaciente);
}