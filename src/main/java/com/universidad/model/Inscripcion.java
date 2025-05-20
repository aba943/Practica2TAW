package com.universidad.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"estudiante_id", "materia_id", "fecha_inscripcion"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;
}
