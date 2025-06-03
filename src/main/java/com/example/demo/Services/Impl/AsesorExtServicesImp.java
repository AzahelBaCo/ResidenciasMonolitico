package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AsesorExterno;
import com.example.demo.Repository.AsesorExtRepository;
import com.example.demo.Services.AsesorExtSevices;

@Service
public class AsesorExtServicesImp  implements AsesorExtSevices{

	@Autowired
	private AsesorExtRepository exRepo;
	
	@Override
	public List<AsesorExterno> buscarTodo() {
		return exRepo.findAll();
	}

	@Override
	public AsesorExterno buscarPorId(Integer id) {
	Optional<AsesorExterno> optional = exRepo.findById(id);
	if(optional.isPresent()) {
		return optional.get();
	}
		return null;
	}

	@Override
	public void guardar(AsesorExterno asesorExterno) {
		exRepo.save(asesorExterno);
		
	}

	@Override
	public void eliminar(Integer id) {
		exRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(AsesorExterno asesorExterno) {
		if (exRepo.existsById(asesorExterno.getIdAsesorEx())) {
			exRepo.save(asesorExterno);
		}
		
	}

}
