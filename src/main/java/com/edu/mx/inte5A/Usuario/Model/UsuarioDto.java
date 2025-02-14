package com.edu.mx.inte5A.Usuario.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDto {

    @NotNull(groups = {ModificarUsuario.class, CambiarStatus.class}, message = "Es necesario un id para el usuario")
    private Long idusuario;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesaio un nombre para el usuario")
    private String nombre;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario tener un usuario")
    private String usuario;

    @NotBlank (groups = {RegistrarRol.class, ModificarRol.class}, message = "Es necesario tener un rol asignado")
    private String rol;

    @NotNull(groups = {RegistrarLugar.class, ModificarLugar.class}, message = "Es necesario tener un lugar seleccionado")
    private Lugar lugar;

    public UsuarioDto() {}

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

    //Validaciones
    public interface RegistrarUsuario {}
    public interface ModificarUsuario {}
    public interface CambiarStatus {}

    public interface RegistrarLugar {}
    public interface ConsultarLugar {}
    public interface ModificarLugar {}

    public interface RegistrarRol {}
    public interface ModificarRol {}
    public interface ConsultarRol {}

    //Validaciones por añadir
    public interface Registrarse {}
    public interface IniciarSesion {}
    public interface CerrarSesion {}
    public interface ConsultarPerfilUsuario {}
    public interface ModificarPerfil {}
    public interface ModificarContrasena {}
    public interface VerificarToken {}
    public interface EnviarEmail {}
}
