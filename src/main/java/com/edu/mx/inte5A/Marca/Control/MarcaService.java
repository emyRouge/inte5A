// Service
package com.edu.mx.inte5A.Marca.Control;

import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaDto> buscarTodos() {
        return marcaRepository.findAll()
                .stream()
                .map(marca -> new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus()))
                .collect(Collectors.toList());
    }

    public MarcaDto buscarPorId(int id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
        return new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());
    }

    public MarcaDto buscarPorNombre(String nombre) {
        Marca marca = marcaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con nombre: " + nombre));
        return new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());
    }

    public MarcaDto cambiarStatus(int id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
        marca.setStatus(!marca.isStatus());
        marcaRepository.save(marca);
        return new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());
    }

    public MarcaDto actualizar(int id, MarcaDto marcaDto) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
        marca.setNombre(marcaDto.getNombre());
        marcaRepository.save(marca);
        return new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());
    }

    public MarcaDto crear(MarcaDto marcaDto) {
        Marca marca = new Marca();
        marca.setNombre(marcaDto.getNombre());
        marca.setStatus(true); // Por defecto true
        marca = marcaRepository.save(marca);
        return new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());
    }
}