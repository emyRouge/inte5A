package com.edu.mx.inte5A.AreaComun.Control;

import com.edu.mx.inte5A.AreaComun.Model.AreaComunDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/areas-comunes")
public class AreaComunController {

    @Autowired
    private AreaComunService areaComunService;

    @PostMapping
    public ResponseEntity<AreaComunDto> crearAreaComun(@RequestBody AreaComunDto areaComunDto) {
        return ResponseEntity.ok(areaComunService.crearAreaComun(areaComunDto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AreaComunDto> cambiarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(areaComunService.cambiarStatus(id));
    }

    @PutMapping("/{id}/lugar/{idLugar}")
    public ResponseEntity<AreaComunDto> asignarLugar(@PathVariable Long id, @PathVariable Long idLugar) {
        return ResponseEntity.ok(areaComunService.asignarLugar(id, idLugar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaComunDto> actualizarAreaComun(@PathVariable Long id, @RequestBody AreaComunDto areaComunDto) {
        return ResponseEntity.ok(areaComunService.actualizarAreaComun(id, areaComunDto));
    }
}