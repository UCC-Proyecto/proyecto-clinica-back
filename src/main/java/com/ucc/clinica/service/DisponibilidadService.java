package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.DisponibilidadRequest;
import com.ucc.clinica.dto.response.DisponibilidadResponse;

public interface DisponibilidadService {
    DisponibilidadResponse registrar(DisponibilidadRequest request);
}
