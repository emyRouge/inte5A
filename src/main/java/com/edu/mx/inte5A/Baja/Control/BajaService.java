// BajaService.java
package com.edu.mx.inte5A.Baja.Control;

import com.edu.mx.inte5A.Baja.Model.Baja;
import com.edu.mx.inte5A.Baja.Model.BajaDto;
import com.edu.mx.inte5A.Baja.Model.BajaRepository;
import com.edu.mx.inte5A.Bien.Model.Bien;

import com.edu.mx.inte5A.Bien.Model.BienRepository;
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
import java.util.Date;
import java.util.Optional;

@Service
public class BajaService {

    private static final Logger logger = LoggerFactory.getLogger(BajaService.class);

    private final BajaRepository bajaRepository;
    private final BienRepository bienRepository;

    @Autowired
    public BajaService(BajaRepository bajaRepository, BienRepository bienRepository) {
        this.bajaRepository = bajaRepository;
        this.bienRepository = bienRepository;
    }

    //Guardar area
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearBaja(BajaDto bajaDto) {
        logger.info("Ejecutando funcion: guardarArea");

        bajaDto.setMotivo(bajaDto.getMotivo());
        if (bajaDto.getMotivo().length() > 255) {
            logger.warn("El motivo de la baja excede el número de caracteres (255)");
            return new ResponseEntity<>(new Message("El motivo de la baja excede el número de caracteres (255)", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Bien> bienOptional = bienRepository.findById(bajaDto.getIdBien());
        if (bienOptional.isEmpty()) {
            logger.info("No se encuentra el bien");
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Baja nuevaBaja = new Baja();
        nuevaBaja.setMotivo(bajaDto.getMotivo());
        nuevaBaja.setFecha(new Date());
        nuevaBaja.setBien(bienOptional.get());

        nuevaBaja = bajaRepository.saveAndFlush(nuevaBaja);
        if (nuevaBaja == null) {
            logger.info("No se pudo registrar el baja");
            return new ResponseEntity<>(new Message(nuevaBaja, "No se pudo registrar la baja", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("Baja registrada correctamente");
        return new ResponseEntity<>(new Message(nuevaBaja, "Baja registrada correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //Modificar area
    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity<Object> modificarBaja(Long idBaja, BajaDto bajaDto) {
        logger.info("Ejecutando funcion: modificarBaja");

        Optional<Baja> bajaOptional = bajaRepository.findById(idBaja);
        if (bajaOptional.isEmpty()) {
            logger.info("No se encuentra el baja");
            return new ResponseEntity<>(new Message("El baja no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        bajaDto.setMotivo(bajaDto.getMotivo());
        if (bajaDto.getMotivo().length() > 255) {
            logger.warn("El motivo de la baja excede el número de caracteres (255)");
            return new ResponseEntity<>(new Message("El motivo de la baja excede el número de caracteres (255)", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Baja bajaExistente = bajaOptional.get();
        bajaExistente.setMotivo(bajaDto.getMotivo());
        bajaExistente.setFecha(new Date());

        bajaExistente = bajaRepository.saveAndFlush(bajaExistente);

        if (bajaExistente == null) {
            logger.error("No se pudo modificar la baja");
            return new ResponseEntity<>(new Message("No se pudo modificar la baja", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("La baja se modifico correctamente");
        return new ResponseEntity<>(new Message("La baja se modifico correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar todas las bajas
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodas() {
        logger.info("Ejecutando funcion: buscarTodas");
        return new ResponseEntity<>(new Message(bajaRepository.findAll(), "Listado de bajas",TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar bajas por id

    public ResponseEntity<Object> buscarPorId(Long idBaja) {
        logger.info("Ejecutando funcion: buscarPorId");
        Optional<Baja> bajaOptional = bajaRepository.findById(idBaja);
        if (bajaOptional.isEmpty()) {
            logger.warn("No se encuentra el baja");
            return new ResponseEntity<>(new Message("La baja no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Baja baja = bajaOptional.get();
        logger.info("Baja encontrada correctamente");
        return new ResponseEntity<>(new Message(bajaOptional, "Baja encontrada: "+ idBaja, TypesResponse.SUCCESS), HttpStatus.OK);
    }

}