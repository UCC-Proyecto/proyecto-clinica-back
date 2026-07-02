package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.DisponibilidadRequest;
import com.ucc.clinica.dto.response.DisponibilidadResponse;
import com.ucc.clinica.entity.Disponibilidad;
import com.ucc.clinica.entity.EstadoDisponibilidad;
import com.ucc.clinica.entity.Medico;
import com.ucc.clinica.repository.DisponibilidadRepository;
import com.ucc.clinica.repository.MedicoRepository;
import com.ucc.clinica.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService {
    private final DisponibilidadRepository disponibilidadRepository;
    private final MedicoRepository medicoRepository;

    @Override
    public DisponibilidadResponse registrar(DisponibilidadRequest request) {
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        Disponibilidad disponibilidad = Disponibilidad.builder()
                .fecha(request.getFecha())
                .hora(request.getHora())
                .estado(
                        EstadoDisponibilidad.DISPONIBLE)
                .medico(medico)
                .build();
        disponibilidad = disponibilidadRepository.save(disponibilidad);
        return DisponibilidadResponse.builder()
                .id(disponibilidad.getId())
                .medico(
                        medico.getNombre() + " " + medico.getApellido())
                .fecha(disponibilidad.getFecha())
                .hora(disponibilidad.getHora())
                .estado(
                        disponibilidad.getEstado().name()
                ).build();
    }
}
