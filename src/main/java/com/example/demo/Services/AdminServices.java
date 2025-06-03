package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Admin;

public interface AdminServices {

	List<Admin> buscarTodo();
	
	Admin buscarPorId(Integer id);
	
	void guardar(Admin admin);
	
	void eliminar(Integer id);
	
	void actualizar(Admin admin);
}
