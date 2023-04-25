package com.api.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.clinica.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
