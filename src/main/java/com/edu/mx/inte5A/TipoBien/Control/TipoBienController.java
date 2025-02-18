// Controller
package com.edu.mx.inte5A.TipoBien.Control;

import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-bien")
public class TipoBienController {

    private final TipoBienService tipoBienService;

    @Autowired
    public TipoBienController(TipoBienService tipoBienService) {
        this.tipoBienService = tipoBienService;
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodos() {
        return tipoBienService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idTipo) {
        return tipoBienService.buscarPorId(idTipo);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> obtenerPorNombre(@PathVariable String nombre) {
        return tipoBienService.buscarPorNombre(nombre);
    }

    @PutMapping("/cambiar-status/{id}")
    public ResponseEntity<Object> cambiarStatus(@PathVariable Long idTipo) {
        return tipoBienService.CambiarStatus(idTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long idTipo, @Validated (TipoBienDto.CambiarStatus.class) @RequestBody TipoBienDto tipoBienDto) {
        return tipoBienService.Actualizar(idTipo, tipoBienDto);
    }

    @PostMapping
    public ResponseEntity<Object> crear(@Validated (TipoBienDto.RegistrarTipoBien.class) @RequestBody TipoBienDto tipoBienDto) {
        return tipoBienService.crearTipo(tipoBienDto);
    }

}
