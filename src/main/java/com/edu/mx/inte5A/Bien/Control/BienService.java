package com.edu.mx.inte5A.Bien.Control;
import com.edu.mx.inte5A.AreaComun.Model.AreaComunDto;
import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import com.edu.mx.inte5A.Usuario.Model.UsuarioDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //Obtener por id
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> obtenerId(Long idBien) {
        logger.info("Ejecuntando funcion: obtenerId");

        Optional<Bien> bienOptional = bienRepository.findById(idBien);
        if (bienOptional.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("No se encontro el bien", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        BienDto bienDto = convertToDto(bienOptional.get());
        logger.info("Ejecuntando funcion: obtenerId");
        return new ResponseEntity<>(new Message(bienDto, "", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Obtener todo
    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarTodos() {
        logger.info("Ejecutando funcion: buscarTodos");

        List<Bien> bienes = bienRepository.findAll();

        if (bienes.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("Los bienes no se encontraron", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        List<BienDto> bienesDto = bienes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        logger.info("Listado de bienes completos");
        return new ResponseEntity<>(new Message(bienesDto, "Listado de bienes", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Crear bien
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crear(BienDto bienDto) {
        logger.info("Ejecuntando funcion: crear");

        Bien bien = new Bien();
        bien.setTipoBien(tipoBienRepository.findById(bienDto.getTipoBienDto().getIdTipo())
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado")));
        bien.setUsuario(usuarioRepository.findById(bienDto.getUsuarioDto().getIdusuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        bien.setModelo(modeloRepository.findById(bienDto.getModeloDto().getIdModelo())
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado")));
        bien.setMarca(marcaRepository.findById(bienDto.getMarcaDto().getIdmarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        bien.setLugar(lugarRepository.findById(bienDto.getLugarDto().getIdlugar())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado")));
        bien.setCodigoBarras(bienDto.getCodigoBarras());
        bien.setnSerie(bienDto.getNSerie());

        Bien bienGuardado = bienRepository.saveAndFlush(bien);
        BienDto bienDtoGuardado = convertToDto(bienGuardado);
        logger.info("El bien se guardo correctamente");
        return new ResponseEntity<>(new Message(bienDtoGuardado, "El bien se guardo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //Actualizar
    @Transactional(rollbackFor ={SQLException.class})
    public ResponseEntity<Object> Actualizar (Long idBien, BienDto bienDto) {
        logger.info("Ejecuntando funcion: Actualizar");

        Optional<Bien> optionalBien = bienRepository.findById(idBien);
        if (optionalBien.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Bien bien = optionalBien.get();
        bien.setCodigoBarras(bienDto.getCodigoBarras());
        bien.setnSerie(bienDto.getNSerie());
        bien.setStatus(bienDto.isStatus());

        bien.setTipoBien(tipoBienRepository.findById(bienDto.getTipoBienDto().getIdTipo())
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado")));
        bien.setUsuario(usuarioRepository.findById(bienDto.getUsuarioDto().getIdusuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        Bien bienGuardado = bienRepository.saveAndFlush(bien);
        BienDto bienDtoGuardado = convertToDto(bienGuardado);

        logger.info("Bien actualizado");
        return new ResponseEntity<>(new Message(bienDtoGuardado, "Bien actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    //CambiarStatus
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> cambiarStatus(Long idBien, boolean status) {
        logger.info("Ejecuntando funcion: Cambiar estado");

        Optional<Bien> optionalBien = bienRepository.findById(idBien);
        if (optionalBien.isEmpty()) {
            logger.info("No se encontro el bien");
            return new ResponseEntity<>(new Message("El bien no se encontro",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Bien bien = optionalBien.get();
        bien.setStatus(status);
        Bien bienGuardado = bienRepository.saveAndFlush(bien);
        BienDto bienDto = convertToDto(bienGuardado);

        logger.info("Cambio de status logrado");
        return new ResponseEntity<>(new Message (bienDto,"Se cambio el estado del bien correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    //Metodo para convertir Bien en BienDto
    private BienDto convertToDto (Bien bien) {
        BienDto bienDto = new BienDto();
        bienDto.setIdBien(bien.getIdBien());
        bienDto.setCodigoBarras(bien.getCodigoBarras());
        bienDto.setNSerie(bien.getnSerie());

        bienDto.setTipoBienDto(convertToTipoBienDto(bien.getTipoBien()));
        bienDto.setUsuarioDto(convertToUsuarioDto(bien.getUsuario()));
        bienDto.setModeloDto(convertToModeloDto(bien.getModelo()));
        bienDto.setMarcaDto(convertToMarcaDto(bien.getMarca()));
        bienDto.setLugarDto(convertToLugarDto(bien.getLugar()));
        return bienDto;
    }

    private TipoBienDto convertToTipoBienDto(TipoBien tipoBien) {
        TipoBienDto dto = new TipoBienDto();
        dto.setIdTipo(tipoBien.getIdTipo());
        dto.setNombre(tipoBien.getNombre());
        return dto;
    }

    private UsuarioDto convertToUsuarioDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setIdusuario(usuario.getIdusuario());
        dto.setRol(usuario.getRol()); //Aqui el rol puede ser admin, becario o responsable
        dto.setNombre(usuario.getNombre());
        return dto;
    }

    private ModeloDto convertToModeloDto(Modelo modelo) {
        ModeloDto dto = new ModeloDto();
        dto.setIdModelo(modelo.getIdModelo());
        dto.setNombreModelo(modelo.getNombreModelo());
        return dto;
    }

    private MarcaDto convertToMarcaDto (Marca marca) {
        MarcaDto dto = new MarcaDto();
        dto.setIdmarca(marca.getIdmarca());
        dto.setNombre(marca.getNombre());
        return dto;
    }

    private LugarDto convertToLugarDto (Lugar lugar) {
        LugarDto dto = new LugarDto();
        dto.setIdlugar(lugar.getIdlugar());
        dto.setLugar(lugar.getLugar());
        return dto;
    }

}
