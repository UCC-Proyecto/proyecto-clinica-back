package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.DisponibilidadRequest;
import com.ucc.clinica.dto.response.DisponibilidadResponse;
import com.ucc.clinica.entity.EstadoDisponibilidad;

import java.util.List;

public interface DisponibilidadService {
    DisponibilidadResponse registrar(DisponibilidadRequest request);
    List<DisponibilidadResponse> consultarDisponibilidades();
}
