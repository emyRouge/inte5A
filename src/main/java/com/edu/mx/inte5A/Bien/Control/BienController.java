package com.edu.mx.inte5A.Bien.Control;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import com.edu.mx.inte5A.utils.Message;
import com.edu.mx.inte5A.utils.TypesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController


@RequestMapping("/bienes")
public class BienController {

    private final BienService bienService;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    public BienController(BienService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/porcentaje-ocupacion")
    public ResponseEntity<Object> obtenerPorcentajeBienesOcupados() {
        return bienService.obtenerPorcentajeBienesOcupados();
    }

    @PatchMapping("/{id}/eliminar-lugar")
    public ResponseEntity<Object> eliminarLugarDeBien(@PathVariable Long id) {
        return bienService.eliminarLugarDeBien(id);
    }




    @GetMapping("/{idBien}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long idBien) {
        return bienService.buscarPorId(idBien);
    }

    @GetMapping("/buscar/{codigoBarras}")
    public ResponseEntity<Object> buscarPorCodigoBarras(@PathVariable String codigoBarras) {
        return bienService.buscarPorCodigoBarras(codigoBarras);
    }
    @GetMapping("/{id}/barcode")
    public ResponseEntity<Object> getBarcodeImage(@PathVariable Long id) {
        Optional<Bien> bienOptional = bienRepository.findById(id);
        if (bienOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Bien bien = bienOptional.get();
        try {
            byte[] barcodeImage = BarcodeGenerator.generateBarcodeImage(bien.getCodigoBarras());
            String base64Image = Base64.getEncoder().encodeToString(barcodeImage);
            return new ResponseEntity<>(new Message(base64Image, "Imagen del código de barras", TypesResponse.SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error al generar la imagen del código de barras", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodosLosBienes() {
        return bienService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> crearBienCompleto(@Validated(BienDto.RegistrarBien.class) @RequestBody BienDto bienDTO) {
        return bienService.crearBien(bienDTO);
    }

    @PutMapping("/{idBien}")
    public ResponseEntity<Object> actualizarBien(@PathVariable Long idBien, @Validated(BienDto.ModificarBien.class) @RequestBody BienDto bienDTO) {
        return bienService.actualizarBien(idBien, bienDTO);
    }

    @PatchMapping("/{idBien}/status")
    public ResponseEntity<Object> cambiarStatus ( @Validated (BienDto.CambiarStatus.class) @PathVariable Long idBien) {
        return bienService.cambiarStatus(idBien);
    }

}
