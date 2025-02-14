package com.edu.mx.inte5A.Bien.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BienDto {

    @NotNull(groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del bien")
    private Long idBien;

    @NotBlank(groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el codigo de barras")
    private String codigoBarras;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el numero de serie")
    private String nSerie;

    // Detalles de entidades relacionadas
    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del tipo de bien")
    private Long tipoBienId;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el nombre del tipo de bien")
    private String tipoBienNombre;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del responsable")
    private Long responsableId;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el nombre del responsable")
    private String responsableNombre;

    @NotNull(groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del modelo")
    private Long modeloId;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el nombre del modelo")
    private String modeloNombre;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id de la marca")
    private Long marcaId;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el nombre de la marca")
    private String marcaNombre;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del lugar")
    private Long lugarId;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message =  "Es necesario el nombre del lugar")
    private String lugarNombre;

    public BienDto() {}

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

    public Long getTipoBienId() {
        return tipoBienId;
    }

    public void setTipoBienId(Long tipoBienId) {
        this.tipoBienId = tipoBienId;
    }

    public String getTipoBienNombre() {
        return tipoBienNombre;
    }

    public void setTipoBienNombre(String tipoBienNombre) {
        this.tipoBienNombre = tipoBienNombre;
    }

    public Long getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Long responsableId) {
        this.responsableId = responsableId;
    }

    public String getResponsableNombre() {
        return responsableNombre;
    }

    public void setResponsableNombre(String responsableNombre) {
        this.responsableNombre = responsableNombre;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public String getModeloNombre() {
        return modeloNombre;
    }

    public void setModeloNombre(String modeloNombre) {
        this.modeloNombre = modeloNombre;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public Long getLugarId() {
        return lugarId;
    }

    public void setLugarId(Long lugarId) {
        this.lugarId = lugarId;
    }

    public String getLugarNombre() {
        return lugarNombre;
    }

    public void setLugarNombre(String lugarNombre) {
        this.lugarNombre = lugarNombre;
    }

    //Validaciones
    public interface RegistrarBien {}
    public interface ModificarBien {}
    public interface CambiarStatus {}

}
