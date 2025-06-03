package com.example.demo.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AsesorInterno;

@Service
public interface AsesorIntServices {

	List<AsesorInterno> buscarTodo();

	AsesorInterno buscarPorId(Integer id);

	void guardar(AsesorInterno asesorInterno);

	void eliminar(Integer id);

	void actualizar(AsesorInterno asesorInterno);
	
	
	/* 
	List<CLASE> buscarTodo();

	CLASE buscarPorId(Integer id);

	void guardar(CLASE clase);

	void eliminar(Integer id);

	void actualizar(CLASE clase);
*/
}
