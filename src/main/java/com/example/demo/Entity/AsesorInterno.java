package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asesorint")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsesorInterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asesorint;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name="apellidop")
    private String apellidoP;
    
    @Column(name="apellidom")
    private String apellidoM;
    
    @Column(name="contraseña")
    private String contraseña;

    @Column(name = "cargo")
    private String cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escuela", nullable = false)
    private Escuela escuela;

    @Override
    public String toString() {
        return "AsesorInterno{" +
                "id_asesorint=" + id_asesorint +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", escuela_id=" + (escuela != null ? escuela.getId_escuela() : null) +
                '}';
    }
}
