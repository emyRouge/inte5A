package com.edu.mx.inte5A.Usuario.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;

public class UsuarioDto {

    private Long idusuario;
    private String nombre;
    private String usuario;
    private boolean status;
    private String rol;
    private Lugar lugar;

    public UsuarioDto() {}

    public UsuarioDto(Long idusuario, String nombre, String usuario, boolean status, String rol, Lugar lugar) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.status = status;
        this.rol = rol;
        this.lugar = lugar;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
