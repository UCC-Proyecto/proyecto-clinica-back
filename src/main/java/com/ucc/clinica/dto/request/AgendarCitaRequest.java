package com.ucc.clinica.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendarCitaRequest {
    @NotNull
    private Long pacienteId;
    @NotNull
    private Long disponibilidadId;
}
