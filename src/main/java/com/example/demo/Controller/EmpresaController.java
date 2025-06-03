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
import com.example.demo.Entity.AsesorExterno;
import com.example.demo.Entity.Empresa;
import com.example.demo.Services.EmpresaServices;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaServices empreSer;
	
	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<Empresa>List = empreSer.buscarTodo();
		model.addAttribute("e",List);
		return "List/listEmpresa";
	}

	@GetMapping("/crear")
	public String crear(AsesorExterno asesorex, Model model) {
		 model.addAttribute("e", empreSer.buscarTodo());
		return "Form/FormEmpresa";
	}
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Empresa eliminada con el ID: " + id);
        empreSer.eliminar(id);
        return "redirect:/empresa/index";
    } 
	
	@PostMapping("/guardar")
	public String guardar(Empresa empresa, Model model) {
		if (empresa == null) {
			// Si hay errores, regresa al formulario y muestra los mensajes de error
			return "empresa/fromEmpresa"; // Retorna la vista del formulario
		}
		empreSer.guardar(empresa);
		System.out.println("El usuario es: " + empresa);
		return "redirect:/empresa/index";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Empresa empresa = empreSer.buscarPorId(id);
		if (empresa != null) {
			model.addAttribute("e", empresa);
			return "Form/FormEmpresa"; // Nombre de la vista donde se editará el producto
		} else {
			return "redirect:/empresa/index"; // Si no existe, redirige a la lista
		}
	}
}
