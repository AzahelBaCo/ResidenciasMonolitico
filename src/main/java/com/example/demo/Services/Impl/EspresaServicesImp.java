package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Empresa;
import com.example.demo.Repository.Empresarepository;
import com.example.demo.Services.EmpresaServices;

@Service
public class EspresaServicesImp implements EmpresaServices{

	@Autowired
	private Empresarepository emRepo;
	
	@Override
	public List<Empresa> buscarTodo() {
		return emRepo.findAll();
	}

	@Override
	public Empresa buscarPorId(Integer id) {
		Optional<Empresa> optional = emRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Empresa empresa) {
		emRepo.save(empresa);
		
	}

	@Override
	public void eliminar(Integer id) {
		emRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(Empresa empresa) {
		if (emRepo.existsById(empresa.getId_empresa())) {
			emRepo.save(empresa);
		}
	}

}
