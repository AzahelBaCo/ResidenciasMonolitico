package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Escuela;
import com.example.demo.Repository.EscuelaRepository;
import com.example.demo.Services.EscuelaServices;

@Service
public class EscuelaServicesImp implements EscuelaServices{

	@Autowired
	private EscuelaRepository esRepo;
	
	@Override
	public List<Escuela> buscarTodo() {
		return esRepo.findAll();
	}

	@Override
	public Escuela buscarPorId(Integer id) {
		Optional<Escuela> optional = esRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Escuela escuela) {
	esRepo.save(escuela);
		
	}

	@Override
	public void eliminar(Integer id) {
		esRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(Escuela escuela) {
		if (esRepo.existsById(escuela.getId_escuela())) {
			esRepo.save(escuela);
		}
	}

}
