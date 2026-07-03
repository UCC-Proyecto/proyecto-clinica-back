package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.EspecialidadRequest;
import com.ucc.clinica.dto.response.EspecialidadResponse;

public interface EspecialidadService {
    EspecialidadResponse crearEspecialidad(EspecialidadRequest especialidadRequest);
}
