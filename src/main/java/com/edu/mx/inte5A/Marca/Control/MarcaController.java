// Controller
package com.edu.mx.inte5A.Marca.Control;

import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodos() {
        return marcaService.buscarTodos();
    }

    @GetMapping("/{idMarca}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idMarca) {
        return marcaService.buscarPorId(idMarca);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> obtenerPorNombre(@PathVariable String nombre) {
        return marcaService.buscarPorNombre(nombre);
    }

    @PutMapping("/cambiar-status/{idMarca}")
    public ResponseEntity<Object> cambiarStatus(@Validated(MarcaDto.CambiarStatus.class) @PathVariable Long idMarca) {
        return marcaService.cambiarStatus(idMarca);
    }

    @PutMapping("/{idMarca}")
    public ResponseEntity<Object> actualizar(@PathVariable Long idMarca, @Validated(MarcaDto.ModificarMarca.class) @RequestBody MarcaDto marcaDto) {
        return marcaService.Actualizar(idMarca, marcaDto);
    }

    @PostMapping
    public ResponseEntity<Object> crear(@Validated(MarcaDto.RegistrarMarca.class) @RequestBody MarcaDto marcaDto) {
        return marcaService.crear(marcaDto);
    }

}