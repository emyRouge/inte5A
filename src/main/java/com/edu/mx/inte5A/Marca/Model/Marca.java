package com.edu.mx.inte5A.Marca.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmarca;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "marca")
    private List<Bien> bienes;

    public Marca() {}

    public Marca(String nombre, boolean status) {
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

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
    }
}
