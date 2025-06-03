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
import com.example.demo.Entity.Escuela;
import com.example.demo.Services.AdminServices;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminServices adSer;

	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<Admin> List = adSer.buscarTodo();
		model.addAttribute("a", List);
		return "List/ListAdmin";
	}

	@GetMapping("/crear")
	public String crear(Admin admin, Model model) {
		model.addAttribute("a", admin);
		return "Form/FormAdmin";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		System.out.println("Administrador eliminado con el ID: " + id);
		adSer.eliminar(id);
		return "redirect:/admin/index";
	}

	@PostMapping("/guardar")
	public String guardar(Admin admin, Model model) {
		if (admin == null) {
			// Si hay errores, regresa al formulario y muestra los mensajes de error
			return "admin/fromAdmin"; // Retorna la vista del formulario
		}
		adSer.guardar(admin);
		System.out.println("El usuario es: " + admin);
		return "redirect:/admin/index";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Admin admin = adSer.buscarPorId(id);
		if (admin != null) {
			model.addAttribute("a", admin);
			return "Form/FormAdmin"; // Nombre de la vista donde se editará el producto
		} else {
			return "redirect:/admin/index"; // Si no existe, redirige a la lista
		}
	}
}
