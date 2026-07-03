package com.ucc.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadRequest {
    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    private String nombre;
    @NotBlank(message = "La descripción de la especialidad es obligatoria")
    private String descripcion;
}
