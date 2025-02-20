// DTO
package com.edu.mx.inte5A.Marca.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MarcaDto {

    @NotNull(groups = {CambiarStatus.class}, message = "Es necesario el id de la marca")
    private Long idMarca;

    @NotBlank(groups = {RegistrarMarca.class, ModificarMarca.class}, message = "Es necesario el nombre de la marca")
    private String nombre;

    private boolean status;

    public MarcaDto() {}

    public MarcaDto(Long idMarca, String nombre, boolean status) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdmarca() {
        return idMarca;
    }

    public void setIdmarca(Long idmarca) {
        this.idMarca = idmarca;
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
    public interface RegistrarMarca {}
    public interface ModificarMarca {}
    public interface CambiarStatus {}
}
