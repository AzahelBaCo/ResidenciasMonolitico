package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.AsesorExterno;
import com.example.demo.Entity.AsesorInterno;
import com.example.demo.Entity.Convenio;
import com.example.demo.Entity.Empresa;
import com.example.demo.Entity.Estudiante;
import com.example.demo.Entity.Residencia;
import com.example.demo.Services.AsesorExtSevices;
import com.example.demo.Services.AsesorIntServices;
import com.example.demo.Services.ConvenioServices;
import com.example.demo.Services.EstudianteServices;
import com.example.demo.Services.ResidenciaServices;

@Controller
@RequestMapping(value = "/residencia")
public class ResidenciaController {

	@Autowired
	private ResidenciaServices resser;
	@Autowired
	private EstudianteServices esSer;
	@Autowired
	private ConvenioServices conSer;
	@Autowired
	private AsesorExtSevices asExSer;
	@Autowired
	private AsesorIntServices asInSer;

	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<Residencia> List = resser.buscarTodo();
		model.addAttribute("Re", List);
		return "List/ListResidencia";
	}

	@GetMapping("/crear")
	public String crear(Residencia residencia, Model model) {
		model.addAttribute("r", residencia);
		model.addAttribute("e", esSer.buscarTodo());
		model.addAttribute("c", conSer.buscarTodo());
		model.addAttribute("asex", asExSer.buscarTodo());
		model.addAttribute("asin", asInSer.buscarTodo());
		return "Form/FormResidencias";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		System.out.println("AsesorEx eliminado con el ID: " + id);
		resser.eliminar(id);
		return "redirect:/residencia/index";
	}

	@PostMapping("/guardar")
	public String guardar(Residencia residencia, Model model) {

		// Validar que venga el id
		if (residencia.getEstudiante() == null || residencia.getEstudiante().getId_estudiante() == null) {
			System.out.println("estudiante no selecionado");
			if (residencia.getAsesorInterno() == null || residencia.getAsesorInterno().getId_asesorint() == null) {
				System.out.println("asesor in no selecionado");
				if (residencia.getAsesorExterno() == null || residencia.getAsesorExterno().getIdAsesorEx() == null) {
					System.out.println("asesor ex no selecionado");
					if (residencia.getConvenio() == null || residencia.getConvenio().getId_convenio() == null) {
						System.out.println("convenio no selecionado");
						return "redirect:/residencia/crear";
					}
				}
			}
			// atributos.addFlashAttribute("error", "Debe seleccionar una empresa.");
		}

		//Buscar la empresa por id
		Estudiante estudiante = esSer.buscarPorId(residencia.getEstudiante().getId_estudiante());
		if (estudiante == null) {
			System.out.println("No existe el alumno");
			return "redirect:/residencia/crear";
		}
		AsesorInterno asesori = asInSer.buscarPorId(residencia.getAsesorInterno().getId_asesorint());
		if (asesori == null) {
			System.out.println("no existe el asesor Int");
			return "redirect:/residencia/crear";
		}
		AsesorExterno asesorx = asExSer.buscarPorId(residencia.getAsesorExterno().getIdAsesorEx());
		if (asesorx == null) {
			System.out.println("no existe el asesor Ext");
			return "redirect:/residencia/crear";
		}
		Convenio convenio = conSer.buscarPorId(residencia.getConvenio().getId_convenio());
		if (convenio == null) {
			System.out.println("no existe el convenio");
			return "redirect:/residencia/crear";
		}

		// Asignar la empresa persistida al asesor externo
		residencia.setEstudiante(estudiante);
		residencia.setAsesorInterno(asesori);
		residencia.setAsesorExterno(asesorx);
		residencia.setConvenio(convenio);

		// Guardar el asesor externo
		resser.guardar(residencia);
		// atributos.addFlashAttribute("msg", "Se guardó el usuario correctamente");
		System.out.println("La residencia es: " + residencia);
		return "redirect:/residencia/index";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Residencia residencia = resser.buscarPorId(id);
	    if (residencia == null) {
	        System.out.println("Residencia no encontrada con ID: " + id);
	        return "redirect:/residencia/index";
	    }

	    model.addAttribute("r", residencia);
	    model.addAttribute("e", esSer.buscarTodo());
	    model.addAttribute("c", conSer.buscarTodo());
	    model.addAttribute("asex", asExSer.buscarTodo());
	    model.addAttribute("asin", asInSer.buscarTodo());

	    return "Form/FormResidencias";
	}

}
