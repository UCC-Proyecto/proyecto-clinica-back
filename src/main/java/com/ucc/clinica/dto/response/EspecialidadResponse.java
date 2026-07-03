package com.ucc.clinica.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EspecialidadResponse {
    private Long id;
    private String nombre;
    private String descripcion;
}
