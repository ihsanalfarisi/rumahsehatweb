package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.JWTRequest;
import apap.tugasakhir.rumahsehat.model.JWTResponse;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.security.JWTUtility;
import apap.tugasakhir.rumahsehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class JWTAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private PasienService pasienService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PasienModel pasienBaru) throws Exception {
        pasienBaru.setRole("pasien");
        pasienBaru.setSaldo(0);
        pasienBaru.setAppointment(new ArrayList<>());
        String username = pasienBaru.getUsername();
        String email = pasienBaru.getEmail();
        PasienModel pasienUsername = pasienService.getPasienByUsername(username);
        PasienModel pasienEmail = pasienService.getPasienByEmail(email);
        if (pasienUsername != null || pasienEmail != null) {
            return ResponseEntity.badRequest().body("Username or email already registered.");
        }
        pasienService.addPasien(pasienBaru);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(pasienBaru.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("")
    public String helloWorld() {
        return "Hello World";
    }
}