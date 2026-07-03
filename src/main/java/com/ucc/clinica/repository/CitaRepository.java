package com.ucc.clinica.repository;

import com.ucc.clinica.entity.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
    List<Cita> findByUsuario_Id(Long pacienteId);
}
