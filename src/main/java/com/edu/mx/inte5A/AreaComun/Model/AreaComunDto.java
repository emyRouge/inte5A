package com.edu.mx.inte5A.AreaComun.Model;

import com.edu.mx.inte5A.Lugar.Model.LugarDto;

public class AreaComunDto {

    private Long idArea;
    private String nombreArea;
    private boolean status;
    private LugarDto lugar;

    public AreaComunDto() {}

    public AreaComunDto(Long idArea, String nombreArea, boolean status, LugarDto lugar) {
        this.idArea = idArea;
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

    public LugarDto getLugar() {
        return lugar;
    }

    public void setLugar(LugarDto lugar) {
        this.lugar = lugar;
    }
}
