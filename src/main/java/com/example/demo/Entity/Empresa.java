package com.example.demo.Entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empresa;

    @Column(name="nombre")
    private String nombre;

    @Column(name="direccion")
    private String direccion;

    @Column(name="telefono")
    private Integer telefono;

    @OneToMany(mappedBy = "empresa") // o quítalo si no necesitas cascade
    private List<AsesorExterno> asesores;


    @OneToMany(mappedBy = "empresa")
    private List<Convenio> convenios;

    @Override
    public String toString() {
        return "Empresa{" +
                "id_empresa=" + id_empresa +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                // NO incluyas asesores ni convenios aquí
                '}';
    }
}