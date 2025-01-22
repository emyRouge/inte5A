package com.edu.mx.inte5A.Modelo.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModelo;

    @Column(name = "nombreModelo", columnDefinition = "VARCHAR(100)")
    private String nombreModelo;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;

    @Lob
    @Column(name = "foto", nullable = true)
    private byte[] foto;
    @JsonIgnore
    @OneToMany(mappedBy = "modelo")
    private List<Bien> bienes;

    public Modelo() {
    }

    public Modelo(String nombreModelo, boolean status, byte[] foto) {
        this.nombreModelo = nombreModelo;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
    }
}
