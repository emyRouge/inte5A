package com.edu.mx.inte5A.Lugar.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
    Optional<Lugar> findByLugar(String lugar);
    @Query("SELECT b FROM Bien b WHERE b.lugar.idlugar = :idlugar")
    List<Bien> findBienesByLugarId(@Param("idlugar") int idlugar);
    List<Lugar> findAllByStatusIsTrue();

}
