package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.LoginRequest;
import com.ucc.clinica.dto.response.LoginResponse;
import com.ucc.clinica.entity.Usuario;
import com.ucc.clinica.exception.CredencialesInvalidasException;
import com.ucc.clinica.repository.UsuarioRepository;
import com.ucc.clinica.security.JwtService;
import com.ucc.clinica.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Usuario usuario = usuarioRepository
                .findUsuarioByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CredencialesInvalidasException(
                        "Credenciales Inválidas"
                ));

        boolean passwordCorrecto = passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword());
        if (!passwordCorrecto) {
            throw new CredencialesInvalidasException(
                    "Credenciales Invalidas"
            );
        }

        String token = jwtService.generateJwtToken(usuario.getEmail());

        return LoginResponse.builder()
                .token(token).build();
    }
}
