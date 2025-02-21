package com.edu.mx.inte5A.Bien.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BienDto {

    @NotNull(groups = {CambiarStatus.class}, message = "Es necesario el id del bien")
    private Long idBien;

    @NotBlank(groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el codigo de barras")
    private String codigoBarras;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el numero de serie")
    private String nSerie;

    private boolean status;

    private Date fecha;

    // Detalles de entidades relacionadas
    @NotNull (groups = {RegistrarBien.class, CambiarStatus.class}, message = "Es necesario el id del tipo de bien")
    private Long idTipoBien;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del responsable")
    private Long idUsuario;

    @NotNull(groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del modelo")
    private Long idModelo;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id de la marca")
    private Long idMarca;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del lugar")
    private Long idLugar;

    public BienDto() {
    }

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getIdTipoBien() {
        return idTipoBien;
    }

    public void setIdTipoBien(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Long getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Long idLugar) {
        this.idLugar = idLugar;
    }

    //Validaciones
    public interface RegistrarBien {}
    public interface ModificarBien {}
    public interface CambiarStatus {}

}
