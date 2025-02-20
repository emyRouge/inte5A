package com.edu.mx.inte5A.Lugar.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LugarDto {

    @NotNull(groups = { CambiarStatus.class}, message = "Es necesario el id del lugar")
    private Long idLugar;

    @NotBlank(groups = {RegistrarLugar.class, CambiarStatus.class}, message = "Es necesario el nombre del lugar")
    private String lugar;

    private boolean status;

    public LugarDto() {}

    public LugarDto(Long idLugar, String lugar, boolean status) {
        this.idLugar = idLugar;
        this.lugar = lugar;
        this.status = status;
    }

    public Long getIdlugar() {
        return idLugar;
    }

    public void setIdlugar(Long idLugar) {
        this.idLugar = idLugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    //Validaciones
    public interface RegistrarLugar {}
    public interface ModificarLugar {}
    public interface CambiarStatus {}
}
