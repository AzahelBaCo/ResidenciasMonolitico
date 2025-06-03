package com.example.demo.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Estudiante;
import com.example.demo.Services.EstudianteServices;

@Controller
@RequestMapping(value="/estudiante")
public class EstudianteController {

	@Autowired
	private EstudianteServices esSer;
	
	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<Estudiante>List = esSer.buscarTodo();
		model.addAttribute("a",List);
		return "List/listEstudiante";
	}
	
	@GetMapping("/crear")
	public String crear(Estudiante estudiante,Model model) {
		model.addAttribute("e",estudiante);
		return "Form/FormEstudiante";
	}

	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("AsesorEx eliminado con el ID: " + id);
        esSer.eliminar(id);
        return "redirect:/estudiante/index";
    } 
	
	@GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id,Model model) {
        Estudiante estudiante = esSer.buscarPorId(id);
        if (estudiante != null) {
            model.addAttribute("e", estudiante);
            return "Form/FormEstudiante"; // Nombre de la vista donde se editará el producto
        } else {
            return "redirect:/estudiante/index"; // Si no existe, redirige a la lista
        }
    }
	
	@PostMapping("/guardar")
    public String guardar(Estudiante estudiante, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Form/FormEstudiante";
        }
        esSer.guardar(estudiante);
        return "redirect:/estudiante/index";
    }
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
}
