package com.ucc.clinica.controller;

import com.ucc.clinica.dto.request.EspecialidadRequest;
import com.ucc.clinica.dto.response.EspecialidadResponse;
import com.ucc.clinica.service.EspecialidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    @PostMapping
    public ResponseEntity<EspecialidadResponse> crear(@Valid @RequestBody EspecialidadRequest especialidadRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(especialidadService.crearEspecialidad(especialidadRequest));
    }
}
