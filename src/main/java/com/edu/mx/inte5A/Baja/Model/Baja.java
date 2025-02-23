package com.edu.mx.inte5A.Baja.Model;

import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "baja")
public class Baja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBaja;

    @Column(name = "motivo", columnDefinition = "VARCHAR(255)")
    private String motivo;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idBien", nullable = false)
    private Bien bien;

    public Baja() {
    }

    public Baja(Long idBaja, String motivo, Date fecha, Bien bien) {
        this.idBaja = idBaja;
        this.motivo = motivo;
        this.fecha = fecha;
        this.bien = bien;
    }

    public Baja(String motivo, Date fecha, Bien bien) {
        this.motivo = motivo;
        this.fecha = fecha;
        this.bien = bien;
    }

    public Long getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(Long idBaja) {
        this.idBaja = idBaja;
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
