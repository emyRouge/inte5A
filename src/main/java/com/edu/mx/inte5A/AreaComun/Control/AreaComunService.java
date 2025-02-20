package com.edu.mx.inte5A.AreaComun.Control;

import com.edu.mx.inte5A.AreaComun.Model.AreaComun;
import com.edu.mx.inte5A.AreaComun.Model.AreaComunDto;
import com.edu.mx.inte5A.AreaComun.Model.AreaComunRepository;
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
import java.util.Optional;

@Service
public class AreaComunService {

    private static final Logger logger = LoggerFactory.getLogger(AreaComunService.class);

    private final AreaComunRepository areaComunRepository;
    private final LugarRepository lugarRepository;

    @Autowired
    public AreaComunService(AreaComunRepository areaComunRepository,LugarRepository lugarRepository) {
        this.areaComunRepository = areaComunRepository;
        this.lugarRepository = lugarRepository;
    }

        @Transactional(rollbackFor = {SQLException.class})
        public ResponseEntity<Object> guardarArea(AreaComunDto areaComunDto) {
            logger.info("Ejecutando funcion: guardarArea");

            areaComunDto.setNombreArea(areaComunDto.getNombreArea());
            if (areaComunDto.getNombreArea().length() > 100 ) {
                logger.warn("El nombre del área excede el número de caracteres (100)");
                return new ResponseEntity<>(new Message("El nombre del area excede los numeros de los caracteres",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
            }

            areaComunDto.setLugar(areaComunDto.getLugar());
            Optional<Lugar> optionalLugar = lugarRepository.findById(areaComunDto.getLugar().getIdlugar());
            if (!optionalLugar.isPresent()) {
                logger.warn("No se encontro el lugar o no existe");
                return new ResponseEntity<>(new Message("El lugar no existe",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
            }

            AreaComun areaComun = new AreaComun(areaComunDto.getNombreArea(),true,optionalLugar.get());
            areaComun = areaComunRepository.saveAndFlush(areaComun);

            if (areaComun == null) {
                logger.error("No se registro el area de comun");
                return new ResponseEntity<>(new Message(areaComun,"No se registro el area de comun",TypesResponse.ERROR ), HttpStatus.BAD_REQUEST);
            }

            logger.info("Area de comun guardada correctamente");
            return new ResponseEntity<>(new Message(areaComun,"Se registro el area comun correctamente",TypesResponse.SUCCESS), HttpStatus.OK);
        }

    //Consultar areas comunes
    @Transactional(readOnly = true)
    public ResponseEntity<Object> consultarAreas() {
        logger.info("Ejecutando funcion: consultar areas");
        return new ResponseEntity<>(new Message(areaComunRepository.findAll(),"Listado de areas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Consultar areas activas
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> consultarAreasActivas(){
        logger.info("Ejecutando funcion: consultar areas activas");
        return new ResponseEntity<>(new Message(areaComunRepository.findAllByStatusIsTrue(), "Listado de areas activas",TypesResponse.SUCCESS ),HttpStatus.OK);
    }

    //Modificar areas comunes
    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity<Object> modificarArea(Long idArea, AreaComunDto areaComunDto) {
        logger.info("Ejecutando funcion: modificarArea");

        Optional<AreaComun> optional = areaComunRepository.findById(idArea);
        if (!optional.isPresent()) {
            logger.error("No se encontro el area de comun");
            return new ResponseEntity<>(new Message("No se encontro el area ",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        areaComunDto.setNombreArea(areaComunDto.getNombreArea());
        if (areaComunDto.getNombreArea().length() > 100 ) {
            logger.warn("El nombre del area excede el numero de caracteres (100)");
            return new ResponseEntity<>(new Message("El nombre del área no puede exceder 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        areaComunDto.setLugar(areaComunDto.getLugar());
        Optional<Lugar> optionalLugar = lugarRepository.findById(areaComunDto.getLugar().getIdlugar());
        if (!optionalLugar.isPresent()) {
            logger.error("No se encontro el lugar o no existe");
            return new ResponseEntity<>(new Message("No se encontro el lugar o no existe",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        AreaComun areaComun = optional.get();
        areaComun.setNombreArea(areaComunDto.getNombreArea());
        areaComun.setLugar(optionalLugar.get());
        areaComun = areaComunRepository.saveAndFlush(areaComun);
        if (areaComun == null) {
            logger.error("No se modifico el area de comun");
            return new ResponseEntity<>(new Message("No se modifico el area de comun",TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("Area de comun modificada correctamente");
        return new ResponseEntity(new Message("Se modifico el area correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatus(Long idArea) {
        logger.info("Ejecutando funcion: cambiarStatus");
        Optional<AreaComun> optional = areaComunRepository.findById(idArea);

        if (!optional.isPresent()) {
            logger.error("No se encontro el area de comun");
            return new ResponseEntity<>(new Message("No se encontro el area", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        AreaComun areaComun = optional.get();
        areaComun.setStatus(!areaComun.isStatus());
        areaComun = areaComunRepository.saveAndFlush(areaComun);
        if (areaComun == null) {
            logger.info("No se modifico el status del area");
            return new ResponseEntity<>(new Message("No se encontro el area", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("Se modifico el status del area correctamente");
        return new ResponseEntity<>(new Message(areaComun, "Se modifico el status del area correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //Asignar lugar
    @Transactional(rollbackFor = {SQLException.class, RuntimeException.class})
    public ResponseEntity<Message> asignarLugar(Long idArea, Long idLugar) {
        logger.info("Ejecutando funcion: asignarLugar");

        AreaComun areaComun = areaComunRepository.findById(idArea).orElseThrow(
                ()->{
                    logger.error("No se encontro el area de comun");
                    return new RuntimeException ("No se encontro el area de comun");
                }
        );

        Lugar lugar = lugarRepository.findById(idLugar).orElseThrow(
                ()-> {
                    logger.error("No se encontro el lugar");
                    return new RuntimeException ("No se encontro el lugar");
                }
        );
        areaComun.setLugar(lugar);
        areaComun = areaComunRepository.saveAndFlush(areaComun);

        if (areaComun == null) {
            logger.error("No se pudo asignar el lugar a el area");
            return new ResponseEntity<>(new Message("No se puedo asignar el area",TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        LugarDto lugarDto = new LugarDto();
        lugarDto.setIdlugar(lugar.getIdlugar());
        lugarDto.setLugar(lugar.getLugar());

        // Convertir LugarDto a Lugar
        Lugar lugarEntity = convertirLugarDtoALugar(lugarDto);

        AreaComunDto areaComunDto = new AreaComunDto();
        areaComunDto.setIdArea(areaComun.getIdArea());
        areaComunDto.setNombreArea(areaComun.getNombreArea());
        areaComunDto.setLugar(lugarEntity); // Ahora esto funciona porque el tipo coincide

        logger.info("Lugar asignado correctamente al área común");
        return new ResponseEntity<>(new Message(areaComunDto, "Lugar asignado correctamente al área común", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    public Lugar convertirLugarDtoALugar(LugarDto lugarDto) {
        Lugar lugar = new Lugar();
        lugar.setIdlugar(lugarDto.getIdlugar());
        lugar.setLugar(lugarDto.getLugar());
        return lugar;
    }



}
