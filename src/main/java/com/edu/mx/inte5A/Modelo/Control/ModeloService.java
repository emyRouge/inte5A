// Service
package com.edu.mx.inte5A.Modelo.Control;

import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
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
public class ModeloService {

    private static final Logger logger = LoggerFactory.getLogger(ModeloService.class);
    private final ModeloRepository modeloRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    //Buscar por id
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodos() {
        logger.info("Ejecutando funcion: buscarTodos");

        List<ModeloDto> modelos = modeloRepository.findAll()
                .stream()
                .map(modelo -> new ModeloDto(
                        modelo.getIdModelo(),
                        modelo.getNombreModelo(),
                        modelo.isStatus(),
                        modelo.getFoto() != null ? modelo.getFoto() : null
                ))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Message(modelos,"Listado de modelos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar por id
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarPorId(Long idModelo){
        logger.info("Ejecutando funcion: buscarPorId");
        Optional<Modelo> modeloOptional = modeloRepository.findById(idModelo);

        if(modeloOptional.isEmpty()){
            logger.info("Modelo no encontrado");
            return new ResponseEntity<>(new Message("",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Modelo modelo = modeloOptional.get();
        ModeloDto modeloDto = new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());
        logger.info("Modelo encontrado");
        return new ResponseEntity<>((new Message(modeloDto,"Modelo encontrado exitosamente" ,TypesResponse.SUCCESS )), HttpStatus.OK);
    }

    //Buscar por nombre
    @Transactional(readOnly = true)
    public ResponseEntity <Object> buscarPorNombre(String nombreModelo) {
        logger.info("Ejecutando funcion: buscarPorNombre");

        Optional<Modelo> modeloOptional = modeloRepository.findByNombreModelo(nombreModelo);
        if(modeloOptional.isEmpty()){
            logger.info("Modelo no encontrado");
            return new ResponseEntity<>(new Message(modeloOptional,"El modelo no se encontro",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Modelo modelo = modeloOptional.get();
        ModeloDto modeloDto = new ModeloDto(modelo.getIdModelo(),modelo.getNombreModelo(),modelo.isStatus(),modelo.getFoto());

        logger.info("Modelo encontrado");
        return new ResponseEntity<>(new Message(modeloDto,"",TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatus (Long idMarca){
        logger.info("Ejecutando funcion: cambiarStatus");

        Optional<Modelo> modeloOptional = modeloRepository.findById(idMarca);
        if(modeloOptional.isEmpty()){
            logger.info("Modelo no encontrado");
            return new ResponseEntity<>(new Message(modeloOptional,"",TypesResponse.SUCCESS),HttpStatus.OK);
        }

        Modelo modelo = modeloOptional.get();
        modelo.setStatus(modelo.isStatus());
        modeloRepository.saveAndFlush(modelo);
        ModeloDto modeloDto = new ModeloDto(modelo.getIdModelo(),modelo.getNombreModelo(),modelo.isStatus(),modelo.getFoto());

        logger.info("Se cambio el status correctamente");
        return new ResponseEntity<>(new Message(modeloDto,"Se cambio el status correctamente",TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //actualizar
    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity <Object> Actualizar(Long idModelo, ModeloDto modeloDto) {
        logger.info("Ejucatando funcion: Actualizar");
        Optional<Modelo> modeloOptional = modeloRepository.findById(idModelo);

        if(modeloOptional.isPresent()){
            logger.info("Modelo no encontrado");
            return new ResponseEntity<>(new Message("El modelo no se encontro",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Modelo modelo = modeloOptional.get();
        modelo.setNombreModelo(modeloDto.getNombreModelo());
        modelo.setFoto(modeloDto.getFoto());
        modeloRepository.saveAndFlush(modelo);

        ModeloDto updatedModelo = new ModeloDto(modelo.getIdModelo(), modelo.getNombreModelo(), modelo.isStatus(), modelo.getFoto());

        logger.info("Modelo actualizado correctamente");
        return new ResponseEntity<>(new Message(updatedModelo,"Modelo actualizado",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    //crear
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crear (ModeloDto modeloDto) {
        logger.info("Ejecutando funcion: crear");

        byte[] fotoBytes = null;
        if(modeloDto.getFoto() != null){
            fotoBytes = modeloDto.getFoto();
        }

        Modelo modelo = new Modelo();
        modelo.setNombreModelo(modeloDto.getNombreModelo());
        modelo.setStatus(modelo.isStatus());
        modelo.setFoto(fotoBytes);
        modeloRepository.saveAndFlush(modelo);

        logger.info("Modelo creado correctamente");
        return new ResponseEntity<>(new Message("Modelo creado correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }


}