// DTO
package com.edu.mx.inte5A.TipoBien.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoBienDto {

    private Long idTipo;

    @NotBlank(groups = {RegistrarTipoBien.class, ModificarTipoBien.class}, message = "Es necesario el nombre del tipo de bien")
    private String nombre;

    private boolean status;

    public TipoBienDto() {
    }

    public TipoBienDto(Long idTipo, String nombre, boolean status) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Validaciones
    public interface RegistrarTipoBien {}
    public interface ModificarTipoBien {}
    public interface CambiarStatus {}

}