package com.edu.mx.inte5A.AreaComun.Control;

import com.edu.mx.inte5A.AreaComun.Model.AreaComun;
import com.edu.mx.inte5A.AreaComun.Model.AreaComunDto;
import com.edu.mx.inte5A.AreaComun.Model.AreaComunRepository;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaComunService {

    @Autowired
    private AreaComunRepository areaComunRepository;

    @Autowired
    private LugarRepository lugarRepository;

    public AreaComunDto crearAreaComun(AreaComunDto areaComunDto) {
        Lugar lugar = lugarRepository.findById((long) areaComunDto.getLugar().getIdlugar())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        AreaComun areaComun = new AreaComun(
                areaComunDto.getNombreArea(),
                areaComunDto.isStatus(),
                lugar
        );
        areaComun = areaComunRepository.save(areaComun);

        return new AreaComunDto(
                areaComun.getIdArea(),
                areaComun.getNombreArea(),
                areaComun.isStatus(),
                new LugarDto(
                        lugar.getIdlugar(),
                        lugar.getLugar(),
                        lugar.isStatus()
                )
        );
    }

    public AreaComunDto cambiarStatus(Long id) {
        AreaComun areaComun = areaComunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área Común no encontrada"));
        areaComun.setStatus(!areaComun.isStatus());
        areaComun = areaComunRepository.save(areaComun);

        return new AreaComunDto(
                areaComun.getIdArea(),
                areaComun.getNombreArea(),
                areaComun.isStatus(),
                new LugarDto(
                        areaComun.getLugar().getIdlugar(),
                        areaComun.getLugar().getLugar(),
                        areaComun.getLugar().isStatus()
                )
        );
    }

    public AreaComunDto asignarLugar(Long idArea, Long idLugar) {
        AreaComun areaComun = areaComunRepository.findById(idArea)
                .orElseThrow(() -> new RuntimeException("Área Común no encontrada"));
        Lugar lugar = lugarRepository.findById(idLugar)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        areaComun.setLugar(lugar);
        areaComun = areaComunRepository.save(areaComun);

        return new AreaComunDto(
                areaComun.getIdArea(),
                areaComun.getNombreArea(),
                areaComun.isStatus(),
                new LugarDto(
                        lugar.getIdlugar(),
                        lugar.getLugar(),
                        lugar.isStatus()
                )
        );
    }

    public AreaComunDto actualizarAreaComun(Long id, AreaComunDto areaComunDto) {
        AreaComun areaComun = areaComunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área Común no encontrada"));

        areaComun.setNombreArea(areaComunDto.getNombreArea());
        areaComun.setStatus(areaComunDto.isStatus());

        Lugar lugar = lugarRepository.findById((long) areaComunDto.getLugar().getIdlugar())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        areaComun.setLugar(lugar);
        areaComun = areaComunRepository.save(areaComun);

        return new AreaComunDto(
                areaComun.getIdArea(),
                areaComun.getNombreArea(),
                areaComun.isStatus(),
                new LugarDto(
                        lugar.getIdlugar(),
                        lugar.getLugar(),
                        lugar.isStatus()
                )
        );
    }
}
