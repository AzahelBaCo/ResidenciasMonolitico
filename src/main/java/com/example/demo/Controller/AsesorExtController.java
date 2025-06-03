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
import com.example.demo.Entity.Empresa;
import com.example.demo.Services.AsesorExtSevices;
import com.example.demo.Services.EmpresaServices;

@Controller
@RequestMapping(value="/asesorex")
public class AsesorExtController {
	
	@Autowired
	private AsesorExtSevices asSer;
	@Autowired
	private EmpresaServices emSer;
	
	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<AsesorExterno>List = asSer.buscarTodo();
		model.addAttribute("a",List);
		return "List/listAsesorEx";
	}

	@GetMapping("/crear")
	public String crear(AsesorExterno asesorEx,Model model) {
		model.addAttribute("a", asesorEx);
        model.addAttribute("e", emSer.buscarTodo());
		return "Form/FormAsesorExt";
	}
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("AsesorEx eliminado con el ID: " + id);
        asSer.eliminar(id);
        return "redirect:/asesorex/index";
    } 
	
	
	@PostMapping("/guardar")
	public String guardar(AsesorExterno asesor, Model model) {
		
	    // Validar que venga el id de empresa
	    if (asesor.getEmpresa() == null || asesor.getEmpresa().getId_empresa() == null) {
	    	System.out.println("eror1");
	        //atributos.addFlashAttribute("error", "Debe seleccionar una empresa.");
	        return "redirect:/asesorex/crear";
	    }

	    // Buscar la empresa por id
	    Empresa empresa = emSer.buscarPorId(asesor.getEmpresa().getId_empresa());
	    if (empresa == null) {
	    	System.out.println("eror2");
	        //atributos.addFlashAttribute("error", "La empresa seleccionada no existe.");
	        return "redirect:/asesorex/crear";
	    }

	    // Asignar la empresa persistida al asesor externo
	    asesor.setEmpresa(empresa);

	    // Guardar el asesor externo
	    asSer.guardar(asesor);
	    //atributos.addFlashAttribute("msg", "Se guardó el usuario correctamente");
	    System.out.println("El usuario es: " + asesor);
	    return "redirect:/asesorex/index";
	}
	
	@GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        AsesorExterno asesor = asSer.buscarPorId(id);
        List<Empresa> empresas = emSer.buscarTodo();
        if (asesor != null) {
            model.addAttribute("a", asesor);
            model.addAttribute("e", empresas);
            return "Form/FormAsesorExt"; // Nombre de la vista donde se editará el producto
        } else {
            return "redirect:/asesorex/index"; // Si no existe, redirige a la lista
        }
    }
}

