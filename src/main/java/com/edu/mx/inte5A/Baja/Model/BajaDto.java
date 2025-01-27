// BajaDto.java
package com.edu.mx.inte5A.Baja.Model;

import java.util.Date;

public class BajaDto {
    private Long idbaja;
    private String motivo;
    private Date fecha;
    private Long idBien;

    // Getters y Setters
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

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }
}
