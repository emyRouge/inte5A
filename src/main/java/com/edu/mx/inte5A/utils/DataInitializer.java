package com.edu.mx.inte5A.utils;

import com.edu.mx.inte5A.Rol.Model.Rol;
import com.edu.mx.inte5A.Rol.Model.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase( RolRepository rolRepository) {
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

        };
    }
}
