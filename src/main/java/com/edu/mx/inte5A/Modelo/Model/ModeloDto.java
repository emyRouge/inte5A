// DTO
package com.edu.mx.inte5A.Modelo.Model;


public class ModeloDto {


    private Long idModelo;

    private String nombreModelo;

    private boolean status;

    private byte[] foto;

    public ModeloDto() {
    }

    public ModeloDto(Long idModelo, String nombreModelo, boolean status, byte[] foto) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.status = status;
        this.foto = foto;
    }

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}