package com.edu.mx.inte5A.AreaComun.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AreaComunDto {

    @NotNull(groups = {CambiarStatus.class}, message = "Es necesario el id")
    private Long idArea;

    @NotBlank(groups = {RegistrarArea.class,ModificarArea.class}, message = "Es necesario el nombre del area")
    private String nombreArea;

    @NotNull(groups = {RegistrarArea.class,ModificarArea.class}, message = "Es necesario el lugar")
    private Lugar lugar;

    public AreaComunDto() {}

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    //Validaciones
    public interface RegistrarArea{}
    public interface ModificarArea{}
    public interface CambiarStatus{}

}
