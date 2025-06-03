package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Estudiante;
import java.util.List;
import java.util.Optional;


public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{

	Optional<Estudiante> findByNombre(String nombre);
}
