// BajaController.java
package com.edu.mx.inte5A.Baja.Control;

import com.edu.mx.inte5A.Baja.Model.Baja;
import com.edu.mx.inte5A.Baja.Model.BajaDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bajas")
public class BajaController {

    private final BajaService bajaService;

    @Autowired
    public BajaController(BajaService bajaService) {
        this.bajaService = bajaService;
    }

    @PostMapping
    public ResponseEntity<Object> crearBaja(@Validated (BajaDto.RegistrarBaja.class) @RequestBody BajaDto bajaDto) {
        return bajaService.crearBaja(bajaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modificarBaja(@PathVariable Long idBaja, @Validated (BajaDto.ModificarBaja.class) @RequestBody BajaDto bajaDto) {
        return bajaService.modificarBaja(idBaja, bajaDto);
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodas() {
        return bajaService.buscarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long idBaja) {
        return bajaService.buscarPorId(idBaja);
    }


}
