package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "estudiante")
@Data // Lombok para generar getters, setters, etc.
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estudiante;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellidop")
    private String apellidoP;
    
    @Column(name="apellidom")
    private String apellidoM;
    
    @Column(name="contraseña")
    private String contraseña;
    
    @Column(name="carrera")
    private String carrera;
    
    @Column(name="semestre")
    private Integer semestre;
    
    @Column(name="nacimiento")
    private Date nacimiento;
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="telefono")
    private Integer telefono;
}