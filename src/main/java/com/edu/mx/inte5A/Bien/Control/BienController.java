package com.edu.mx.inte5A.Bien.Control;

import com.edu.mx.inte5A.Bien.Model.BienDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bienes")
public class BienController {

    private final BienService bienService;

    @Autowired
    public BienController(BienService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idBien) {
        return bienService.obtenerId(idBien);

    }

    //Este esta mal luego lo corrijo
    @GetMapping("/responsable/{idResponsable}")
    public ResponseEntity<Object> obtenerUsuario(@PathVariable Long idUsuario) {
        return bienService.obtenerId(idUsuario);
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodosLosBienes() {
        return bienService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated(BienDto.RegistrarBien.class) @RequestBody BienDto bienDTO) {
        return bienService.crear(bienDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long idBien, @Validated (BienDto.ModificarBien.class) @RequestBody BienDto bienDTO) {
        return bienService.Actualizar(idBien, bienDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> cambiarStatus (@PathVariable Long idBien, @Validated (BienDto.CambiarStatus.class) @RequestParam boolean status) {
        return bienService.cambiarStatus(idBien, status);
    }

}
