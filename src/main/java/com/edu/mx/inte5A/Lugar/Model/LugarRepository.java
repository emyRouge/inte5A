package com.edu.mx.inte5A.Lugar.Model;

import com.edu.mx.inte5A.Modelo.Model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {

    Optional<Lugar> findByLugar(String lugar);

}
