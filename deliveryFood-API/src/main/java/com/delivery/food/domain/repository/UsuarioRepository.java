package com.reneantunes.reneFood.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.reneantunes.reneFood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	
}
