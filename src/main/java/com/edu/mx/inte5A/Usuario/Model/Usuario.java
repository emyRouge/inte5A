package com.edu.mx.inte5A.Usuario.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre", columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(name = "usuario", columnDefinition = "VARCHAR(45)", unique = true)
    private String usuario;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(255)")
    private String contrasena;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private boolean status;

    @Column(name = "rol", columnDefinition = "VARCHAR(45)")
    private String rol;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLugar", nullable = true)
    private Lugar lugar;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String usuario, String contrasena, boolean status, String rol, Lugar lugar) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.status = status;
        this.rol = rol;
        this.lugar = lugar;
    }

    public Usuario(String nombre, String usuario, String contrasena, boolean status, String rol, Lugar lugar) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.status = status;
        this.rol = rol;
        this.lugar = lugar;
    }

    public Long getIdusuario() {
        return idUsuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idUsuario  = idusuario;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
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

