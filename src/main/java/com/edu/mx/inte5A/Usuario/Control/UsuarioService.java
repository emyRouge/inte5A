package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Usuario.Model.*;
import com.edu.mx.inte5A.utils.Message;
import com.edu.mx.inte5A.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarPorId(Long idUsuario) {
        logger.info("Ejecutando la funcion: buscarPorId");

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("No se encontro el usuario", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        UsuarioDto usuarioDto = convertToUsuarioDto(usuarioOptional.get());
        logger.info("Se encotro el id del usuario");
        return new ResponseEntity<>(new Message(usuarioDto, "El usuario se encontro exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodos() {
        logger.info("Ejecuntando funcion: buscarTodos");

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        List<UsuarioDto> usuarioDtos = usuarios.stream()
                .map(this::convertToUsuarioDto)
                .collect(Collectors.toList());

        logger.info("Listado de usuarios");
        return new ResponseEntity<>(new Message(usuarioDtos, "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crear(UsuarioDto usuarioDto) {
        logger.info("Ejecutando funcion: crear");

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setStatus(usuarioDto.isStatus());
        usuario.setRol(usuarioDto.getRol());
        usuario.setLugar(usuarioDto.getLugar());
        usuario = usuarioRepository.saveAndFlush(usuario);

        UsuarioDto nuevoUsuarioDto = convertToUsuarioDto(usuario);
        logger.info("Se creo el usuario");
        return new ResponseEntity<>(new Message(nuevoUsuarioDto, "Usuario guardado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity<Object> Actualizar (Long idUsuario, UsuarioDto usuarioDto) {
        logger.info("Ejecutando funcion: Actualizar");

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("No se encontro el usuario", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();

        if (usuarioDto.getNombre() != null) {
            usuario.setNombre(usuarioDto.getNombre());
        }

        if (usuarioDto.getUsuario() != null) {
            usuario.setUsuario(usuarioDto.getUsuario());
        }

        if (usuarioDto.getContrasena() != null) {
            usuario.setContrasena(usuarioDto.getContrasena());
        }

        if (usuarioDto.getRol() != null) {
            usuario.setStatus(usuarioDto.isStatus());
        }

        if (usuarioDto.getLugar() != null) {
            usuario.setLugar(usuarioDto.getLugar());
        }

        usuario.setStatus(usuarioDto.isStatus());
        usuario = usuarioRepository.saveAndFlush(usuario);
        UsuarioDto updatedUsuario = convertToUsuarioDto(usuario);

        logger.info("Se actualizo el usuario");
        return new ResponseEntity<>(new Message(updatedUsuario,"El usuario se actualizo correctamente", TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatus(Long idUsuario, boolean status) {
        logger.info("Ejecutando funcion: Cambiar status del usuario");

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("No se encontro el usuario", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setStatus(status);
        usuario = usuarioRepository.saveAndFlush(usuario);

        logger.info("Se cambio el status del usuario");
        return new ResponseEntity<>(new Message(usuario, "Se cambio el status del usuario exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    private UsuarioDto convertToUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdusuario(usuario.getIdusuario());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setUsuario(usuario.getUsuario());
        usuarioDto.setStatus(usuario.isStatus());
        usuarioDto.setRol(usuario.getRol());
        usuarioDto.setLugar(usuario.getLugar());
        return usuarioDto;
    }

}