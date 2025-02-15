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
    private Long idMarca;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "marca")
    private List<Bien> bienes;

    public Marca() {}

    public Marca(Long idMarca, String nombre, boolean status, List<Bien> bienes) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.status = status;
        this.bienes = bienes;
    }

    public Marca(String nombre, boolean status, List<Bien> bienes) {
        this.nombre = nombre;
        this.status = status;
        this.bienes = bienes;
    }

    public Long getIdmarca() {
        return idMarca;
    }

    public void setIdmarca(Long idMarca) {
        this.idMarca = idMarca;
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
