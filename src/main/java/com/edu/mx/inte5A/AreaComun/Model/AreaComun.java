package com.edu.mx.inte5A.AreaComun.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import jakarta.persistence.*;

@Entity
@Table(name = "areaComun")
public class AreaComun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArea;

    @Column(name = "nombreArea", columnDefinition = "VARCHAR(100)")
    private String nombreArea;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "idLugar", nullable = false,unique = true)
    private Lugar lugar;

    public AreaComun() {
    }

    public AreaComun(String nombreArea, boolean status, Lugar lugar) {
        this.nombreArea = nombreArea;
        this.status = status;
        this.lugar = lugar;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
