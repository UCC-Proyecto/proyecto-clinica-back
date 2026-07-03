package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.MedicoRequest;
import com.ucc.clinica.dto.response.MedicoResponse;
import com.ucc.clinica.entity.Especialidad;
import com.ucc.clinica.entity.Medico;
import com.ucc.clinica.exception.DatoObligatorioException;
import com.ucc.clinica.exception.EspecialidadNoEncontradaException;
import com.ucc.clinica.repository.EspecialidadRepository;
import com.ucc.clinica.repository.MedicoRepository;
import com.ucc.clinica.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;

    @Override
    public MedicoResponse crearMedico(MedicoRequest medicoRequest) {

        Especialidad especialidad = especialidadRepository.findById(medicoRequest.getEspecialidadId()).orElseThrow(
                () -> new EspecialidadNoEncontradaException("Especialidad con ID " + medicoRequest.getEspecialidadId() + " no encontrada")
        );

        Medico medico = Medico.builder()
                .nombre(medicoRequest.getNombre())
                .apellido(medicoRequest.getApellido())
                .especialidad(especialidad)
                .build();

        medico = medicoRepository.save(medico);

        return MedicoResponse.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .especialidad(
                        medico.getEspecialidad().getNombre()
                )
                .build();
    }
}
