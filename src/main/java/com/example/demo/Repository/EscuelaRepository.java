package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Escuela;

public interface EscuelaRepository extends JpaRepository<Escuela, Integer> {

}
