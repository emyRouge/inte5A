package com.edu.mx.inte5A.Lugar.Control;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Control.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lugares")
public class LugarController {

    private final LugarService lugarService;

    @Autowired
    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @GetMapping("/{idLugar}/bienes")
    public ResponseEntity<Object> obtenerBienesPorLugar(@PathVariable Long idLugar) {
        return lugarService.buscarBienesPorLugar(idLugar);
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodosLosLugares() {
        return lugarService.buscarTodosLosLugares();
    }

    @GetMapping("/{idLugar}")
    public ResponseEntity<Object> buscarLugaresPorId(@PathVariable Long idLugar) {
        return lugarService.buscarLugaresPorId(idLugar);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> buscarLugaresPorNombre(@PathVariable String nombre) {
        return lugarService.buscarLugaresPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Object> crearLugar(@Validated(LugarDto.RegistrarLugar.class) @RequestBody LugarDto lugarDto) {
        return lugarService.crearLugar(lugarDto);
    }

    @PutMapping("/{idLugar}")
    public ResponseEntity<Object> actualizarLugar(@PathVariable Long idLugar, @Validated(LugarDto.ModificarLugar.class) @RequestBody LugarDto lugarDto) {
        return lugarService.actualizarLugar(idLugar, lugarDto);
    }

    @PatchMapping("/{idLugar}/status")
    public ResponseEntity<Object> cambiarStatusLugar(@Validated (LugarDto.CambiarStatus.class) @PathVariable Long idLugar) {
        return lugarService.cambiarStatusLugar(idLugar);
    }

}