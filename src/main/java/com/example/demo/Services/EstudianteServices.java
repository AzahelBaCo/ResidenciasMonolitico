package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Estudiante;

public interface EstudianteServices {

	
	List<Estudiante> buscarTodo();

	Estudiante buscarPorId(Integer id);

	void guardar(Estudiante estudiante);

	void eliminar(Integer id);

	void actualizar(Estudiante estudiante);
}
