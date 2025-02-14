// Repository
package com.edu.mx.inte5A.TipoBien.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoBienRepository extends JpaRepository<TipoBien, Long> {
    Optional<TipoBien> findByNombre(String nombre);
    List<TipoBien> findAllByStatusIsTrue ();
}