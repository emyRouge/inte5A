package com.edu.mx.inte5A.utils;
import java.util.Date;
import com.edu.mx.inte5A.Bien.Model.Bien;
import com.edu.mx.inte5A.Bien.Model.BienRepository;
import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarRepository;
import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Marca.Model.MarcaRepository;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.Modelo.Model.ModeloRepository;
import com.edu.mx.inte5A.Rol.Model.Rol;
import com.edu.mx.inte5A.Rol.Model.RolRepository;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienRepository;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import com.edu.mx.inte5A.Usuario.Model.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepository, MarcaRepository marcaRepository, LugarRepository lugarRepository, ModeloRepository modeloRepository, TipoBienRepository tipoBienRepository, UsuarioRepository usuarioRepository, BienRepository bienRepository) {
        return args -> {



                Rol rol = new Rol("ROLE_ADMINISTRADOR");
                rolRepository.saveAndFlush(rol);

    Rol rol2 = new Rol("ROLE_RESPONSABLE");
                rolRepository.saveAndFlush(rol2);



                Rol rol3 = new Rol("ROLE_BECARIO");
                rolRepository.saveAndFlush(rol3);


            Lugar lugar1 = new Lugar( "M1", true, null);
            Lugar lugar2 = new Lugar( "M2", true, null);
            lugarRepository.saveAndFlush(lugar1);
            lugarRepository.saveAndFlush(lugar2);



            Marca marca1 = new Marca( "HP", true, null);
            Marca marca2 = new Marca( "Dell", true, null);
            Marca marca3 = new Marca( "Lenovo", true, null);

            marcaRepository.saveAndFlush(marca1);
            marcaRepository.saveAndFlush(marca2);
            marcaRepository.saveAndFlush(marca3);

            Modelo modelo1 = new Modelo("ss12", true, "https://m.media-amazon.com/images/I/61LdecwlWYL.jpg", null);
            Modelo modelo2 = new Modelo("sshhi3", true, "https://media.gq.com.mx/photos/61e70ca25def32c5619cef06/16:9/w_1280,c_limit/Lenovo%20Yoga%20Slim%207%20Pro.jpg", null);
            Modelo modelo3 = new Modelo("as3322", true, "https://helios-i.mashable.com/imagery/articles/05djrP5PjtVB7CcMtvrTOAP/images-4.fill.size_2000x1125.v1723100793.jpg", null);

            modeloRepository.saveAndFlush(modelo1);
            modeloRepository.saveAndFlush(modelo2);
            modeloRepository.saveAndFlush(modelo3);

            TipoBien tipoBien1 = new TipoBien("laptop",  true,null);
            TipoBien tipoBien2 = new TipoBien("Pc",  true,null);
            TipoBien tipoBien3 = new TipoBien("Tablet",  true,null);
            tipoBienRepository.saveAndFlush(tipoBien1);
            tipoBienRepository.saveAndFlush(tipoBien2);
            tipoBienRepository.saveAndFlush(tipoBien3);

            Usuario admin = new Usuario(
                    "Emiliano Santiago Rodríguez Castañeda",
                    "emy",
                    "zeldahw12", // Contraseña sin encriptar
                    true,
                    new Lugar( "M3", true, null)

            );

            admin.setRol(rol);
            usuarioRepository.saveAndFlush(admin);

            Usuario becario = new Usuario(
                    "Elias Márquez Bailón",
                    "elias",
                    "zeldahw12", // Contraseña sin encriptar
                    true,
                    new Lugar( "M4", true, null)

            );

            becario.setRol(rol3);
            usuarioRepository.saveAndFlush(becario);

            Usuario responsable = new Usuario(
                    "Gabriela Fernanda",
                    "fer",
                    "zeldahw12", // Contraseña sin encriptar
                    true,
                    new Lugar( "M5", true, null)

            );

            responsable.setRol(rol2);
            usuarioRepository.saveAndFlush(responsable);

            Bien nuevoBien = new Bien(
                    "1234567890c12", // Código de barras
                    "SN1ff2345", // Número de serie
                    true, // Estado activo
                    tipoBien1, // Tipo de bien
                    responsable, // Usuario responsable
                    modelo1, // Modelo
                    marca1, // Marca
                    lugar1, // Lugar
                    null, // Bajas (puedes dejarlo vacío si no hay bajas asociadas)
                    new Date() // Fecha de creación
            );

            bienRepository.saveAndFlush(nuevoBien);

            Bien nuevoBien2 = new Bien(
                    "1234a56783412", // Código de barras
                    "SN1a2ee345", // Número de serie
                    true, // Estado activo
                    tipoBien2, // Tipo de bien
                    responsable, // Usuario responsable
                    modelo2, // Modelo
                    marca2, // Marca

                    null, // Bajas (puedes dejarlo vacío si no hay bajas asociadas)
                    new Date() // Fecha de creación
            );

            bienRepository.saveAndFlush(nuevoBien2);

            Bien nuevoBien3 = new Bien(
                    "rfrfdrseh", // Código de barras
                    "SN123gy4se5", // Número de serie
                    true, // Estado activo
                    tipoBien3, // Tipo de bien
                    responsable, // Usuario responsable
                    modelo3, // Modelo
                    marca3, // Marca
                    lugar1, // Lugar
                    null, // Bajas (puedes dejarlo vacío si no hay bajas asociadas)
                    new Date() // Fecha de creación
            );

            bienRepository.saveAndFlush(nuevoBien3);

        };
    }
}
