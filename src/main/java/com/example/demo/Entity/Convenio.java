package com.example.demo.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "convenio")
@Data
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_convenio;

    @Column(name="nombre")
    private String nombre;

    @Column(name="archivo")
    private String archivo;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Agregamos esta anotación para el formato de fecha
    private java.util.Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    // Agregamos este campo para manejar el archivo en el formulario
    @Transient  // Esta anotación indica que el campo no se mapea a la base de datos
    private MultipartFile archivoAdjunto;

    // Getters y setters se mantienen gracias a @Data de Lombok
}