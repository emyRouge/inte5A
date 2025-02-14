// DTO
package com.edu.mx.inte5A.Marca.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MarcaDto {

    @NotNull(groups = {ModificarMarca.class, CambiarStatus.class}, message = "Es necesario el id de la marca")
    private Long idmarca;

    @NotBlank(groups = {RegistrarMarca.class, ModificarMarca.class}, message = "Es necesario el nombre de la marca")
    private String nombre;

    public MarcaDto() {}

    public Long getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Long idmarca) {
        this.idmarca = idmarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Validaciones
    public interface RegistrarMarca {}
    public interface ModificarMarca {}
    public interface CambiarStatus {}
}
