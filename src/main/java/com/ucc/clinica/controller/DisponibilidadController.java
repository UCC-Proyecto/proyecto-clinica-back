package com.ucc.clinica.controller;

import com.ucc.clinica.dto.request.DisponibilidadRequest;
import com.ucc.clinica.dto.response.DisponibilidadResponse;
import com.ucc.clinica.service.DisponibilidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    @PostMapping
    public ResponseEntity<DisponibilidadResponse> registrar(@Valid @RequestBody DisponibilidadRequest disponibilidadrequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadService.registrar(disponibilidadrequest));
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadResponse>> consultarDisponibles() {
        return ResponseEntity.ok(
                disponibilidadService.consultarDisponibilidades()
        );
    }
}
