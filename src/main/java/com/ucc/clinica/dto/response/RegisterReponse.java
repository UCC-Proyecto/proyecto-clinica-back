package com.ucc.clinica.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterReponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
