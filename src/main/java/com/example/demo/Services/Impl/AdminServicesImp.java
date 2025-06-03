package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Services.AdminServices;

@Service
public class AdminServicesImp implements AdminServices {

	@Autowired
	private AdminRepository adRepo;

	@Override
	public List<Admin> buscarTodo() {
		return adRepo.findAll();
	}

	@Override
	public Admin buscarPorId(Integer id) {
		Optional<Admin> optional = adRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Admin admin) {
		adRepo.save(admin);

	}

	@Override
	public void eliminar(Integer id) {
		adRepo.deleteById(id);

	}

	@Override
	public void actualizar(Admin admin) {
		if (adRepo.existsById(admin.getId_admin())) {
			adRepo.save(admin);
		}
	}

}
