package com.universidad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "materia")
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    public Materia(Long id, String nombreMateria, String codigoUnico) {
        this.id = id;
        this.nombreMateria = nombreMateria;
        this.codigoUnico = codigoUnico;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long id;

    @Column(name = "nombre_materia", nullable = false, length = 100)
    private String nombreMateria;

    @Column(name = "codigo_unico", nullable = false, unique = true)
    private String codigoUnico;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @Version
    private Long version;

    @ManyToMany
    @JoinTable(
        name = "materia_prerequisito",
        joinColumns = @JoinColumn(name = "id_materia"),
        inverseJoinColumns = @JoinColumn(name = "id_prerequisito")
    )
    private List<Materia> prerequisitos;

    @ManyToMany(mappedBy = "prerequisitos")
    private List<Materia> esPrerequisitoDe;

    // RELACIÓN CON DOCENTE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Docente docente;

    public boolean formariaCirculo(Long prerequisitoId) {
        return formariaCirculoRecursivo(this.getId(), prerequisitoId, new java.util.HashSet<>());
    }

    private boolean formariaCirculoRecursivo(Long objetivoId, Long actualId, java.util.Set<Long> visitados) {
        if (objetivoId == null || actualId == null) return false;
        if (objetivoId.equals(actualId)) return true;
        if (!visitados.add(actualId)) return false;
        if (this.getPrerequisitos() == null) return false;
        for (Materia prereq : this.getPrerequisitos()) {
            if (prereq != null && prereq.getId() != null && prereq.getId().equals(actualId)) {
                if (prereq.getPrerequisitos() != null) {
                    for (Materia subPrereq : prereq.getPrerequisitos()) {
                        if (formariaCirculoRecursivo(objetivoId, subPrereq.getId(), visitados)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
