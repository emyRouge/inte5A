// Controller
package com.edu.mx.inte5A.Modelo.Control;

import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    private final ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodosLosModelos() {
        return modeloService.buscarTodosLosModelos();
    }

    @GetMapping("/{idModelo}")
    public ResponseEntity<Object> buscarModelosPorId(@PathVariable Long idModelo) {
        return modeloService.buscarModelosPorId(idModelo);
    }

    @GetMapping("/nombre/{nombreModelo}")
    public ResponseEntity<Object> buscarModelosPorNombre(@PathVariable String nombreModelo) {
        return modeloService.buscarModelosPorNombre(nombreModelo);
    }

    @PutMapping("/cambiar-status/{idModelo}")
    public ResponseEntity<Object> cambiarStatusModelo(@Validated (ModeloDto.CambiarStatus.class) @PathVariable Long idModelo) {
        return modeloService.cambiarStatusModelo(idModelo);
    }

    @PutMapping("/{idModelo}")
    public ResponseEntity<Object> actualizarModelo(@PathVariable Long idModelo, @Validated(ModeloDto.ModificarModelo.class) @RequestBody ModeloDto modeloDto) {
        return modeloService.actualizarModelo(idModelo, modeloDto);
    }

    @PostMapping
    public ResponseEntity<Object> crearModelo(@RequestBody ModeloDto modeloDto) {
        return modeloService.crearModelo(modeloDto);
    }

}
