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


            Optional<Rol> optionalRol = rolRepository.findByNombre("ROLE_ADMINISTRADOR");
            if (!optionalRol.isPresent()) {
                Rol rol = new Rol("ROLE_ADMINISTRADOR");
                rolRepository.saveAndFlush(rol);

            }
            Optional<Rol> optionalRol2 = rolRepository.findByNombre("ROLE_RESPONSABLE");
            if (!optionalRol2.isPresent()) {
                Rol rol2 = new Rol("ROLE_RESPONSABLE");
                rolRepository.saveAndFlush(rol2);

            }
            Optional<Rol> optionalRol3 = rolRepository.findByNombre("ROLE_BECARIO");
            if (!optionalRol3.isPresent()) {
                Rol rol3 = new Rol("ROLE_BECARIO");
                rolRepository.saveAndFlush(rol3);

            }
            Lugar lugar1 = new Lugar( "Edificio Central", true, null);
            Lugar lugar2 = new Lugar( "Laboratorio de Ciencias", true, null);
            lugarRepository.saveAndFlush(lugar1);
            lugarRepository.saveAndFlush(lugar2);



            Marca marca1 = new Marca( "HP", true, null);
            Marca marca2 = new Marca( "Dell", true, null);
            Marca marca3 = new Marca( "Lenovo", true, null);

            marcaRepository.saveAndFlush(marca1);
            marcaRepository.saveAndFlush(marca2);
            marcaRepository.saveAndFlush(marca3);

            Modelo modelo1 = new Modelo("ss12", true, null, null);
            Modelo modelo2 = new Modelo("sshhi3", true, null, null);
            Modelo modelo3 = new Modelo("as3322", true, null, null);

            modeloRepository.saveAndFlush(modelo1);
            modeloRepository.saveAndFlush(modelo2);
            modeloRepository.saveAndFlush(modelo3);

            TipoBien tipoBien1 = new TipoBien("laptop",  true,null);
            TipoBien tipoBien2 = new TipoBien("Pc",  true,null);
            TipoBien tipoBien3 = new TipoBien("Tablet",  true,null);
            tipoBienRepository.saveAndFlush(tipoBien1);
            tipoBienRepository.saveAndFlush(tipoBien2);
            tipoBienRepository.saveAndFlush(tipoBien3);
        };
    }
}
