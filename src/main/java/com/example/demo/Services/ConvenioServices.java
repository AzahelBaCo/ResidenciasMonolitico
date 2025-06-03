package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Convenio;

public interface ConvenioServices {

	List<Convenio> buscarTodo();

	Convenio buscarPorId(Integer id);

	void guardar(Convenio convenio);

	void eliminar(Integer id);

	void actualizar(Convenio convenio);
}
