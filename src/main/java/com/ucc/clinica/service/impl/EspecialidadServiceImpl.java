package com.ucc.clinica.service.impl;

import com.ucc.clinica.dto.request.EspecialidadRequest;
import com.ucc.clinica.dto.response.EspecialidadResponse;
import com.ucc.clinica.entity.Especialidad;
import com.ucc.clinica.exception.EspecialidadDuplicadaException;
import com.ucc.clinica.repository.EspecialidadRepository;
import com.ucc.clinica.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {
    private final EspecialidadRepository especialidadRepository;

    @Override
    public EspecialidadResponse crearEspecialidad(EspecialidadRequest especialidadRequest) {

        if(especialidadRepository.existsByNombre(especialidadRequest.getNombre())){
            throw new EspecialidadDuplicadaException("Ya existe una especialidad con el nombre: " + especialidadRequest.getNombre());
        }

        Especialidad especialidad = Especialidad.builder()
                .nombre(especialidadRequest.getNombre())
                .descripcion(especialidadRequest.getDescripcion())
                .build();

        especialidad = especialidadRepository.save(especialidad);

        return EspecialidadResponse.builder()
                .id(especialidad.getId())
                .nombre(especialidad.getNombre())
                .descripcion(especialidad.getDescripcion())
                .build();
    }
}
