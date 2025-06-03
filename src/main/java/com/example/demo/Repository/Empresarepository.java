package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Empresa;

public interface Empresarepository extends JpaRepository<Empresa, Integer>{

}
