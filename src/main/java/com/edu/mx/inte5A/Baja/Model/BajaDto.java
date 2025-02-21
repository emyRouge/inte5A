// BajaDto.java
package com.edu.mx.inte5A.Baja.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BajaDto {

    @NotNull(groups = {CambiarStatus.class}, message = "Es necesario el id de la baja")
    private Long idBaja;

    @NotBlank(groups = {RegistrarBaja.class, CambiarStatus.class}, message = "Es necesario especificar el motivo")
    private String motivo;

    private Date fecha;

    //Esto se tiene que revisar porque no deberia ser long sino un objeto
    @NotNull(groups = {RegistrarBaja.class, CambiarStatus.class}, message = "Es necesario el id del bien")
    private Long idBien;

    @NotNull(groups = {RegistrarBaja.class}, message = "Es necesario el id del usuario")
    private Long idUsuario;

    public BajaDto() {}

    public BajaDto(Long idBaja, String motivo, Date fecha, Long idBien, Long idUsuario) {
        this.idBaja = idBaja;
        this.motivo = motivo;
        this.fecha = fecha;
        this.idBien = idBien;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public Long getIdBaja() {
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //Validaciones
    public interface RegistrarBaja {}
    public interface ModificarBaja {}
    public interface CambiarStatus {}

}