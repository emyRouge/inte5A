package com.edu.mx.inte5A.Rol.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByNombre(String nombre);

}
