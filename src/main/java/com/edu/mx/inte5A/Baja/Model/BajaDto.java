// BajaDto.java
package com.edu.mx.inte5A.Baja.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BajaDto {

    @NotNull(groups = {ModificarBaja.class, CambiarStatus.class}, message = "Es necesario el id")
    private Long idBaja;

    @NotBlank(groups = {RegistrarBaja.class, CambiarStatus.class}, message = "Es necesario especificar el motivo")
    private String motivo;

    //Esto se tiene que revisar porque no deberia ser long sino un objeto
    @NotNull(groups = {RegistrarBaja.class, CambiarStatus.class}, message = "Es necesario el bien")
    private Long idBien;

    public BajaDto() {}

    // Getters y Setters
    public Long getIdbaja() {
        return idBaja;
    }

    public void setIdbaja(Long idBaja) {
        this.idBaja = idBaja;
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