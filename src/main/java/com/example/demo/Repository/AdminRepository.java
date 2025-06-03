package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Admin;

public interface AdminRepository  extends JpaRepository<Admin, Integer>{
	Optional<Admin> findByNombre(String nombre);
}
