// DTO
package com.edu.mx.inte5A.Marca.Model;

public class MarcaDto {

    private int idmarca;
    private String nombre;
    private boolean status;

    public MarcaDto() {}

    public MarcaDto(int idmarca, String nombre, boolean status) {
        this.idmarca = idmarca;
        this.nombre = nombre;
        this.status = status;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
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
}
