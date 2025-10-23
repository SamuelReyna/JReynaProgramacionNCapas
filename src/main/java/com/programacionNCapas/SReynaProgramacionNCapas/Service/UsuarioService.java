package com.programacionNCapas.SReynaProgramacionNCapas.Service;

import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.UsuarioRepository;
import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Result UpdatePasswords() {
        Result result = new Result();
        try {
            List<UsuarioJPA> usersDB = usuarioRepository.findAll();
            int contador = 0;
            for (UsuarioJPA user : usersDB) {
                String password = user.getPassword();
                if (password.length() < 20) {
                    contador++;
                    user.setPassword(passwordEncoder.encode(password));
                }
            }
            usuarioRepository.saveAll(usersDB);
            result.object = contador;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
