package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Lugar.Model.LugarDto;
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

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> buscarUsuarioPorId(@PathVariable Long idUsuario) {
        return usuarioService.buscarUsuarioPorId(idUsuario);
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodosLosUsuarios() {
        return usuarioService.buscarTodosLosUsuarios();
    }

    @PostMapping
    public ResponseEntity<Object> crearUsuario(@Validated(UsuarioDto.RegistrarUsuario.class) @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.crearUsuario(usuarioDto);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable Long idUsuario, @Validated(UsuarioDto.ModificarUsuario.class) @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.actualizarUsuario(idUsuario, usuarioDto);
    }

    @PatchMapping("/{idUsuario}/status")
    public ResponseEntity<Object> cambiarStatusUsuario(@Validated(UsuarioDto.CambiarStatus.class) @PathVariable Long idUsuario) {
        return usuarioService.cambiarStatusUsuario(idUsuario);
    }

}