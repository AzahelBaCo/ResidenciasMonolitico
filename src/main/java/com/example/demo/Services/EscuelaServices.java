package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Escuela;

public interface EscuelaServices {

	List<Escuela> buscarTodo();

	Escuela buscarPorId(Integer id);

	void guardar(Escuela escuela);

	void eliminar(Integer id);

	void actualizar(Escuela escuela);
}
