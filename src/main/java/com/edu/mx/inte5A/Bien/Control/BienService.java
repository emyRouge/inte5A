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
import java.util.*;

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
    public ResponseEntity<Object> obtenerPorcentajeBienesOcupados() {
        logger.info("Ejecutando función: obtenerPorcentajeBienesOcupados");

        long totalBienes = bienRepository.count();
        long bienesOcupados = bienRepository.countByLugarIsNotNull(); // Solo cuenta los bienes asignados a un lugar
        long bienesLibres = totalBienes - bienesOcupados; // Bienes que no están ocupados

        if (totalBienes == 0) {
            return new ResponseEntity<>(new Message("No hay bienes registrados", TypesResponse.WARNING), HttpStatus.OK);
        }

        double porcentajeOcupados = (bienesOcupados * 100.0) / totalBienes;
        double porcentajeLibres = 100.0 - porcentajeOcupados;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("porcentajeOcupados", porcentajeOcupados);
        resultado.put("porcentajeLibres", porcentajeLibres);
        resultado.put("bienesOcupados", bienesOcupados);
        resultado.put("bienesLibres", bienesLibres);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
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

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarPorCodigoBarras(String codigoBarras) {
        Optional<Bien> bienOptional = bienRepository.findByCodigoBarras(codigoBarras);
        if (bienOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Message(bienOptional.get(), "Bien encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
    }



    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearBien(BienDto bienDto) {
        logger.info("Ejecutando funcion: crear bien");

        // Validación de parámetros obligatorios
        if (bienDto.getIdTipoBien() == null) {
            return new ResponseEntity<>(new Message("El ID del tipo de bien no puede ser nulo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdUsuario() == null) {
            return new ResponseEntity<>(new Message("El ID del usuario no puede ser nulo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdModelo() == null) {
            return new ResponseEntity<>(new Message("El ID del modelo no puede ser nulo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getIdMarca() == null) {
            return new ResponseEntity<>(new Message("El ID de la marca no puede ser nulo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Verificación de existencia de entidades relacionadas
        TipoBien tipoBien = tipoBienRepository.findById(bienDto.getIdTipoBien())
                .orElseThrow(() -> new RuntimeException("Tipo de bien no encontrado"));

        Usuario usuario = usuarioRepository.findById(bienDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Modelo modelo = modeloRepository.findById(bienDto.getIdModelo())
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado"));

        Marca marca = marcaRepository.findById(bienDto.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        // Si idLugar es proporcionado, intenta buscarlo; si no, asigna null
        Lugar lugar = (bienDto.getIdLugar() != null) ?
                lugarRepository.findById(bienDto.getIdLugar()).orElse(null) :
                null;

        // Generar código de barras si es necesario
        if (bienDto.getCodigoBarras() == null || bienDto.getCodigoBarras().isEmpty()) {
            bienDto.setCodigoBarras(generarCodigoBarras());
        }

        if (bienDto.getCodigoBarras().length() > 45) {
            return new ResponseEntity<>(new Message("El código de barras no puede exceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getnSerie().length() > 100) {
            return new ResponseEntity<>(new Message("El número de serie no puede exceder los 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (bienDto.getFecha() == null) {
            return new ResponseEntity<>(new Message("La fecha no puede ser nula", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Creación del objeto Bien y guardado
        Bien bien = new Bien();
        bien.setIdBien(bienDto.getIdBien());
        bien.setCodigoBarras(bienDto.getCodigoBarras());
        bien.setnSerie(bienDto.getnSerie());
        bien.setFecha(new Date());
        bien.setTipoBien(tipoBien);
        bien.setStatus(true);
        bien.setUsuario(usuario);
        bien.setModelo(modelo);
        bien.setMarca(marca);
        bien.setLugar(lugar); // Ahora puede ser null

        bien = bienRepository.saveAndFlush(bien);

        if (bien == null) {
            return new ResponseEntity<>(new Message("El bien no se registró", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        logger.info("Se creó el bien");
        return new ResponseEntity<>(new Message(bien, "Se creó el bien exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    // Método para generar el código de barras automáticamente
    private String generarCodigoBarras() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12); // Generar un código único de 12 caracteres
    }

    public ResponseEntity<Object> eliminarLugarDeBien(Long id) {
        Optional<Bien> bienOptional = bienRepository.findById(id);

        if (!bienOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "El bien con ID " + id + " no fue encontrado"));
        }

        Bien bien = bienOptional.get();
        bien.setLugar(null); // Eliminar la relación con el lugar
        bienRepository.save(bien);

        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Lugar eliminado del bien con ID " + id));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> obtenerBienesPorResponsable(Long idUsuario) {
        logger.info("Ejecutando función: obtenerBienesPorResponsable");

        // Buscar el usuario por su ID
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("Usuario no encontrado", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        // Obtener los bienes del usuario
        List<Bien> bienes = bienRepository.findByUsuario(usuarioOptional.get());

        if (bienes.isEmpty()) {
            return new ResponseEntity<>(new Message("El usuario no tiene bienes asignados", TypesResponse.WARNING), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Message(bienes, "Bienes asociados al usuario", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> asignarLugarABien(Long idBien, Long idLugar) {
        Optional<Bien> bienOptional = bienRepository.findById(idBien);
        Optional<Lugar> lugarOptional = lugarRepository.findById(idLugar);

        if (bienOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El bien no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        if (lugarOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El lugar no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Bien bien = bienOptional.get();

        if (bien.getLugar() != null) {
            return new ResponseEntity<>(new Message("El bien ya tiene un lugar asignado", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        bien.setLugar(lugarOptional.get());
        bienRepository.save(bien);

        return new ResponseEntity<>(new Message("Bien asignado correctamente al lugar", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> actualizarBien (Long idBien,BienDto bienDto) {
        logger.info("Ejecutando funcion: actualizarBien");
        Optional<Bien> bienOptional = bienRepository.findById(idBien);

        if (bienOptional.isPresent()) {
            Bien bien = bienOptional.get();
            bien.setCodigoBarras(bienDto.getCodigoBarras());
            bien.setnSerie(bienDto.getnSerie());
            bien.setFecha(new Date() != null ? bienDto.getFecha() : new Date());

            if (bienDto.getIdTipoBien() != null) {
                bien.setTipoBien(tipoBienRepository.findById(bienDto.getIdTipoBien()).orElse(null));
            }

            if (bienDto.getIdUsuario() != null) {
                bien.setUsuario(usuarioRepository.findById(bienDto.getIdUsuario()).orElse(null));
            }

            if (bienDto.getIdModelo() != null) {
                bien.setModelo(modeloRepository.findById(bienDto.getIdModelo()).orElse(null));
            }

            if (bienDto.getIdMarca() != null) {
                bien.setMarca(marcaRepository.findById(bienDto.getIdMarca()).orElse(null));
            }

            if (bienDto.getIdLugar() != null) {
                bien.setLugar(lugarRepository.findById(bienDto.getIdLugar()).orElse(null));
            }

            bien  = bienRepository.saveAndFlush(bien);
            return new ResponseEntity<>(new Message(bien, "Se Actualizo el bien correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new Message("No se encontro el bien", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

    }



}
