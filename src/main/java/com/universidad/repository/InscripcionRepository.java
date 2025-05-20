package com.universidad.repository;

import com.universidad.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    Optional<Inscripcion> findByEstudianteIdAndMateriaIdAndFechaInscripcion(Long estudianteId, Long materiaId, LocalDate fechaInscripcion);
}
