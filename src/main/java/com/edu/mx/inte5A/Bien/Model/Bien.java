// Modelo de Bien
package com.edu.mx.inte5A.Bien.Model;
import com.edu.mx.inte5A.Baja.Model.Baja;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bien")
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBien;

    @ManyToOne
    @JoinColumn(name = "tipoBien", nullable = false)
    private TipoBien tipoBien;

    @ManyToOne
    @JoinColumn(name = "responsable", nullable = true)
    private Usuario responsable;

    @ManyToOne
    @JoinColumn(name = "idModelo", nullable = false)
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "idMarca", nullable = false)
    private Marca marca;

    @Column(name = "codigoBarras", nullable = false, unique = true, length = 45)
    private String codigoBarras;

    @Column(name = "nSerie", nullable = false, length = 100)
    private String nSerie;

    @ManyToOne
    @JoinColumn(name = "idLugar", nullable = true)
    private Lugar lugar;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Baja> bajas;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;

    public Bien() {}

    public Bien(TipoBien tipoBien, Usuario responsable, Modelo modelo, Marca marca, String codigoBarras, String nSerie, Lugar lugar, boolean status) {
        this.tipoBien = tipoBien;
        this.responsable = responsable;
        this.modelo = modelo;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.nSerie = nSerie;
        this.lugar = lugar;
        this.status = status;
    }

    // Getters y Setters...
}
