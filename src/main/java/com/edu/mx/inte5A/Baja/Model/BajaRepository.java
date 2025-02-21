package com.edu.mx.inte5A.Baja.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BajaRepository extends JpaRepository<Baja, Long> {

    @Query("SELECT b FROM Baja b JOIN FETCH b.usuario WHERE b.idBaja = :idBaja")
    Optional<Baja> findByIdWithUsuario(@Param("idBaja") Long idBaja);

}
