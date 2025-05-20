package com.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaDTO implements Serializable {

    private Long id;
    private String nombreMateria;
    private String codigoUnico;
    private Integer creditos;

    private Long docenteId;

    private List<Long> prerequisitos;
    private List<Long> esPrerequisitoDe;
}
