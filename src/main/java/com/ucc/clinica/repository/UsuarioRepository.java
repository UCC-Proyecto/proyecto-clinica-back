package com.ucc.clinica.repository;

import com.ucc.clinica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsUsuarioByEmail(String email);
    Optional<Usuario> findUsuarioByEmail(String email);
}
