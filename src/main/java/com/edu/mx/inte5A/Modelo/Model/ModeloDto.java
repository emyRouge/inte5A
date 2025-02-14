// DTO
package com.edu.mx.inte5A.Modelo.Model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModeloDto {

    @NotNull(groups = {ModificarModelo.class, CambiarStatus.class})
    private Long idModelo;

    @NotBlank(groups = {RegistrarModelo.class, ModificarModelo.class})
    private String nombreModelo;

    @NotNull(groups = {ModificarModelo.class, ModificarModelo.class})
    private byte[] foto;

    public ModeloDto() {
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