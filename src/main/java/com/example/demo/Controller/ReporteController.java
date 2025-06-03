package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Reporte;
import com.example.demo.Services.ReporteServices;
import com.example.demo.Services.ResidenciaServices;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
	
	@Autowired
	private ReporteServices reser;
	
	@Autowired
	private ResidenciaServices resiSer;
	
	@GetMapping("/index")
    public String mostrarLista(Model model) {
    	List<Reporte> lista = reser.buscarTodos();
       	model.addAttribute("a", lista);
    	return "List/ListReporte";
    }
	
	@GetMapping("/crear")
    public String crearProducto(Reporte reporte, Model model) {
		model.addAttribute("e", reporte);
		model.addAttribute("r", resiSer.buscarTodo());
    	return "Form/FormReporte";
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("AsesorEx eliminado con el ID: " + id);
        reser.eliminar(id);
        return "redirect:/reporte/index";
    } 
	
	@PostMapping("/guardar")
	public String guardar(Reporte reporte, Model model) {
		if (reporte == null) {
			// Si hay errores, regresa al formulario y muestra los mensajes de error
			return "admin/fromReporte"; // Retorna la vista del formulario
		}
		reser.guardar(reporte);
		System.out.println("El usuario es: " + reporte);
		return "redirect:/reporte/index";
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Reporte reporte = reser.buscarPorId(id);
		model.addAttribute("r", resiSer.buscarTodo());
		if (reporte != null) {
			model.addAttribute("e", reporte);
			return "Form/FormReporte"; // Nombre de la vista donde se editará el producto
		} else {
			return "redirect:/reporte/index"; // Si no existe, redirige a la lista
		}
	}

}
