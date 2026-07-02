package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.AgendarCitaRequest;
import com.ucc.clinica.dto.response.AgendarCitaResponse;

public interface CitaService {
    AgendarCitaResponse agendarCita(AgendarCitaRequest request);
}
