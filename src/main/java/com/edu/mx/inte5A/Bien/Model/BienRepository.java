package com.edu.mx.inte5A.Bien.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long> {
    List<Bien> findByResponsable_Idusuario(Long idUsuario);
    List<Bien> findAllByStatusIsTrue();
}