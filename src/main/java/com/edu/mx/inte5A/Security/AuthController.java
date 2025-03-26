package com.edu.mx.inte5A.Security;

import com.edu.mx.inte5A.Usuario.Control.UsuarioService;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8081", "http://192.168.0.37:8081", "http://192.168.43.127:8081"})
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Obtener detalles del usuario
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().iterator().next().getAuthority();

            // Aquí necesitas obtener el id_lugar desde la base de datos o el servicio de usuario
            Usuario usuario = usuarioService.findByUsername(authRequest.getUsername()); // Debes implementar este método
            Long idLugar = usuario.getLugar().getIdlugar(); // Asegúrate de que la entidad tenga este campo

            Long idUsuario = usuario.getIdusuario();
            // Generar el token JWT
            String jwt = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

            // Crear la respuesta con el token, rol e id_lugar
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("role", role);
            response.put("id_lugar", idLugar); // Agregar el id_lugar a la respuesta

            response.put("idUsuario", idUsuario);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

}