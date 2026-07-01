package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.RegisterRequest;
import com.ucc.clinica.dto.response.RegisterReponse;
import com.ucc.clinica.entity.Rol;
import com.ucc.clinica.entity.Usuario;
import com.ucc.clinica.repository.UsuarioRepository;
import com.ucc.clinica.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public RegisterReponse registrarUsuario(RegisterRequest registerRequest) {
        if(usuarioRepository.existsUsuarioByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("El email ya se encuentra registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(registerRequest.getNombre())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .rol(Rol.PACIENTE)
                .build();

        Usuario save = usuarioRepository.save(usuario);

        return RegisterReponse.builder()
                .id(save.getId())
                .nombre(save.getNombre())
                .apellido(save.getApellido())
                .email(save.getEmail())
                .build();
    }
}
