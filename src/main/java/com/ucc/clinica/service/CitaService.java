package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.AgendarCitaRequest;
import com.ucc.clinica.dto.response.AgendarCitaResponse;
import com.ucc.clinica.dto.response.HistorialCitaResponse;

import java.util.List;

public interface CitaService {
    AgendarCitaResponse agendarCita(AgendarCitaRequest request);
    List<HistorialCitaResponse> listarPorPaciente(Long pacienteId);
}
