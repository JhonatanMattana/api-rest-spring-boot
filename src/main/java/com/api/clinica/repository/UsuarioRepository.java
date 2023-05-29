package com.api.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.clinica.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}