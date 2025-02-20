package com.edu.mx.inte5A.AreaComun.Control;

import com.edu.mx.inte5A.AreaComun.Model.AreaComunDto;
import com.edu.mx.inte5A.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/areas-comunes")
public class AreaComunController {

    private final AreaComunService areaComunService;

    @Autowired
    public AreaComunController(AreaComunService areaComunService) {
        this.areaComunService = areaComunService;
    }

    @PostMapping
    public ResponseEntity<Object> crearAreaComun(@Validated(AreaComunDto.RegistrarArea.class) @RequestBody AreaComunDto areaComunDto) {
        return areaComunService.guardarArea(areaComunDto);
    }

    @PatchMapping("/{idArea}/status")
    public ResponseEntity<Object> cambiarStatus(@Validated(AreaComunDto.CambiarStatus.class) @PathVariable Long idArea) {
        return areaComunService.cambiarStatus(idArea);
    }

    @PutMapping("/{idArea}/lugar/{idLugar}")
    public ResponseEntity<Message> asignarLugar(@PathVariable Long idArea, @PathVariable Long idLugar) {
        return areaComunService.asignarLugar(idArea, idLugar);
    }

    @PutMapping({"/{idArea}"})
    public ResponseEntity<Object> actualizarAreaComun(@PathVariable Long idArea, @Validated (AreaComunDto.ModificarArea.class)@RequestBody AreaComunDto areaComunDto) {
        return areaComunService.modificarArea(idArea, areaComunDto);
    }

}