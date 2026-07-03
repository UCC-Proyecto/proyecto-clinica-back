package com.ucc.clinica.controller;

import com.ucc.clinica.dto.request.AgendarCitaRequest;
import com.ucc.clinica.dto.response.AgendarCitaResponse;
import com.ucc.clinica.dto.response.HistorialCitaResponse;
import com.ucc.clinica.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {
    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<AgendarCitaResponse> agendar(@Valid @RequestBody AgendarCitaRequest agendarCitaRequest){
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                citaService.agendarCita(agendarCitaRequest)
        );
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<HistorialCitaResponse>> listarPorPaciente(@PathVariable Long pacienteId){
    return ResponseEntity.ok(
            citaService.listarPorPaciente(pacienteId)
    );
    }
}
