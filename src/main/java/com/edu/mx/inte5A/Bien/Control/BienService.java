package com.edu.mx.inte5A.Bien.Control;
import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienDto;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
import com.edu.mx.inte5A.Usuario.Model.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BienService {

    private static final Logger log = LoggerFactory.getLogger(BienService.class);

    private final BienRepository bienRepository;
    private final TipoBienRepository tipoBienRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final LugarRepository lugarRepository;

    @Autowired
    public BienService (BienRepository bienRepository, TipoBienRepository tipoBienRepository,
                        UsuarioRepository usuarioRepository, ModeloRepository modeloRepository,
                        MarcaRepository marcaRepository, LugarRepository lugarRepository) {
        this.bienRepository = bienRepository;
        this.tipoBienRepository = tipoBienRepository;
        this.usuarioRepository = usuarioRepository;
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
        this.lugarRepository = lugarRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> obtenerId (Long id) {

    }

}
