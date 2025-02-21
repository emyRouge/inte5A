package com.edu.mx.inte5A.Bien.Control;

import com.edu.mx.inte5A.Bien.Model.BienDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bienes")
public class BienController {

    private final BienService bienService;

    @Autowired
    public BienController(BienService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/{idBien}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idBien) {
        return bienService.buscarPorId(idBien);
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodosLosBienes() {
        return bienService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> crearBienCompleto(@Validated(BienDto.RegistrarBien.class) @RequestBody BienDto bienDTO) {
        return bienService.crearBien(bienDTO);
    }

    @PutMapping("/{idBien}")
    public ResponseEntity<Object> actualizarBien(@PathVariable Long idBien, @Validated(BienDto.ModificarBien.class) @RequestBody BienDto bienDTO) {
        return bienService.actualizarBien(idBien, bienDTO);
    }

    @PatchMapping("/{idBien}/status")
    public ResponseEntity<Object> cambiarStatus ( @Validated (BienDto.CambiarStatus.class) @PathVariable Long idBien) {
        return bienService.cambiarStatus(idBien);
    }

}
