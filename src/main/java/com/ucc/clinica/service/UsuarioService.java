package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.RegisterRequest;
import com.ucc.clinica.dto.response.RegisterReponse;

public interface UsuarioService {
    RegisterReponse registrarUsuario(RegisterRequest registerRequest);
}
