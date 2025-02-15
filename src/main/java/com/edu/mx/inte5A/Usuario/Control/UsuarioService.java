package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Usuario.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public UsuarioDto buscarPorId(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        return usuarioOpt.map(this::convertirADto).orElse(null);
    }

    public List<UsuarioDto> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::convertirADto).toList();
    }

    public UsuarioDto crear(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return convertirADto(nuevoUsuario);
    }

    public UsuarioDto actualizar(Long id, Usuario usuarioDetalles) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();
            usuarioExistente.setNombre(usuarioDetalles.getNombre());
            usuarioExistente.setUsuario(usuarioDetalles.getUsuario());
            usuarioExistente.setContrasena(usuarioDetalles.getContrasena());
            usuarioExistente.setStatus(usuarioDetalles.isStatus());
            usuarioExistente.setRol(usuarioDetalles.getRol());
            usuarioExistente.setLugar(usuarioDetalles.getLugar());
            Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
            return convertirADto(usuarioActualizado);
        }
        return null;
    }

    public boolean cambiarEstatus(Long id, boolean nuevoEstatus) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setStatus(nuevoEstatus);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    private UsuarioDto convertirADto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getIdusuario(),
                usuario.getNombre(),
                usuario.getUsuario(),
                usuario.isStatus(),
                usuario.getRol(),
                usuario.getLugar()
        );
    }
}