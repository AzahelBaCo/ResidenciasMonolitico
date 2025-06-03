package com.example.demo.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "escuela")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_escuela;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correo")
    private String correo;

    @OneToMany(mappedBy = "escuela", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AsesorInterno> asesores = new HashSet<>();

    @Override
    public String toString() {
        return "Escuela{" +
                "id_escuela=" + id_escuela +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", asesores=" + (asesores != null ? asesores.size() : 0) +
                '}';
    }
}