package com.edu.mx.inte5A.Baja.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "baja")
public class Baja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idbaja;

    @Column(name = "motivo", columnDefinition = "VARCHAR(255)")
    private String motivo;

    @Column(name = "fecha", columnDefinition = "DATE")
    private Date fecha;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idbien", nullable = false)
    private Bien bien;

    public Baja() {
    }

    public Baja(Long idbaja, String motivo, Date fecha, Bien bien) {
        this.idbaja = idbaja;
        this.motivo = motivo;
        this.fecha = fecha;
        this.bien = bien;
    }

    public Baja(String motivo, Date fecha, Bien bien) {
        this.motivo = motivo;
        this.fecha = fecha;
        this.bien = bien;
    }

    public Long getIdbaja() {
        return idbaja;
    }

    public void setIdbaja(Long idbaja) {
        this.idbaja = idbaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }
}
