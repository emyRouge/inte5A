// Controller
package com.edu.mx.inte5A.Modelo.Control;

import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    private final ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodos() {
        return modeloService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idModelo) {
        return modeloService.buscarPorId(idModelo);
    }

    @GetMapping("/nombre/{nombreModelo}")
    public ResponseEntity<Object> obtenerPorNombre(@PathVariable String nombreModelo) {
        return modeloService.buscarPorNombre(nombreModelo);
    }

    @PutMapping("/cambiar-status/{id}")
    public ResponseEntity<Object> cambiarStatus(@Validated (ModeloDto.CambiarStatus.class) @PathVariable Long idModelo) {
        return modeloService.cambiarStatus(idModelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long idModelo, @Validated(ModeloDto.ModificarModelo.class) @RequestBody ModeloDto modeloDto) {
        return modeloService.Actualizar(idModelo, modeloDto);
    }

    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody ModeloDto modeloDto) {
        return modeloService.crear(modeloDto);
    }

}
