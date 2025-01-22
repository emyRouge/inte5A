// Repository
package com.edu.mx.inte5A.Modelo.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    Optional<Modelo> findByNombreModelo(String nombreModelo);
}