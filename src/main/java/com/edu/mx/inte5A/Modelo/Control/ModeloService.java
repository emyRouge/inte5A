// Service
package com.edu.mx.inte5A.Modelo.Control;

import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public List<ModeloDto> buscarTodos() {
        return modeloRepository.findAll()
                .stream()
                .map(modelo -> new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto()))
                .collect(Collectors.toList());
    }

    public ModeloDto buscarPorId(Long id) {
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado con ID: " + id));
        return new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());
    }

    public ModeloDto buscarPorNombre(String nombreModelo) {
        Modelo modelo = modeloRepository.findByNombreModelo(nombreModelo)
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado con nombre: " + nombreModelo));
        return new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());
    }

    public ModeloDto cambiarStatus(Long id) {
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado con ID: " + id));
        modelo.setStatus(!modelo.isStatus());
        modeloRepository.save(modelo);
        return new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());
    }

    public ModeloDto actualizar(Long id, ModeloDto modeloDto) {
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado con ID: " + id));
        modelo.setNombreModelo(modeloDto.getNombreModelo());
        modelo.setFoto(modeloDto.getFoto());
        modeloRepository.save(modelo);
        return new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());
    }

    public ModeloDto crear(ModeloDto modeloDto) {
        Modelo modelo = new Modelo();
        modelo.setNombreModelo(modeloDto.getNombreModelo());
        modelo.setFoto(modeloDto.getFoto()); // Foto es opcional
        modelo.setStatus(true); // Por defecto true
        modelo = modeloRepository.save(modelo);
        return new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());
    }
}