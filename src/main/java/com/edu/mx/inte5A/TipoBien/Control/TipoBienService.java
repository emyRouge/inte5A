// Service
package com.edu.mx.inte5A.TipoBien.Control;

import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
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
public class TipoBienService {

    private static final Logger logger = LoggerFactory.getLogger(TipoBienService.class);
    private final TipoBienRepository tipoBienRepository;

    @Autowired
    public TipoBienService(TipoBienRepository tipoBienRepository) {
        this.tipoBienRepository = tipoBienRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodosTiposBines() {
        logger.info("Ejecutando funcion: buscar todos los tipos de bienes");

        List<TipoBienDto> tipoBienDtos = tipoBienRepository.findAll()
                .stream()
                .map(tipo -> new TipoBienDto(tipo.getIdTipo(), tipo.getNombre(), tipo.isStatus()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Message(tipoBienDtos, "No se encontraron los tipos de bien", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTipoBienPorId(Long idTipo) {
        logger.info("Ejecutando funcion: buscar tipo de bien por Id");

        Optional<TipoBien> tipoBienOptional = tipoBienRepository.findById(idTipo);
        if (tipoBienOptional.isEmpty()) {
            logger.info("No se encuentra el tipo de bien");
            return new ResponseEntity<>(new Message("El bien no se encontro", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        TipoBien tipoBien = tipoBienOptional.get();
        logger.info("Tipo de Bien encontrado");
        return new ResponseEntity<>(new Message(tipoBien, "El id de Tipo de bien encontrado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity <Object> buscarTipoBienPorNombre(String nombre) {
        logger.info("Ejecutando funcion: buscar tipo de bien por nombre");

        Optional<TipoBien> tipoBienOptional = tipoBienRepository.findByNombre(nombre);
        if (tipoBienOptional.isEmpty()) {
            logger.info("No se encuentra el nombre del tipo de bien");
            return new ResponseEntity<>(new Message("El nombre del tipo de bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        TipoBien tipoBien = tipoBienOptional.get();
        TipoBienDto tipoBienDto = new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());

        logger.info("Tipo de Bien encontrado");
        return new ResponseEntity<>(new Message(tipoBienDto ,"El nombre del tipo de bien se encontro exitosamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity <Object> cambiarStatusTipoBien(Long idTipo) {
        logger.info("Ejecutando funcion: cambiarStatus de tipo de bien");
        Optional<TipoBien> tipoBienOptional = tipoBienRepository.findById(idTipo);

        if (tipoBienOptional.isEmpty()) {
            logger.info("No se encuentra el tipo de bien");
            return new ResponseEntity<>(new Message("El bien no se encontro", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        TipoBien tipoBien = tipoBienOptional.get();
        tipoBien.setStatus(!tipoBien.isStatus());
        tipoBienRepository.saveAndFlush(tipoBien);

        TipoBienDto tipoBienDto = new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
        logger.info("Tipo de Bien encontrado");
        return new ResponseEntity<>(new Message(tipoBienDto,"Se cambio el status del tipo de bien", TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity <Object> actualizarTipoBien(Long idTipo, TipoBienDto tipoBienDto) {

        logger.info("Ejecutando funcion: actualizar tipo de bien");
        Optional<TipoBien> tipoBienOptional = tipoBienRepository.findById(idTipo);
        if (!tipoBienOptional.isPresent()) {
            logger.info("No se encontro el tipo de bien");
            return new ResponseEntity<>(new Message("El tipo de bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        TipoBien tipoBien = tipoBienOptional.get();
        tipoBien.setNombre(tipoBienDto.getNombre());
        tipoBien = tipoBienRepository.saveAndFlush(tipoBien);

        TipoBienDto updateTipoBienDto = new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());
        logger.info("Tipo de Bien actualizado");
        return new ResponseEntity<>(new Message(updateTipoBienDto,"El tipo de bien se actualizo correctamenet",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearTipoBien (TipoBienDto tipoBienDto) {
        logger.info("Ejecutando funcion: crear tipo de bien");

        if (tipoBienDto.getNombre().length() > 100) {
            logger.info("El nombre del tipo de bien no puede exceder mas de 100 caracteres");
            return new ResponseEntity<>(new Message("El nombre excede mas de 100 caracteres",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        TipoBien tipoBien = new TipoBien();
        tipoBien.setNombre(tipoBienDto.getNombre());
        tipoBien.setStatus(tipoBienDto.isStatus());
        tipoBien = tipoBienRepository.saveAndFlush(tipoBien);
        TipoBienDto nuevoTipoBienDto = new TipoBienDto(tipoBien.getIdTipo(), tipoBien.getNombre(), tipoBien.isStatus());

        logger.info("Tipo de bien creado ");
        return new ResponseEntity<>(new Message(nuevoTipoBienDto,"Tipo de bien creado exitsamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

}
