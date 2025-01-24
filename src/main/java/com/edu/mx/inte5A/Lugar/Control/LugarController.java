package com.edu.mx.inte5A.Lugar.Control;

import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Control.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares")
public class LugarController {

    @Autowired
    private LugarService lugarService;

    @GetMapping
    public ResponseEntity<List<LugarDto>> obtenerTodos() {
        return ResponseEntity.ok(lugarService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LugarDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lugarService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<LugarDto> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(lugarService.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<LugarDto> crearLugar(@RequestBody LugarDto lugarDto) {
        return ResponseEntity.ok(lugarService.crearLugar(lugarDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LugarDto> actualizarLugar(@PathVariable Long id, @RequestBody LugarDto lugarDto) {
        return ResponseEntity.ok(lugarService.Actualizar(id, lugarDto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LugarDto> cambiarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(lugarService.CambiarStatus(id));
    }
}
