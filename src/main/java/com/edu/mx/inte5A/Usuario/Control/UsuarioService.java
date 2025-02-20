package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
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
    private final LugarRepository lugarRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, LugarRepository lugarRepository) {
        this.usuarioRepository = usuarioRepository;
        this.lugarRepository = lugarRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarPorId(Long idUsuario) {
        logger.info("Ejecutando la funcion: buscarPorId");

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("No se encontro el usuario", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();
        logger.info("Se encotro el id del usuario");
        return new ResponseEntity<>(new Message(usuario, "El usuario se encontro exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodos() {
        logger.info("Ejecuntando funcion: buscarTodos");
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        logger.info("Listado de usuarios");
        return new ResponseEntity<>(new Message(usuarios, "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearUsuario(UsuarioDto usuarioDto) {
        logger.info("Ejecutando la funcion: crearUsuario");

        if (usuarioDto.getIdLugar() == null) {
            logger.error("El ID del lugar no puede ser nulo");
            return new ResponseEntity<>(new Message("El ID del lugar no puede ser nulo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Lugar lugar = lugarRepository.findById(usuarioDto.getIdLugar())
                .orElseThrow(() -> {
                    logger.error("Lugar no encontrado con ID: {}", usuarioDto.getIdLugar());
                    return new RuntimeException("Lugar no encontrado");
                });

        if (usuarioDto.getNombre().length() > 100) {
            logger.info("Error al actualizar nombre");
            return new ResponseEntity<>(new Message("El nombre no puede acceder los 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getUsuario().length() > 45) {
            logger.info("Error al actualizar usuario");
            return new ResponseEntity<>(new Message("El usuario no puede acceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getContrasena().length() > 255) {
            logger.info("Error al actualizar contrasena");
            return new ResponseEntity<>(new Message("La contraseña no puede tener mas de 255 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getRol().length() > 45) {
            logger.info("Error al actualizar rol");
            return new ResponseEntity<>(new Message("El rol no puede tener mas de 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setStatus(usuarioDto.isStatus());
        usuario.setRol(usuarioDto.getRol());
        usuario.setLugar(lugar);

        usuario = usuarioRepository.saveAndFlush(usuario);

        if  (usuario == null) {
            return new ResponseEntity<>(new Message("El usuario no se registro", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        logger.info("Se creó el usuario");
        return new ResponseEntity<>(new Message(usuario, "Usuario guardado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> actualizar(Long idUsuario, UsuarioDto usuarioDto) {
        logger.info("Ejecutando funcion: actualizar");
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("No se encontro el usuario", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        if (usuarioDto.getNombre().length() > 100) {
            logger.info("Error al actualizar nombre");
            return new ResponseEntity<>(new Message("El nombre no puede acceder los 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getUsuario().length() > 45) {
            logger.info("Error al actualizar usuario");
            return new ResponseEntity<>(new Message("El usuario no puede acceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getContrasena().length() > 255) {
            logger.info("Error al actualizar contrasena");
            return new ResponseEntity<>(new Message("La contraseña no puede tener mas de 255 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getRol().length() > 45) {
            logger.info("Error al actualizar rol");
            return new ResponseEntity<>(new Message("El rol no puede tener mas de 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDto.getIdLugar() != null) {
            Optional<Lugar> lugarOptional = lugarRepository.findById(usuarioDto.getIdLugar());

            if (lugarOptional.isEmpty()) {
                logger.info("No se encontro el lugar");
                return new ResponseEntity<>(new Message("No se encontro el lugar", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
            }
        }

        Usuario usuario = new Usuario();
        usuario.setIdusuario(idUsuario);
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setRol(usuarioDto.getRol());

        if (usuarioDto.getIdLugar() != null) {
            Lugar lugar = lugarRepository.findById(usuarioDto.getIdLugar()).
                    orElseThrow(() ->  { logger.info("No se encontro el lugar");
                    return new RuntimeException("Lugar no encontrado");
                    });
        }

        usuario = usuarioRepository.saveAndFlush(usuario);

        logger.info("Usuario actualizado correctamente");
        return new ResponseEntity<>(new Message(usuario, "El usuario se ha actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatus(Long idUsuario) {
        logger.info("Ejecutando funcion: Cambiar status del usuario");
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setStatus(!usuario.isStatus());
        usuario = usuarioRepository.saveAndFlush(usuario);

        if (usuario == null) {
            return new ResponseEntity<>(new Message("El estado no se actualizo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("Estado de usuario actualizado correctamente");
        return new ResponseEntity<>(new Message(usuario,"El estado se actualizo correctamente",TypesResponse.SUCCESS), HttpStatus.OK);
    }
}