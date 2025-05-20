package com.universidad.service.impl;

import com.universidad.dto.InscripcionDTO;
import com.universidad.exception.ResourceNotFoundException;
import com.universidad.model.Estudiante;
import com.universidad.model.Inscripcion;
import com.universidad.model.Materia;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.InscripcionRepository;
import com.universidad.repository.MateriaRepository;
import com.universidad.service.IInscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements IInscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final MateriaRepository materiaRepository;

    @Override
    @Transactional
    public InscripcionDTO crearInscripcion(InscripcionDTO dto) {
        Optional<Inscripcion> existente = inscripcionRepository
                .findByEstudianteIdAndMateriaIdAndFechaInscripcion(dto.getEstudianteId(), dto.getMateriaId(), dto.getFechaInscripcion());

        if (existente.isPresent()) {
            throw new IllegalArgumentException("La inscripción ya existe con los mismos datos.");
        }

        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + dto.getEstudianteId()));
        Materia materia = materiaRepository.findById(dto.getMateriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con ID: " + dto.getMateriaId()));

        Inscripcion inscripcion = Inscripcion.builder()
                .estudiante(estudiante)
                .materia(materia)
                .fechaInscripcion(dto.getFechaInscripcion())
                .build();

        Inscripcion guardada = inscripcionRepository.save(inscripcion);
        return mapToDTO(guardada);
    }

    @Override
    public List<InscripcionDTO> listarInscripciones() {
        return inscripcionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InscripcionDTO obtenerInscripcion(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
        return mapToDTO(inscripcion);
    }

    @Override
    public void eliminarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inscripción no encontrada con ID: " + id);
        }
        inscripcionRepository.deleteById(id);
    }

    private InscripcionDTO mapToDTO(Inscripcion inscripcion) {
        return InscripcionDTO.builder()
                .id(inscripcion.getId())
                .estudianteId(inscripcion.getEstudiante().getId())
                .materiaId(inscripcion.getMateria().getId())
                .fechaInscripcion(inscripcion.getFechaInscripcion())
                .build();
    }
}
