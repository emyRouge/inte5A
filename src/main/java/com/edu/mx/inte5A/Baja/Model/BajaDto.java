// BajaDto.java
package com.edu.mx.inte5A.Baja.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BajaDto {

    @NotNull(groups = {ModificarBaja.class, CambiarStatus.class}, message = "Es necesario el id")
    private Long idbaja;

    @NotBlank(groups = {RegistrarBaja.class, CambiarStatus.class}, message = "Es necesario especificar el motivo")
    private String motivo;

    @NotNull(groups = {RegistrarBaja.class, CambiarStatus.class}, message = "Es necesario el bien")
    private Long idBien;

    public BajaDto() {}

    // Getters y Setters
    public Long getIdbaja() {
        return idbaja;
    }

    public void setIdbaja(Long idbaja) {
        this.idbaja = idbaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    //Validaciones
    public interface RegistrarBaja {}
    public interface ModificarBaja {}
    public interface CambiarStatus {}

}