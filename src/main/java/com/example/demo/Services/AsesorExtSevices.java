package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.AsesorExterno;

public interface AsesorExtSevices {

	List<AsesorExterno> buscarTodo();
	
	AsesorExterno buscarPorId(Integer id);
	
	void guardar(AsesorExterno asesorExterno);
	
	void eliminar(Integer id);
	
	void actualizar(AsesorExterno asesorExterno);
}
