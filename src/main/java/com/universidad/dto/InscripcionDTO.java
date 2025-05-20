package com.universidad.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionDTO {
    private Long id;
    private Long estudianteId;
    private Long materiaId;
    private LocalDate fechaInscripcion;
}
