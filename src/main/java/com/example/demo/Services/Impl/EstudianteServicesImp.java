package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Estudiante;
import com.example.demo.Repository.EstudianteRepository;
import com.example.demo.Services.EstudianteServices;

@Service
public class EstudianteServicesImp implements EstudianteServices {

	@Autowired
	private EstudianteRepository esRepo;

	@Override
	public List<Estudiante> buscarTodo() {
		return esRepo.findAll();
	}

	@Override
	public Estudiante buscarPorId(Integer id) {
		Optional<Estudiante> optional = esRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
			return null;
	}

	@Override
	public void guardar(Estudiante estudiante) {
		esRepo.save(estudiante);
		
	}

	@Override
	public void eliminar(Integer id) {
		esRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		if (esRepo.existsById(estudiante.getId_estudiante())) {
			esRepo.save(estudiante);
		}
	}
	
	
}
