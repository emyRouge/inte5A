package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Usuario.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.buscarPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodos() {
        List<UsuarioDto> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crear(@RequestBody Usuario usuario) {
        UsuarioDto nuevoUsuario = usuarioService.crear(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> actualizar(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        UsuarioDto usuarioActualizado = usuarioService.actualizar(id, usuarioDetalles);
        return usuarioActualizado != null ? ResponseEntity.ok(usuarioActualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> cambiarEstatus(@PathVariable Long id, @RequestParam boolean status) {
        boolean actualizado = usuarioService.cambiarEstatus(id, status);
        return actualizado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
