package com.edu.mx.inte5A.TipoBien.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoBien")
public class TipoBien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    @Column(name = "nombre", columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(name = "statusTipo", columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "tipoBien")
    private List<Bien> bienes;

    public TipoBien() {
    }

    public TipoBien(String nombre, boolean status) {
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

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
    }
}
