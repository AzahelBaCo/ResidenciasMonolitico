package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Residencia;
import com.example.demo.Repository.ResidenciaRepository;
import com.example.demo.Services.ResidenciaServices;

@Service
public class ResidenciasServicesImpl implements ResidenciaServices{

	@Autowired
	private ResidenciaRepository reRepo;
	
	@Override
	public List<Residencia> buscarTodo() {
		return reRepo.findAll();
	}

	@Override
	public Residencia buscarPorId(Integer id) {
		Optional<Residencia> optional = reRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Residencia residencia) {
		reRepo.save(residencia);
	}

	@Override
	public void eliminar(Integer id) {
		reRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(Residencia residencia) {
		if (reRepo.existsById(residencia.getId_residencia())) {
			reRepo.save(residencia);
		}
	}

}
