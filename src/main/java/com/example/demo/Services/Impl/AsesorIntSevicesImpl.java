package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AsesorInterno;
import com.example.demo.Repository.AsesorIntRepository;
import com.example.demo.Services.AsesorIntServices;

@Service
public class AsesorIntSevicesImpl implements AsesorIntServices {

	@Autowired
	private AsesorIntRepository inRepo;

	@Override
	public List<AsesorInterno> buscarTodo() {
		return inRepo.findAll();
	}

	@Override
	public AsesorInterno buscarPorId(Integer id) {
		Optional<AsesorInterno> optional = inRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(AsesorInterno asesorInterno) {
		inRepo.save(asesorInterno);

	}

	@Override
	public void eliminar(Integer id) {
		inRepo.deleteById(id);
	}

	@Override
	public void actualizar(AsesorInterno asesorInterno) {
		if (inRepo.existsById(asesorInterno.getId_asesorint())) {
			inRepo.save(asesorInterno);
		}
	}

}
