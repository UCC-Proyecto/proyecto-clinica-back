package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.DisponibilidadRequest;
import com.ucc.clinica.dto.response.DisponibilidadResponse;
import com.ucc.clinica.entity.Disponibilidad;
import com.ucc.clinica.entity.EstadoDisponibilidad;
import com.ucc.clinica.entity.Medico;
import com.ucc.clinica.exception.MedicoNoEncontradoException;
import com.ucc.clinica.repository.DisponibilidadRepository;
import com.ucc.clinica.repository.MedicoRepository;
import com.ucc.clinica.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService {
    private final DisponibilidadRepository disponibilidadRepository;
    private final MedicoRepository medicoRepository;

    @Override
    public DisponibilidadResponse registrar(DisponibilidadRequest request) {
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new MedicoNoEncontradoException("Medico con id " + request.getMedicoId() + " no encontrado"));
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

    @Override
    public List<DisponibilidadResponse> consultarDisponibilidades() {

        List<Disponibilidad> disponibilidades = disponibilidadRepository.findByEstado(EstadoDisponibilidad.DISPONIBLE);

        return disponibilidades.stream()
                .map(disponibilidad ->
                        DisponibilidadResponse.builder()
                                .id(disponibilidad.getId())
                                .medico(disponibilidad.getMedico().getNombre() + " " + disponibilidad.getMedico().getApellido())
                                .fecha(disponibilidad.getFecha())
                                .hora(disponibilidad.getHora())
                                .estado(disponibilidad.getEstado().name())
                                .build()
                ).toList();
    }
}
