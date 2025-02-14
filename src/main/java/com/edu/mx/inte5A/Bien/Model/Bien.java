package com.edu.mx.inte5A.Bien.Model;
import com.edu.mx.inte5A.Baja.Model.Baja;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "bien")
@Entity
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBien;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "tipoBien", nullable = false)
    private TipoBien tipoBien;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "responsable", nullable = true)
    private Usuario responsable;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "idModelo", nullable = false)
    private Modelo modelo;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "idMarca", nullable = false)
    private Marca marca;

    @Column(name = "codigoBarras", nullable = false, unique = true, length = 45)
    private String codigoBarras;

    @Column(name = "nSerie", nullable = false, length = 100)
    private String nSerie;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "idLugar", nullable = true)
    private Lugar lugar;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Baja> bajas;

    //Faltaria agregar fecha de registro

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;

    public Bien() {
    }

    public Bien(Long idBien, TipoBien tipoBien, Usuario responsable, Modelo modelo, Marca marca, String codigoBarras, String nSerie, Lugar lugar, List<Baja> bajas, boolean status) {
        this.idBien = idBien;
        this.tipoBien = tipoBien;
        this.responsable = responsable;
        this.modelo = modelo;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.nSerie = nSerie;
        this.lugar = lugar;
        this.bajas = bajas;
        this.status = status;
    }

    public Bien(TipoBien tipoBien, Usuario responsable, Modelo modelo, Marca marca, String codigoBarras, String nSerie, Lugar lugar, List<Baja> bajas, boolean status) {
        this.tipoBien = tipoBien;
        this.responsable = responsable;
        this.modelo = modelo;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.nSerie = nSerie;
        this.lugar = lugar;
        this.bajas = bajas;
        this.status = status;
    }

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public TipoBien getTipoBien() {
        return tipoBien;
    }

    public void setTipoBien(TipoBien tipoBien) {
        this.tipoBien = tipoBien;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public List<Baja> getBajas() {
        return bajas;
    }

    public void setBajas(List<Baja> bajas) {
        this.bajas = bajas;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
