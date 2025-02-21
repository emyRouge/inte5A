package com.edu.mx.inte5A.Lugar.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
    Optional<Lugar> findByLugar(String lugar);
    @Query("SELECT b FROM Bien b WHERE b.lugar.idLugar = :idLugar")
    List<Bien> findBienesByLugarId(@Param("idLugar") Long idLugar);
    List<Lugar> findAllByStatusIsTrue();

}
