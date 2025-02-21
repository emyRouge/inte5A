package com.edu.mx.inte5A.Bien.Control;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import com.edu.mx.inte5A.Usuario.Model.UsuarioRepository;
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
import java.util.List;
import java.util.Optional;

@Service
public class BienService {

    private static final Logger logger = LoggerFactory.getLogger(BienService.class);

    private final BienRepository bienRepository;
    private final TipoBienRepository tipoBienRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final LugarRepository lugarRepository;

    @Autowired
    public BienService(BienRepository bienRepository, TipoBienRepository tipoBienRepository,
                       UsuarioRepository usuarioRepository, ModeloRepository modeloRepository,
                       MarcaRepository marcaRepository, LugarRepository lugarRepository) {
        this.bienRepository = bienRepository;
        this.tipoBienRepository = tipoBienRepository;
        this.usuarioRepository = usuarioRepository;
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
        this.lugarRepository = lugarRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodos() {
        logger.info("Ejecutando funcion: buscarTodos");
        List<Bien> bienes = bienRepository.findAll();

        if (bienes.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        logger.info("Listado de bienes");
        return new ResponseEntity<>(new Message(bienes, "Listado completo de bienes", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarPorId(Long idBien) {
        logger.info("Ejecutando funcion: buscarPorId");

        Optional<Bien> bienOptional = bienRepository.findById(idBien);
        if (bienOptional.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Bien bien = bienOptional.get();
        logger.info("Se encontro el id del bien");
        return new ResponseEntity<>(new Message(bien,"El bien se encontro exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearBien(BienDto bienDto) {
        logger.info("Ejecutando funcion: crear bien");

        if (bienDto.getIdTipoBien() == null) {
            logger.info("El ID del tipo de bien no puede ser nullo");
            return new ResponseEntity<>(new Message("El ID del tipo de bien no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdUsuario() == null) {
            logger.info("El ID del usuario no puede ser nullo");
            return new ResponseEntity<>(new Message("El ID del usuario no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdModelo() == null) {
            logger.info("El ID del modelo no puede ser nullo");
            return new ResponseEntity<>(new Message("El ID del modelo no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdMarca() == null) {
            logger.info("El ID de la marca no puede ser nullo");
            return new ResponseEntity<>(new Message("El ID de la marca no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdLugar() == null) {
            logger.error("El ID del lugar no puede ser nullo");
            return new ResponseEntity<>(new Message("El ID del lugar no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        TipoBien tipoBien = tipoBienRepository.findById(bienDto.getIdTipoBien())
                .orElseThrow(() -> {
                   logger.info("tipo de bien no encontrado con ID", bienDto.getIdTipoBien());
                   return new RuntimeException("Tipo de bien no encontrado");
                });

        Usuario usuario = usuarioRepository.findById(bienDto.getIdUsuario())
                .orElseThrow(()-> {
                    logger.info("usuario no encontrado con ID", bienDto.getIdUsuario());
                    return new RuntimeException("Usuario no encontrado");
                });

        Modelo modelo = modeloRepository.findById(bienDto.getIdModelo())
                .orElseThrow(()-> {
                    logger.info("mode no encontrado con ID", bienDto.getIdModelo());
                    return new RuntimeException("Modelo no encontrado");
                });

        Marca marca = marcaRepository.findById(bienDto.getIdMarca())
                .orElseThrow(()->{
                    logger.info("marca no encontrado con ID", bienDto.getIdMarca());
                    return new RuntimeException("Marca no encontrado");
                });

        Lugar lugar = lugarRepository.findById(bienDto.getIdLugar())
                .orElseThrow(()->{
                    logger.info("lugar no encontrado con ID", bienDto.getIdLugar());
                    return new RuntimeException("Lugar no encontrado");
                });

        if (bienDto.getCodigoBarras().length() > 45) {
            logger.info("El codigo de barras no puede exceder los 45 caracteres");
            return new ResponseEntity<>(new Message("El codigo de barras no puede exceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getnSerie().length() > 100) {
            logger.info("El numero de serie no puede exceder los 100 caracteres");
            return new ResponseEntity<>(new Message("El numero de serie no puede exceder los 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getFecha() == null) {
            logger.info("El fecha no puede ser nullo");
            return new ResponseEntity<>(new Message("La fecha no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Bien bien = new Bien();
        bien.setIdBien(bienDto.getIdBien());
        bien.setCodigoBarras(bienDto.getCodigoBarras());
        bien.setnSerie(bienDto.getnSerie());
        bien.setFecha(new Date());
        bien.setTipoBien(tipoBien);
        bien.setUsuario(usuario);
        bien.setModelo(modelo);
        bien.setMarca(marca);
        bien.setLugar(lugar);

        bien = bienRepository.saveAndFlush(bien);

        if (bien == null) {
            return new ResponseEntity<>(new Message("El bien no se registro", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        logger.info("Se creo el bien");
        return new ResponseEntity<>(new Message(bien,"Se creo el bie exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> actualizarBien (Long idBien,BienDto bienDto) {
        logger.info("Ejecutando funcion: actualizarBien");

        Optional<Bien> bienOptional = bienRepository.findById(idBien);
        if (bienOptional.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("No se encontro el bien", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getCodigoBarras().length() > 45) {
            logger.info("El codigo de barras no puede exceder los 45 caracteres");
            return new ResponseEntity<>(new Message("El codigo de barras no puede exceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getnSerie().length() > 100) {
            logger.info("El numero de serie no puede exceder los 100 caracteres");
            return new ResponseEntity<>(new Message("El numero de serie no puede exceder los 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getFecha() == null) {
            logger.info("El fecha no puede ser nullo");
            return new ResponseEntity<>(new Message("La fecha no puede ser nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        //Entidades relacionadas
        if (bienDto.getIdTipoBien() != null){
            Optional<TipoBien> tipoBienOptional = tipoBienRepository.findById(bienDto.getIdTipoBien());
            if (tipoBienOptional.isEmpty()) {
                logger.info("No se encontro el tipo de bien");
                return new ResponseEntity<>(new Message("No se encontro el tipo de bien", TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
            }
        }

        if (bienDto.getIdUsuario() != null){
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(bienDto.getIdUsuario());
            if (usuarioOptional.isEmpty()) {
                logger.info("No se encontro el usuario");
                return new ResponseEntity<>(new Message("No se encontro el usuario", TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
            }
        }

        if (bienDto.getIdModelo() != null){
            Optional<Modelo> modeloOptional = modeloRepository.findById(bienDto.getIdModelo());
            if (modeloOptional.isEmpty()) {
                logger.info("No se encontro el modelo");
                return new ResponseEntity<>(new Message("No se encontro el modelo", TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
            }
        }

        if (bienDto.getIdMarca() != null){
            Optional<Marca> marcaOptional = marcaRepository.findById(bienDto.getIdMarca());
            if (marcaOptional.isEmpty()) {
                logger.info("No se encontro el marca");
                return new ResponseEntity<>(new Message("No se encontro el marca", TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
            }
            bienDto.setMarca(marcaOptional.get());
        }

        if (bienDto.getIdLugar() != null){
            Optional<Lugar> lugarOptional = lugarRepository.findById(bienDto.getIdLugar());
            if (lugarOptional.isEmpty()) {
                logger.info("No se encontro el lugar");
                return new ResponseEntity<>(new Message("No se encontro el lugar", TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
            }

        }
        Bien bien = new Bien();
        bien.setIdBien(bienDto.getIdBien());
        bien.setCodigoBarras(bienDto.getCodigoBarras());
        bien.setnSerie(bienDto.getnSerie());
        bien.setFecha(new Date());

        if (bienDto.getFecha() != null){
            bien.setFecha(bienDto.getFecha());
        } else {
            bien.setFecha(new Date());
        }

        if (bienDto.getIdTipoBien() != null) {
            TipoBien tipoBien = tipoBienRepository.findById(bienDto.getIdTipoBien())
                    .orElseThrow(()-> {
                        logger.info("No se encontro el tipo de bien");
                        return new RuntimeException("No se encontro el tipo de bien");
                    });
        }

        if (bienDto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(bienDto.getIdUsuario())
                    .orElseThrow(()-> {
                        logger.info("No se encontro el usuario");
                        return new RuntimeException("No se encontro el usuario");
                    });
        }

        if (bienDto.getIdModelo() != null) {
            Modelo modelo = modeloRepository.findById(bienDto.getIdModelo())
                    .orElseThrow(()->{
                        logger.info("No se encontro el modelo");
                        return new RuntimeException("No se encontro el modelo");
                    });
        }

        if (bienDto.getIdMarca() != null) {
            Marca marca = marcaRepository.findById(bienDto.getIdMarca())
                    .orElseThrow(()-> {
                        logger.info("No se encontro el marca");
                        return new RuntimeException("No se encontro el marca");
                    });
        }

        if (bienDto.getIdLugar() != null) {
            Lugar lugar = lugarRepository.findById(bienDto.getIdLugar())
                    .orElseThrow(()->{
                        logger.info("No se encontro el lugar");
                        return new RuntimeException("No se encontro el lugar");
                    });
        }

        bien = bienRepository.saveAndFlush(bien);

        logger.info("Se actualizo el bien");
        return new ResponseEntity<>(new Message(bien,"Se actualizo el bien correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatus (Long idBien) {
        logger.info("Ejecutando funcion: cambiarStatus");

        Optional<Bien> bienOptional = bienRepository.findById(idBien);
        if (bienOptional.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }

        Bien bien = bienOptional.get();
        bien.setStatus(!bien.isStatus());
        bienRepository.saveAndFlush(bien);

        if (bien == null) {
            return new ResponseEntity<>(new Message("El bien no se cambio de status", TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }

        logger.info("Se actualizo el estado del bien");
        return new ResponseEntity<>(new Message(bien, "Se actualizo el estado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
