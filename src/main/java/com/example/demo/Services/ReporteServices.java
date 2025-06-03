package com.example.demo.Services;

import java.util.List;

import com.example.demo.Entity.Reporte;

public interface ReporteServices{

	List<Reporte> buscarTodos();
	
	Reporte buscarPorId(Integer id);
	
	void guardar(Reporte reporte);
	
	void eliminar(Integer id);
	
	void actualizar(Reporte reporte);
	
	

}
