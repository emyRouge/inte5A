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
    public ResponseEntity<Object> buscarTodasLasMarcas() {
        return marcaService.buscarTodasLasMarcas();
    }

    @GetMapping("/{idMarca}")
    public ResponseEntity<Object> buscarMarcasPorId(@PathVariable Long idMarca) {
        return marcaService.buscarMarcasPorId(idMarca);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> buscarMarcasPorNombre(@PathVariable String nombre) {
        return marcaService.buscarMarcasPorNombre(nombre);
    }

    @PutMapping("/cambiar-status/{idMarca}")
    public ResponseEntity<Object> cambiarStatusMarcas(@Validated(MarcaDto.CambiarStatus.class) @PathVariable Long idMarca) {
        return marcaService.cambiarStatusMarcas(idMarca);
    }

    @PutMapping("/{idMarca}")
    public ResponseEntity<Object> actualizarMarcas(@PathVariable Long idMarca, @Validated(MarcaDto.ModificarMarca.class) @RequestBody MarcaDto marcaDto) {
        return marcaService.actualizarMarcas(idMarca, marcaDto);
    }

    @PostMapping
    public ResponseEntity<Object> crearMarca(@Validated(MarcaDto.RegistrarMarca.class) @RequestBody MarcaDto marcaDto) {
        return marcaService.crearMarca(marcaDto);
    }

}