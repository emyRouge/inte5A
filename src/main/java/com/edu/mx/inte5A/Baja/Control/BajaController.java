// BajaController.java
package com.edu.mx.inte5A.Baja.Control;

import com.edu.mx.inte5A.Baja.Model.Baja;
import com.edu.mx.inte5A.Baja.Model.BajaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bajas")
public class BajaController {

    private final BajaService bajaService;

    public BajaController(BajaService bajaService) {
        this.bajaService = bajaService;
    }

    @PostMapping
    public ResponseEntity<Baja> crearBaja(@RequestBody BajaDto bajaDto) {
        return ResponseEntity.ok(bajaService.crearBaja(bajaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Baja> modificarBaja(@PathVariable Long id, @RequestBody BajaDto bajaDto) {
        return ResponseEntity.ok(bajaService.modificarBaja(id, bajaDto));
    }

    @GetMapping
    public ResponseEntity<List<Baja>> buscarTodas() {
        return ResponseEntity.ok(bajaService.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Baja> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bajaService.buscarPorId(id));
    }
}
