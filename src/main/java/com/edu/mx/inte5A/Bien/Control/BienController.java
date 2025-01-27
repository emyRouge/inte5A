package com.edu.mx.inte5A.Bien.Control;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bienes")
public class BienController {

    @Autowired
    private BienService bienService;

    @GetMapping("/{id}")
    public ResponseEntity<BienDto> getById(@PathVariable int id) {
        return ResponseEntity.ok(bienService.getById(id));
    }
    @Autowired
    private BienRepository bienRepository;
    @GetMapping
    public List<Bien> getAllBienes() {
        return bienRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<BienDto> create(@RequestBody BienDto bienDTO) {
        return ResponseEntity.ok(bienService.create(bienDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BienDto> update(@PathVariable int id, @RequestBody BienDto bienDTO) {
        return ResponseEntity.ok(bienService.update(id, bienDTO));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable int id, @RequestParam boolean status) {
        bienService.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
