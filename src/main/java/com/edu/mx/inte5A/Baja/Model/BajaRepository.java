package com.edu.mx.inte5A.Baja.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BajaRepository extends JpaRepository<Baja, Long> {
    List<Baja> findAllByStatusIsTrue();
}
