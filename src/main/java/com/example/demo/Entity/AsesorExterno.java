package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "asesorex")
@Getter
@Setter
public class AsesorExterno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAsesorEx;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="cargo")
	private String cargo;
	
    @ManyToOne
    @JoinColumn(name = "idempresa", nullable = false)
	private Empresa empresa;

    @Override
    public String toString() {
        return "AsesorExterno{" +
                "idAsesorEx=" + idAsesorEx +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", empresaId=" + (empresa != null ? empresa.getId_empresa() : null) +
                '}';
    }
}