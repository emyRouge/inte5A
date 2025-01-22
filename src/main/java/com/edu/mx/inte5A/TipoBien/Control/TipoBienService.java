// Service
package com.edu.mx.inte5A.TipoBien.Control;

import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoBienService {

    @Autowired
    private TipoBienRepository tipoBienRepository;

    public List<TipoBienDto> buscarTodos() {
        return tipoBienRepository.findAll()
                .stream()
                .map(tipo -> new TipoBienDto(tipo.getIdTipo(), tipo.getNombre(), tipo.isStatus()))
                .collect(Collectors.toList());
    }

    public TipoBienDto buscarPorId(Long id) {
        TipoBien tipoBien = tipoBienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado con ID: " + id));
        return new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
    }

    public TipoBienDto buscarPorNombre(String nombre) {
        TipoBien tipoBien = tipoBienRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado con nombre: " + nombre));
        return new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
    }

    public TipoBienDto cambiarStatus(Long id) {
        TipoBien tipoBien = tipoBienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado con ID: " + id));
        tipoBien.setStatus(!tipoBien.isStatus());
        tipoBienRepository.save(tipoBien);
        return new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
    }

    public TipoBienDto actualizar(Long id, TipoBienDto tipoBienDto) {
        TipoBien tipoBien = tipoBienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado con ID: " + id));
        tipoBien.setNombre(tipoBienDto.getNombre());
        tipoBienRepository.save(tipoBien);
        return new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
    }

    public TipoBienDto crear(TipoBienDto tipoBienDto) {
        TipoBien tipoBien = new TipoBien();
        tipoBien.setNombre(tipoBienDto.getNombre());
        tipoBien.setStatus(true); // Por defecto true
        tipoBien = tipoBienRepository.save(tipoBien);
        return new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
    }
}
