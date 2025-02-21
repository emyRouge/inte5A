// Service
package com.edu.mx.inte5A.Marca.Control;

import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
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
public class MarcaService {

    private static final Logger logger = LoggerFactory.getLogger(MarcaService.class);
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    //Crear
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearMarca (MarcaDto marcaDto) {
        logger.info("Ejecutando funcion: crear Marca");

        if (marcaDto.getNombre().length() > 100) {
            logger.info("El nombre de la marca no puede exceder mas de los 100 caracteres");
            return new ResponseEntity<>(new Message("El nombre no puede exceder mas de los 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Marca marca = new Marca();
        marca.setNombre(marcaDto.getNombre());
        marca.setStatus(marcaDto.isStatus());
        marca = marcaRepository.saveAndFlush(marca);

        MarcaDto nuevaMarca = new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());

        logger.info("Marca registrada correctamente");
        return new ResponseEntity<>(new Message(nuevaMarca, "La marca ha sido registrada correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar todos
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodasLasMarcas(){
        logger.info("Ejecutando funcion: buscar todas las marcas");

        List<MarcaDto> marcas = marcaRepository.findAll()
                .stream()
                .map(marca -> new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Message(marcas, "Listado de marcas", TypesResponse.SUCCESS),HttpStatus.OK);
    }

    //Buscar todos por id
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarMarcasPorId(Long idMarca){
        logger.info("Ejecutando funcion: buscar marcas por Id");
        Optional<Marca> marcaOptional = marcaRepository.findById(idMarca);

        if (marcaOptional.isEmpty()) {
            logger.warn("No se encuentra la marca");
            return new ResponseEntity<>(new Message("La marca no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Marca marca = marcaOptional.get();
        MarcaDto marcaDto = new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());

        logger.info("Marca encontrada correctamente");
        return new ResponseEntity<>(new Message(marcaDto, "La marca fue encontrada", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Buscar todos por nombre
    @Transactional(readOnly = true)
    public ResponseEntity <Object> buscarMarcasPorNombre(String nombre) {
        logger.info("Ejecutando funcion: buscar marcas por nombre");

        Optional<Marca> marcaOptional = marcaRepository.findByNombre(nombre);
        if (marcaOptional.isEmpty()) {
            logger.warn("No se encuentra la marca");
            return new ResponseEntity<>(new Message("La marca no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Marca marca = marcaOptional.get();
        MarcaDto marcaDto= new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());

        logger.info("Marca encontrada correctamente");
        return new ResponseEntity<>(new Message(marcaDto, "El nombre de la marca se encontro", TypesResponse.SUCCESS), HttpStatus.OK);

    }
    //Cambiar status
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatusMarcas (Long idMarca){
        logger.info("Ejecutando funcion: cambiar status marcas");

        Optional<Marca> marcaOptional = marcaRepository.findById(idMarca);
        if (marcaOptional.isEmpty()) {
            logger.warn("No se encuentra la marca");
            return new ResponseEntity<>(new Message("La marca no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Marca marca = marcaOptional.get();
        marca.setStatus(marca.isStatus());
        marca = marcaRepository.saveAndFlush(marca);
        MarcaDto marcaDto = new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());

        logger.info("Cambio de status exitosamente");
        return new ResponseEntity<>(new Message(marcaDto, "Se cambio el status", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    //Actualizar
    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity <Object> actualizarMarcas(Long idMarca, MarcaDto marcaDto) {
        logger.info("Ejecutando funcion: actualizar marcas");
        Optional<Marca> marcaOptional = marcaRepository.findById(idMarca);

        if (!marcaOptional.isPresent()) {
            logger.warn("No se encuentra la marca");
            return new ResponseEntity<>(new Message("La marca no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Marca marca = marcaOptional.get();
        marca.setStatus(marcaDto.isStatus());
        marca.setNombre(marcaDto.getNombre());
        marca = marcaRepository.saveAndFlush(marca);

        MarcaDto updatedMarcaDto = new MarcaDto(marca.getIdmarca(), marca.getNombre(), marca.isStatus());
        logger.info("Se modifico la marca correctamente");
        return new ResponseEntity<>(new Message(updatedMarcaDto,"",TypesResponse.SUCCESS), HttpStatus.OK);
    }


}