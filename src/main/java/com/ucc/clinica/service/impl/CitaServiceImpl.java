package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.AgendarCitaRequest;
import com.ucc.clinica.dto.response.AgendarCitaResponse;
import com.ucc.clinica.dto.response.HistorialCitaResponse;
import com.ucc.clinica.entity.*;
import com.ucc.clinica.exception.DisponibilidadNoEncontradaException;
import com.ucc.clinica.exception.HorarioNoDisponibleException;
import com.ucc.clinica.exception.PacienteNoEncontradoException;
import com.ucc.clinica.repository.CitaRepository;
import com.ucc.clinica.repository.DisponibilidadRepository;
import com.ucc.clinica.repository.UsuarioRepository;
import com.ucc.clinica.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {
    private final CitaRepository citaRepository;
    private final UsuarioRepository usuarioRepository;
    private final DisponibilidadRepository disponibilidadRepository;

    @Override
    public AgendarCitaResponse agendarCita(AgendarCitaRequest request) {

        Usuario paciente = usuarioRepository.findById(
                request.getPacienteId())
                .orElseThrow(()-> new PacienteNoEncontradoException("Paciente no encontrado"));

        Disponibilidad disponibilidad = disponibilidadRepository.findById(
                request.getDisponibilidadId())
                .orElseThrow(()-> new DisponibilidadNoEncontradaException("Disponibilidad no encontrada"));

        if(disponibilidad.getEstado() != EstadoDisponibilidad.DISPONIBLE){
            throw new HorarioNoDisponibleException("El horario seleccionado no está disponible");
        }

        disponibilidad.setEstado(EstadoDisponibilidad.OCUPADO);

        disponibilidadRepository.save(disponibilidad);

        Cita cita = Cita.builder()
                .estadoCita(EstadoCita.AGENDADA)
                .usuario(paciente)
                .disponibilidad(disponibilidad)
                .build();

        cita = citaRepository.save(cita);

        return AgendarCitaResponse.builder()
                .id(cita.getId())
                .paciente(paciente.getNombre() + " " + paciente.getApellido())
                .medico(disponibilidad.getMedico().getNombre() + " " + disponibilidad.getMedico().getApellido())
                .fecha(disponibilidad.getFecha())
                .hora(disponibilidad.getHora())
                .estadoCita(cita.getEstadoCita().name())
                .build();
    }

    @Override
    public List<HistorialCitaResponse> listarPorPaciente(Long pacienteId) {

        List<Cita> citas = citaRepository.findByUsuario_Id(pacienteId);
        return citas.stream().map(
                cita -> HistorialCitaResponse.builder()
                        .id(cita.getId())
                        .medico(cita.getDisponibilidad().getMedico().getNombre() + " " + cita.getDisponibilidad().getMedico().getApellido())
                        .fecha(cita.getDisponibilidad().getFecha())
                        .hora(cita.getDisponibilidad().getHora())
                        .estado(cita.getEstadoCita().name())
                        .build()
        ).toList(
        );
    }
}
