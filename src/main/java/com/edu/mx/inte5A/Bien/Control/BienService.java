package com.edu.mx.inte5A.Bien.Control;
import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
import com.edu.mx.inte5A.Usuario.Model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BienService {

    @Autowired
    private BienRepository bienRepository;

    @Autowired
    private TipoBienRepository tipoBienRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private LugarRepository lugarRepository;

    public BienDto getById(int id) {
        return bienRepository.findById((long) id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));
    }

    public List<BienDto> getAll() {
        return bienRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BienDto create(BienDto bienDTO) {
        Bien bien = new Bien();
        bien.setTipoBien(tipoBienRepository.findById(bienDTO.getTipoBienId())
                .orElseThrow(() -> new RuntimeException("TipoBien no encontrado")));
        bien.setResponsable(usuarioRepository.findById(bienDTO.getResponsableId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        bien.setModelo(modeloRepository.findById(bienDTO.getModeloId())
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado")));
        bien.setMarca(marcaRepository.findById(bienDTO.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        bien.setLugar(lugarRepository.findById(bienDTO.getLugarId())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado")));
        bien.setCodigoBarras(bienDTO.getCodigoBarras());
        bien.setnSerie(bienDTO.getnSerie());
        bien.setStatus(bienDTO.isStatus());
        return convertToDTO(bienRepository.save(bien));
    }

    public List<BienDto> getByResponsable(Long idResponsable) {
        List<Bien> bienes = bienRepository.findByResponsable_Idusuario(idResponsable);
        return bienes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BienDto update(int id, BienDto bienDTO) {
        Bien bien = bienRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));

        bien.setCodigoBarras(bienDTO.getCodigoBarras());
        bien.setnSerie(bienDTO.getnSerie());
        bien.setStatus(bienDTO.isStatus());
        return convertToDTO(bienRepository.save(bien));
    }

    public void changeStatus(int id, boolean status) {
        Bien bien = bienRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));
        bien.setStatus(status);
        bienRepository.save(bien);
    }

    private BienDto convertToDTO(Bien bien) {
        BienDto dto = new BienDto();
        dto.setIdBien(bien.getIdBien());
        dto.setCodigoBarras(bien.getCodigoBarras());
        dto.setnSerie(bien.getnSerie());
        dto.setStatus(bien.isStatus());

        dto.setTipoBienId(bien.getTipoBien().getIdTipo());
        dto.setTipoBienNombre(bien.getTipoBien().getNombre());

        dto.setResponsableId(bien.getResponsable() != null ? bien.getResponsable().getIdusuario() : null);
        dto.setResponsableNombre(bien.getResponsable() != null ? bien.getResponsable().getNombre() : null);

        dto.setModeloId(bien.getModelo().getIdModelo());
        dto.setModeloNombre(bien.getModelo().getNombreModelo());

        dto.setMarcaId(bien.getMarca().getIdmarca());
        dto.setMarcaNombre(bien.getMarca().getNombre());

        dto.setLugarId(bien.getLugar() != null ? bien.getLugar().getIdlugar() : null);
        dto.setLugarNombre(bien.getLugar() != null ? bien.getLugar().getLugar() : null);

        return dto;
    }
}
