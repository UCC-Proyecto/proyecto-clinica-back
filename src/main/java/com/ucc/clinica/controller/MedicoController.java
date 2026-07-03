package com.ucc.clinica.controller;

import com.ucc.clinica.dto.request.MedicoRequest;
import com.ucc.clinica.dto.response.MedicoResponse;
import com.ucc.clinica.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoResponse> crearMedico(@Valid @RequestBody MedicoRequest medicoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicoService.crearMedico(medicoRequest));
    }
}
