package com.ucc.clinica.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AgendarCitaResponse {
    private Long id;
    private String paciente;
    private String medico;
    private LocalDate fecha;
    private LocalTime hora;
    private String  estadoCita;
}
