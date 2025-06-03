package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Entity.Escuela;
import com.example.demo.Services.EscuelaServices;

@Controller
@RequestMapping(value="/escuela")
public class EscuelaController {

	@Autowired
	private EscuelaServices esSer;
	
	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<Escuela>List = esSer.buscarTodo();
		model.addAttribute("a",List);
		return "List/listescuela";
	}

	@GetMapping("/crear")
	public String crear(Escuela escuela,Model model) {
		 model.addAttribute("a", escuela);
		return "Form/FormEscuela";
	}
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("AsesorEx eliminado con el ID: " + id);
        esSer.eliminar(id);
        return "redirect:/escuela/index";
    } 
	//metodo para guardar
  	@PostMapping("/guardar")
      public String guardar(Escuela escuela, Model model) {
          if (escuela == null) {
              // Si hay errores, regresa al formulario y muestra los mensajes de error
              return "usuario/fromUsuario"; // Retorna la vista del formulario
          }
          esSer.guardar(escuela);
          System.out.println("El usuario es: " + escuela);
          return "redirect:/escuela/index";
      }
  	
  	@GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Escuela escuela = esSer.buscarPorId(id);
        if (escuela != null) {
            model.addAttribute("a", escuela);
            return "Form/FormEscuela"; // Nombre de la vista donde se editará el producto
        } else {
            return "redirect:/escuela/index"; // Si no existe, redirige a la lista
        }
    }
	
}
