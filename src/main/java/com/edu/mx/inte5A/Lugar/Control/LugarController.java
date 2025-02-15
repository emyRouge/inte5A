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

    @GetMapping("/{id}/bienes")
    public ResponseEntity<Object> obtenerBienesPorLugar(@PathVariable Long idLugar) {
        return lugarService.buscarBienesPorLugar(idLugar);
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodos() {
        return lugarService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idLugar) {
        return lugarService.buscarPorId(idLugar);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> obtenerPorNombre(@PathVariable String nombre) {
        return lugarService.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Object> crearLugar(@Validated(LugarDto.RegistrarLugar.class) @RequestBody LugarDto lugarDto) {
        return lugarService.crearLugar(lugarDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarLugar(@PathVariable Long idLugar, @Validated(LugarDto.ModificarLugar.class) @RequestBody LugarDto lugarDto) {
        return lugarService.Actualizar(idLugar, lugarDto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> cambiarStatus(@PathVariable Long idLugar) {
        return lugarService.CambiarStatus(idLugar);
    }

}