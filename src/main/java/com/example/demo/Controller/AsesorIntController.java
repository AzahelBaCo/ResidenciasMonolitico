package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.AsesorInterno;
import com.example.demo.Entity.Escuela;
import com.example.demo.Services.AsesorIntServices;
import com.example.demo.Services.EscuelaServices;

@Controller
@RequestMapping(value="/asesorin")
public class AsesorIntController {
	
	@Autowired
	private AsesorIntServices asInSer;
	@Autowired
	private EscuelaServices esSer;

	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<AsesorInterno>List = asInSer.buscarTodo();
		model.addAttribute("a",List);
		return "List/listAsesorInt";
	}
	@GetMapping("/crear")
	public String crear(AsesorInterno asesorin, Model model) {
		model.addAttribute("a", asesorin);
		 model.addAttribute("e",esSer.buscarTodo());
		return "Form/FormAsesorInt";
	}
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("AsesorEx eliminado con el ID: " + id);
        asInSer.eliminar(id);
        return "redirect:/asesorin/index";
    } 
	
	@PostMapping("/guardar")
	public String guardar(AsesorInterno asesorin, Model model) {
	    // Validar que venga el id de empresa
	    if (asesorin.getEscuela() == null || asesorin.getEscuela().getId_escuela() == null) {
	    	System.out.println("eror1");
	        //atributos.addFlashAttribute("error", "Debe seleccionar una empresa.");
	        return "redirect:/asesorin/crear";
	    }

	    // Buscar la empresa por id
	    Escuela escuela = esSer.buscarPorId(asesorin.getEscuela().getId_escuela());
	    if (escuela == null) {
	    	System.out.println("eror2");
	        //atributos.addFlashAttribute("error", "La empresa seleccionada no existe.");
	        return "redirect:/asesorin/crear";
	    }

	    // Asignar la empresa persistida al asesor externo
	    asesorin.setEscuela(escuela);

	    // Guardar el asesor externo
	    asInSer.guardar(asesorin);
	    //atributos.addFlashAttribute("msg", "Se guardó el usuario correctamente");
	    System.out.println("El usuario es: " + asesorin);
	    return "redirect:/asesorin/index";
	}
	
	@GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        AsesorInterno asesor = asInSer.buscarPorId(id);
        List<Escuela> escuelas = esSer.buscarTodo();
        if (asesor != null) {
            model.addAttribute("a", asesor);
            model.addAttribute("e", escuelas);
            return "Form/FormAsesorInt"; // Nombre de la vista donde se editará el producto
        } else {
            return "redirect:/asesorin/index"; // Si no existe, redirige a la lista
        }
    }
	
}
