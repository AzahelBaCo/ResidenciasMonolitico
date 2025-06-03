package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Residencia;

public interface ResidenciaServices {

	List<Residencia> buscarTodo();

	Residencia buscarPorId(Integer id);

	void guardar(Residencia residencia);

	void eliminar(Integer id);

	void actualizar(Residencia residencia);

}
