package com.edu.mx.inte5A.Lugar.Control;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class LugarService {
    @Autowired
    private LugarRepository lugarRepository;

    public List<LugarDto> buscarTodos() {
        return lugarRepository.findAll()
                .stream()
                .map(lugar-> new LugarDto(lugar.getIdlugar(),lugar.getLugar(),lugar.isStatus()))
                .collect(Collectors.toList());

    }

public LugarDto buscarPorId(Long id) {
        Lugar lugar = lugarRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
                return new LugarDto(lugar.getIdlugar(),lugar.getLugar(),lugar.isStatus());
}
public LugarDto buscarPorNombre(String nombre) {
        Lugar lugar= lugarRepository.findByLugar(nombre)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
        return new LugarDto(lugar.getIdlugar(),lugar.getLugar(),lugar.isStatus());
}

public LugarDto CambiarStatus(Long id) {
        Lugar lugar= lugarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
        lugar.setStatus(!lugar.isStatus());
        lugarRepository.save(lugar);
        return new LugarDto(lugar.getIdlugar(),lugar.getLugar(),lugar.isStatus());

}

public LugarDto Actualizar(Long id,LugarDto lugarDto) {
    Lugar lugar= lugarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
    lugar.setLugar(lugarDto.getLugar());
    lugarRepository.save(lugar);
    return new LugarDto(lugar.getIdlugar(),lugar.getLugar(),lugar.isStatus());
}
    public LugarDto crearLugar(LugarDto lugarDto) {
        Lugar lugar = new Lugar();
        lugar.setLugar(lugarDto.getLugar());
        lugar.setStatus(lugarDto.isStatus());
        lugar = lugarRepository.save(lugar);
        return new LugarDto(lugar.getIdlugar(), lugar.getLugar(), lugar.isStatus());
    }


}
