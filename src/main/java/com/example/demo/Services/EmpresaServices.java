package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Empresa;

public interface EmpresaServices {

	List<Empresa> buscarTodo();

	Empresa buscarPorId(Integer id);

	void guardar(Empresa empresa);

	void eliminar(Integer id);

	void actualizar(Empresa empresa);
}
