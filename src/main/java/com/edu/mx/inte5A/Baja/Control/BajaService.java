// BajaService.java
package com.edu.mx.inte5A.Baja.Control;

import com.edu.mx.inte5A.Baja.Model.Baja;
import com.edu.mx.inte5A.Baja.Model.BajaDto;
import com.edu.mx.inte5A.Baja.Model.BajaRepository;
import com.edu.mx.inte5A.Bien.Model.Bien;

import com.edu.mx.inte5A.Bien.Model.BienRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BajaService {

    private final BajaRepository bajaRepository;
    private final BienRepository bienRepository;

    public BajaService(BajaRepository bajaRepository, BienRepository bienRepository) {
        this.bajaRepository = bajaRepository;
        this.bienRepository = bienRepository;
    }

    public Baja crearBaja(BajaDto bajaDto) {
        Optional<Bien> bienOptional = bienRepository.findById(bajaDto.getIdBien());
        if (bienOptional.isEmpty()) {
            throw new IllegalArgumentException("El bien con ID " + bajaDto.getIdBien() + " no existe.");
        }

        Baja nuevaBaja = new Baja();
        nuevaBaja.setMotivo(bajaDto.getMotivo());
        nuevaBaja.setFecha(new Date()); // Fecha automática
        nuevaBaja.setBien(bienOptional.get());

        return bajaRepository.save(nuevaBaja);
    }

    public Baja modificarBaja(Long id, BajaDto bajaDto) {
        Optional<Baja> bajaOptional = bajaRepository.findById(id);
        if (bajaOptional.isEmpty()) {
            throw new IllegalArgumentException("La baja con ID " + id + " no existe.");
        }

        Baja bajaExistente = bajaOptional.get();
        bajaExistente.setMotivo(bajaDto.getMotivo());
        return bajaRepository.save(bajaExistente);
    }

    public List<Baja> buscarTodas() {
        return bajaRepository.findAll();
    }

    public Baja buscarPorId(Long id) {
        return bajaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La baja con ID " + id + " no existe."));
    }
}