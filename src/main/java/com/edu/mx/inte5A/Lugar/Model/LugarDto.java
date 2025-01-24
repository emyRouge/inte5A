package com.edu.mx.inte5A.Lugar.Model;

public class LugarDto {
   private int idlugar;
   private String lugar;
   private boolean status;


    public LugarDto() {}

    public LugarDto(int idlugar,String lugar, boolean status) {
       this.idlugar = idlugar;
        this.lugar = lugar;
        this.status = status;
    }

    public int getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(int idlugar) {
        this.idlugar = idlugar;
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
