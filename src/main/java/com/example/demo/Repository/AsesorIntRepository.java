package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.AsesorInterno;
import java.util.List;
import java.util.Optional;


public interface AsesorIntRepository extends JpaRepository<AsesorInterno, Integer> {
	Optional<AsesorInterno> findByNombre(String nombre);
}
