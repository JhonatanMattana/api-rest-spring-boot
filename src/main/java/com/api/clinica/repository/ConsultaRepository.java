package com.api.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.clinica.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}