// DTO
package com.edu.mx.inte5A.Modelo.Model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModeloDto {

    @NotNull(groups = {CambiarStatus.class}, message = "Es necesario el id del  modelo")
    private Long idModelo;

    @NotBlank(groups = {RegistrarModelo.class}, message = "Es necesario el nombre de la marca")
    private String nombreModelo;

    private byte[] foto;

    public ModeloDto() {
    }

    public ModeloDto(Long idModelo, String nombreModelo, boolean status, byte[] foto) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    //Validaciones
    public interface RegistrarModelo {}
    public interface ModificarModelo {}
    public interface CambiarStatus {}

}