// Controller
package com.edu.mx.inte5A.Modelo.Control;

import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public ResponseEntity<List<ModeloDto>> obtenerTodos() {
        return ResponseEntity.ok(modeloService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombreModelo}")
    public ResponseEntity<ModeloDto> obtenerPorNombre(@PathVariable String nombreModelo) {
        return ResponseEntity.ok(modeloService.buscarPorNombre(nombreModelo));
    }

    @PutMapping("/cambiar-status/{id}")
    public ResponseEntity<ModeloDto> cambiarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.cambiarStatus(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDto> actualizar(@PathVariable Long id, @RequestBody ModeloDto modeloDto) {
        return ResponseEntity.ok(modeloService.actualizar(id, modeloDto));
    }

    @PostMapping
    public ResponseEntity<ModeloDto> crear(@RequestBody ModeloDto modeloDto) {
        return ResponseEntity.ok(modeloService.crear(modeloDto));
    }
}
