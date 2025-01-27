package com.edu.mx.inte5A.Bien.Model;

public class BienDto {

    private int idBien;
    private String codigoBarras;
    private String nSerie;
    private boolean status;

    // Detalles de entidades relacionadas
    private Long tipoBienId;
    private String tipoBienNombre;

    private Long responsableId;
    private String responsableNombre;

    private Long modeloId;
    private String modeloNombre;

    private int marcaId;
    private String marcaNombre;

    private int lugarId;
    private String lugarNombre;

    public BienDto() {}

    public int getIdBien() {
        return idBien;
    }

    public void setIdBien(int idBien) {
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

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public long getLugarId() {
        return lugarId;
    }

    public void setLugarId(int lugarId) {
        this.lugarId = lugarId;
    }

    public String getLugarNombre() {
        return lugarNombre;
    }

    public void setLugarNombre(String lugarNombre) {
        this.lugarNombre = lugarNombre;
    }
}
