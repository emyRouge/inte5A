// Controller
package com.edu.mx.inte5A.Marca.Control;

import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaDto>> obtenerTodos() {
        return ResponseEntity.ok(marcaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(marcaService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MarcaDto> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(marcaService.buscarPorNombre(nombre));
    }

    @PutMapping("/cambiar-status/{id}")
    public ResponseEntity<MarcaDto> cambiarStatus(@PathVariable int id) {
        return ResponseEntity.ok(marcaService.cambiarStatus(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDto> actualizar(@PathVariable int id, @RequestBody MarcaDto marcaDto) {
        return ResponseEntity.ok(marcaService.actualizar(id, marcaDto));
    }

    @PostMapping
    public ResponseEntity<MarcaDto> crear(@RequestBody MarcaDto marcaDto) {
        return ResponseEntity.ok(marcaService.crear(marcaDto));
    }
}
