// Controller
package com.edu.mx.inte5A.TipoBien.Control;

import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-bien")
public class TipoBienController {

    @Autowired
    private TipoBienService tipoBienService;

    @GetMapping
    public ResponseEntity<List<TipoBienDto>> obtenerTodos() {
        return ResponseEntity.ok(tipoBienService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoBienDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoBienService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoBienDto> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(tipoBienService.buscarPorNombre(nombre));
    }

    @PutMapping("/cambiar-status/{id}")
    public ResponseEntity<TipoBienDto> cambiarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(tipoBienService.cambiarStatus(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoBienDto> actualizar(@PathVariable Long id, @RequestBody TipoBienDto tipoBienDto) {
        return ResponseEntity.ok(tipoBienService.actualizar(id, tipoBienDto));
    }

    @PostMapping
    public ResponseEntity<TipoBienDto> crear(@RequestBody TipoBienDto tipoBienDto) {
        return ResponseEntity.ok(tipoBienService.crear(tipoBienDto));
    }
}
