// Controller
package com.edu.mx.inte5A.TipoBien.Control;

import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-bien")
public class TipoBienController {

    private final TipoBienService tipoBienService;

    @Autowired
    public TipoBienController(TipoBienService tipoBienService) {
        this.tipoBienService = tipoBienService;
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodosTiposBines() {
        return tipoBienService.buscarTodosTiposBines();
    }

    @GetMapping("/{idTipo}")
    public ResponseEntity<Object> buscarTipoBienPorId(@PathVariable Long idTipo) {
        return tipoBienService.buscarTipoBienPorId(idTipo);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> buscarTipoBienPorNombre(@PathVariable String nombre) {
        return tipoBienService.buscarTipoBienPorNombre(nombre);
    }

    @PutMapping("/cambiar-status/{idTipo}")
    public ResponseEntity<Object> cambiarStatusTipoBien(@PathVariable Long idTipo) {
        return tipoBienService.cambiarStatusTipoBien(idTipo);
    }

    @PutMapping("/{idTipo}")
    public ResponseEntity<Object> actualizarTipoBien(@PathVariable Long idTipo,
                                             @Validated (TipoBienDto.ModificarTipoBien.class)
                                             @RequestBody TipoBienDto tipoBienDto) {
        return tipoBienService.actualizarTipoBien(idTipo, tipoBienDto);
    }

    @PostMapping
    public ResponseEntity<Object> crearTipoBien(@Validated (TipoBienDto.RegistrarTipoBien.class) @RequestBody TipoBienDto tipoBienDto) {
        return tipoBienService.crearTipoBien(tipoBienDto);
    }

}
