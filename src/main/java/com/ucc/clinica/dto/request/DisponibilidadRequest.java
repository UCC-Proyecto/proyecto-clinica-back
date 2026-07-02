package com.ucc.clinica.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class DisponibilidadRequest {
    @NotNull
    private Long medicoId;
    @NotNull
    private LocalDate fecha;
    @NotNull
    private LocalTime hora;
}
