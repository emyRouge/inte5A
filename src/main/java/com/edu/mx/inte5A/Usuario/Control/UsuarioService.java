package com.edu.mx.inte5A.Usuario.Control;

import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import com.edu.mx.inte5A.Rol.Model.Rol;
import com.edu.mx.inte5A.Rol.Model.RolRepository;
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
    public ResponseEntity<Object> obtenerLugaresSinUsuarios() {
        logger.info("Ejecutando función: obtener lugares sin usuarios");

        List<Lugar> lugaresSinUsuarios = lugarRepository.findLugaresSinUsuarios();

        if (lugaresSinUsuarios.isEmpty()) {
            logger.info("No hay lugares sin usuarios asociados");
            return new ResponseEntity<>(new Message("No hay lugares sin usuarios", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        List<LugarDto> lugarDtos = lugaresSinUsuarios.stream()
                .map(lugar -> new LugarDto(lugar.getIdlugar(), lugar.getLugar(), lugar.isStatus()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Message(lugarDtos, "Lugares sin usuarios encontrados", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarUsuarioPorId(Long idUsuario) {
        logger.info("Ejecutando la funcion: buscar usuario por Id");

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
    public ResponseEntity<Object> buscarTodosLosUsuarios() {
        logger.info("Ejecuntando funcion: buscarTodos los usuarios");
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            logger.info("No se encontro el usuario");
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        logger.info("Listado de usuarios");
        return new ResponseEntity<>(new Message(usuarios, "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    @Autowired
    private RolRepository rolRepository;  // Asegúrate de tener este repositorio

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearUsuario(UsuarioDto usuarioDto) {
        logger.info("Ejecutando la función: crear Usuario");

        if (usuarioDto.getIdLugar() == null) {
            logger.error("El ID del lugar no puede ser nulo");
            return new ResponseEntity<>(new Message("El ID del lugar no puede ser nulo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Lugar lugar = lugarRepository.findById(usuarioDto.getIdLugar())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        // Validar Rol
        Rol rol = rolRepository.findById(usuarioDto.getRol().getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setStatus(usuarioDto.isStatus());
        usuario.setRol(rol);
        usuario.setLugar(lugar);

        usuario = usuarioRepository.saveAndFlush(usuario);
        logger.info("Usuario guardado exitosamente");
        return new ResponseEntity<>(new Message(usuario, "Usuario guardado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> actualizarUsuario(Long idUsuario, UsuarioDto usuarioDto) {
        logger.info("Ejecutando función: actualizar Usuario");
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuarioDto.getIdLugar() != null) {
            Lugar lugar = lugarRepository.findById(usuarioDto.getIdLugar())
                    .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
            usuario.setLugar(lugar);
        }

        if (usuarioDto.getRol() != null) {
            Rol rol = rolRepository.findById(usuarioDto.getRol().getIdRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
        }

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setStatus(usuarioDto.isStatus());

        usuario = usuarioRepository.saveAndFlush(usuario);
        logger.info("Usuario actualizado correctamente");
        return new ResponseEntity<>(new Message(usuario, "El usuario se ha actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatusUsuario(Long idUsuario) {
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