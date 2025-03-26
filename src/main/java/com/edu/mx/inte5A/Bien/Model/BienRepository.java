package com.edu.mx.inte5A.Bien.Model;

import com.edu.mx.inte5A.Usuario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long> {
    Optional<Bien> findByCodigoBarras(String codigoBarras);
    long countByLugarIsNotNull();
    List<Bien> findByUsuario(Usuario usuario);
}