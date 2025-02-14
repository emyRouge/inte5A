// DTO
package com.edu.mx.inte5A.TipoBien.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoBienDto {

    @NotNull(groups = {ModificarTipoBien.class, CambiarStatus.class}, message = "Es necesario el id del tipo de bien")
    private Long idTipo;

    @NotBlank(groups = {RegistrarTipoBien.class, ModificarTipoBien.class}, message = "Es necesario el nombre del tipo de bien")
    private String nombre;

    public TipoBienDto() {
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Validaciones
    public interface RegistrarTipoBien {}
    public interface ModificarTipoBien {}
    public interface CambiarStatus {}

}