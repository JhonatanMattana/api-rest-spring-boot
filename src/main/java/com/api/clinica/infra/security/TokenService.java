package com.api.clinica.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.api.clinica.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {
	
	public String gerarToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("123456");
			
			String token = JWT.create()
					.withIssuer("API Clinica auth0")
					.withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao())
					.sign(algorithm);
			
			return token;
		} catch (JWTCreationException ex) {
			throw new RuntimeException("Erro ao gerar token jwt. ", ex);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}