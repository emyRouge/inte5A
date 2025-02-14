package com.edu.mx.inte5A.Lugar.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LugarDto {

    @NotNull(groups = {ModificarLugar.class, CambiarStatus.class}, message = "Es necesario el id del lugar")
    private Long idlugar;

    @NotBlank(groups = {RegistrarLugar.class, CambiarStatus.class}, message = "Es necesario el nombre del lugar")
    private String lugar;

    public LugarDto() {}

    public Long getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(Long idlugar) {
        this.idlugar = idlugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    //Validaciones
    public interface RegistrarLugar {}
    public interface ModificarLugar {}
    public interface CambiarStatus {}
}
