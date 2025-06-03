package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Convenio;
import com.example.demo.Repository.ConvenioRepository;
import com.example.demo.Services.ConvenioServices;

@Service
public class ConvenioServicesImp implements ConvenioServices{

	@Autowired
	private ConvenioRepository conRepo;
	
	@Override
	public List<Convenio> buscarTodo() {
		return conRepo.findAll();
	}

	@Override
	public Convenio buscarPorId(Integer id) {
		Optional<Convenio> optional = conRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Convenio convenio) {
		conRepo.save(convenio);
		
	}

	@Override
	public void eliminar(Integer id) {
		conRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(Convenio convenio) {
		if (conRepo.existsById(convenio.getId_convenio())) {
			conRepo.save(convenio);
		}
		
	}

}
