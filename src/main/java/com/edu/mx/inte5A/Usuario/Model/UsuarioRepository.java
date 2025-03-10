package com.edu.mx.inte5A.Usuario.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByStatusIsTrue();
    Optional<Usuario> findByUsuario(String usuario);
}