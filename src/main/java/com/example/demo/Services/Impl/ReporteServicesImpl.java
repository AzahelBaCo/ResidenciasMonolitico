package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Reporte;
import com.example.demo.Repository.ReporteRepository;
import com.example.demo.Services.ReporteServices;

@Service
public class ReporteServicesImpl implements ReporteServices{

	@Autowired
	private ReporteRepository reporteRepo;
	
	@Override
	public List<Reporte> buscarTodos() {
		return reporteRepo.findAll();
	}

	@Override
	public Reporte buscarPorId(Integer id) {
		Optional<Reporte> optional = reporteRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public void guardar(Reporte reporte) {
		reporteRepo.save(reporte);
		
	}

	@Override
	public void eliminar(Integer id) {
		reporteRepo.deleteById(id);
		
	}

	@Override
	public void actualizar(Reporte reporte) {
	if(reporteRepo.existsById(reporte.getId_reporte())) {
		reporteRepo.save(reporte);
		}
	}

}
