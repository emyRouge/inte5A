// BajaController.java
package com.edu.mx.inte5A.Baja.Control;

import com.edu.mx.inte5A.Baja.Model.BajaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{idBaja}")
    public ResponseEntity<Object> modificarBaja(@PathVariable Long idBaja, @Validated (BajaDto.ModificarBaja.class) @RequestBody BajaDto bajaDto) {
        return bajaService.modificarBaja(idBaja, bajaDto);
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodas() {
        return bajaService.buscarTodasLasBajas();
    }

    @GetMapping("/{idBaja}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long idBaja) {
        return bajaService.buscarPorId(idBaja);
    }

    @GetMapping("/{idBaja}/responsable")
    public ResponseEntity<Object> buscarPorUsuario(@PathVariable Long idBaja) {
        return bajaService.buscarPorResponsable(idBaja);
    }

}
