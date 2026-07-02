package com.ucc.clinica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "citas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EstadoCita estadoCita;
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Usuario usuario;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "disponibilidad_id")
    private Disponibilidad disponibilidad;
}
