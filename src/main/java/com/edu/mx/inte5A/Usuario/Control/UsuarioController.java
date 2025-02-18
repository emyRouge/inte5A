package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Usuario.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long idUsuario) {
        return usuarioService.buscarPorId(idUsuario);
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> crearUsuario(@Validated(UsuarioDto.RegistrarUsuario.class) @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.crear(usuarioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long idUsuario, @Validated(UsuarioDto.ModificarUsuario.class) @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.Actualizar(idUsuario, usuarioDto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> cambiarEstatus(@PathVariable Long idUsuario,@Validated (UsuarioDto.CambiarStatus.class) @RequestParam boolean status) {
        return usuarioService.cambiarStatus(idUsuario, status);
    }

}