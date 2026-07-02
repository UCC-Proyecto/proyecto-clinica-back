package com.ucc.clinica.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class DisponibilidadResponse {
    private Long id;
    private String medico;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
}
