package com.ucc.clinica.repository;

import com.ucc.clinica.entity.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Long> {
}
