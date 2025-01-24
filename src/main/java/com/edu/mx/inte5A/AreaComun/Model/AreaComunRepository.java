package com.edu.mx.inte5A.AreaComun.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaComunRepository extends JpaRepository<AreaComun, Long> {
}
