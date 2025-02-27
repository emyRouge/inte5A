package com.edu.mx.inte5A.Lugar.Model;

import com.edu.mx.inte5A.AreaComun.Model.AreaComun;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lugar")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLugar;

    @Column(name = "lugar", nullable = false, length = 50)
    private String lugar;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;

    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<AreaComun> areas;

    public Lugar() {}

    public Lugar(Long idLugar, String lugar, boolean status, List<AreaComun> areas) {
        this.idLugar = idLugar;
        this.lugar = lugar;
        this.status = status;
        this.areas = areas;
    }

    public Lugar(String lugar, boolean status, List<AreaComun> areas) {
        this.lugar = lugar;
        this.status = status;
        this.areas = areas;
    }

    public Long getIdlugar() {
        return idLugar;
    }

    public void setIdlugar(Long idlugar) {
        this.idLugar = idlugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}