package com.edu.mx.inte5A.Usuario.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDto {

    @NotNull(groups = {ModificarUsuario.class, CambiarStatus.class}, message = "Es necesario un id para el usuario")
    private Long idUsuario;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesaio un nombre para el usuario")
    private String nombre;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario tener un usuario")
    private String usuario;

    @NotBlank (groups = {RegistrarRol.class, ModificarRol.class}, message = "Es necesario tener un rol asignado")
    private String rol;

    @NotBlank (groups = {RegistrarContrasena.class, ModificarContrasena.class, CambiarContrasena.class}, message = "Es necesario ingresar una contraseña")
    private String Contrasena;

    @NotNull(groups = {RegistrarLugar.class, ModificarLugar.class}, message = "Es necesario tener un lugar seleccionado")
    private Long idLugar;

    private boolean status;

    public UsuarioDto() {}

    public Long getIdusuario() {
        return idUsuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idUsuario = idusuario;
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

    public Long getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Long idLugar) {
        this.idLugar = idLugar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
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

    public interface CambiarContrasena{}
    public interface ModificarContrasena {}
    public interface RegistrarContrasena {}

    //Validaciones por añadir
    /*public interface Registrarse {}
    public interface IniciarSesion {}
    public interface CerrarSesion {}
    public interface ConsultarPerfilUsuario {}
    public interface ModificarPerfil {}
    public interface ModificarContrasena {}
    public interface VerificarToken {}
    public interface EnviarEmail {}*/
}
