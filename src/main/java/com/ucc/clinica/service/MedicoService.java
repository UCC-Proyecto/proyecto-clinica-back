package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.MedicoRequest;
import com.ucc.clinica.dto.response.MedicoResponse;

public interface MedicoService {
    MedicoResponse crearMedico(MedicoRequest medicoRequest);
}
