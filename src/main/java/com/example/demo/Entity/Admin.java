package com.example.demo.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data // Lombok para generar getters, setters, etc
public class Admin {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_admin;
	
	@Column(name="nombre")
    private String nombre;
	
    @Column(name="contraseña")
    private String contraseña;
}
