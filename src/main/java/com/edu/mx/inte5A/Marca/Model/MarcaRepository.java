// Repository
package com.edu.mx.inte5A.Marca.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNombre(String nombre);
    List<Marca> findAllByStatusIsTrue();

}