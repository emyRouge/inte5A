package com.edu.mx.inte5A.Lugar.Control;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
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
public class LugarService {

    private static final Logger logger = LoggerFactory.getLogger(LugarService.class);
    private final LugarRepository lugarRepository;

    @Autowired
    public LugarService(LugarRepository lugarRepository) {
        this.lugarRepository = lugarRepository;
    }

    //Crear Lugar
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearLugar (LugarDto lugarDto) {
        logger.info("Ejecutando funcion: crear lugar");

        if (lugarDto.getLugar().length() > 50) {
            logger.info("El nombre del lugar no puede exceder mas de los 50 caracteres");
            return new ResponseEntity<>(new Message("El nombre no puede exceder mas de los 50 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Lugar lugar = new Lugar();
        lugar.setLugar(lugarDto.getLugar());
        lugar.setStatus(lugarDto.isStatus());
        lugar = lugarRepository.saveAndFlush(lugar);

        LugarDto nuevoLugarDto = new LugarDto(lugar.getIdlugar(), lugarDto.getLugar(), lugarDto.isStatus());

        logger.info("Lugar registrado correctamente");
        return new ResponseEntity<>(new Message(nuevoLugarDto, "Lugar registrado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //Buscar todos
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodosLosLugares() {
        logger.info("Ejecutando funcion: buscar todos los lugares");

        List<LugarDto> lugares = lugarRepository.findAll()
                .stream()
                .map(lugar -> new LugarDto(lugar.getIdlugar(), lugar.getLugar(), lugar.isStatus()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Message(lugares, "Listado de lugares", TypesResponse.SUCCESS), HttpStatus.OK);
    }
    //Buscar bienes por lugar
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarBienesPorLugar(Long idLugar) {
        logger.info("Ejecutando funcion: BuscarBienesPorLugar");

        List<Bien> bienes = lugarRepository.findBienesByLugarId(idLugar);
        if (bienes.isEmpty()) {
            logger.warn("No se encontraron bienes para el lugar con ID: " + idLugar);
            return new ResponseEntity<>(new Message("No se encontraron bienes para el lugar", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Message(bienes, "Listado de bienes por lugar", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar por ID
    @Transactional(readOnly = true)
    public ResponseEntity <Object> buscarLugaresPorId(Long idLugar) {
        logger.info("Ejecutando funcion: buscar lugares por ID");

        Optional<Lugar> lugarOptional = lugarRepository.findById(idLugar);
        if (lugarOptional.isEmpty()) {
            logger.warn("No se encuentra el lugar");
            return new ResponseEntity<>(new Message("El lugar no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Lugar lugar = lugarOptional.get();
        LugarDto lugarDto = new LugarDto(lugar.getIdlugar(), lugar.getLugar(), lugar.isStatus());
        logger.info("Lugar encontrado exitosamente");
        return new ResponseEntity<>(new Message(lugarDto, "Lugar encontrado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar por nombre
    @Transactional(readOnly = true)
    public ResponseEntity <Object> buscarLugaresPorNombre(String nombre) {
        logger.info("Ejecutando funcion: buscar lugares por nombre");

        Optional<Lugar> optionalLugar = lugarRepository.findByLugar(nombre);
        if (optionalLugar.isEmpty()) {
            logger.warn("No se encuentra el lugar");
            return new ResponseEntity<>(new Message("El lugar no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Lugar lugar = optionalLugar.get();
        LugarDto lugarDto = new LugarDto(lugar.getIdlugar(),lugar.getLugar(), lugar.isStatus());

        logger.info("Lugar encontrado exitosamente");
        return new ResponseEntity<>(new Message(lugarDto, "Lugar encontrado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity <Object> cambiarStatusLugar(Long idLugar) {
        logger.info("Ejecutando funcion: cambiar status lugar");
        Optional<Lugar> optionalLugar = lugarRepository.findById(idLugar);
        if (optionalLugar.isEmpty()) {
            logger.warn("No se encuentra el lugar");
            return new ResponseEntity<>(new Message("El lugar no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Lugar lugar = optionalLugar.get();
        lugar.setStatus(!lugar.isStatus());
        lugarRepository.saveAndFlush(lugar);

        LugarDto lugarDto = new LugarDto(lugar.getIdlugar(), lugar.getLugar(), lugar.isStatus());

        logger.info("Se modifico el status correctamente");
        return new ResponseEntity<>(new Message(lugarDto,"",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    //Actualizar
    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity <Object> actualizarLugar(Long idLugar, LugarDto lugarDto) {
        logger.info("Ejecutando funcion: actualizar lugar");

        Optional<Lugar> lugarOptional = lugarRepository.findById(idLugar);
        if (!lugarOptional.isPresent()) {
            logger.warn("No se encuentra el lugar");
            return new ResponseEntity<>(new Message("El lugar no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Lugar lugar = lugarOptional.get();
        lugar.setLugar(lugarDto.getLugar());
        lugar.setStatus(lugarDto.isStatus());
        lugar = lugarRepository.saveAndFlush(lugar);

        LugarDto updatedLugarDto = new LugarDto(lugar.getIdlugar(), lugar.getLugar(), lugar.isStatus());

        logger.info("Lugar encontrado exitosamente");
        return new ResponseEntity<>(new Message(updatedLugarDto, "Lugar modificado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }
}
