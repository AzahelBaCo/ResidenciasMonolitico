package com.example.demo.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Identity;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Convenio;
import com.example.demo.Entity.Empresa;
import com.example.demo.Entity.Reporte;
import com.example.demo.Services.ConvenioServices;
import com.example.demo.Services.EmpresaServices;

@Controller
@RequestMapping("/convenio")
public class ConvenioController {

	@Autowired
	private ConvenioServices comser;
	@Autowired
	private EmpresaServices emser;

	@GetMapping("/index")
	public String mostrarLista(Model model) {
		List<Convenio> List = comser.buscarTodo();
		model.addAttribute("a", List);
		return "List/listConvenio";
	}

	@GetMapping("/crear")
	public String crear(Convenio convenio, Model model) {
		model.addAttribute("c",convenio);
		model.addAttribute("e", emser.buscarTodo());
		return "Form/FormConvenio";
	}
	

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		System.out.println("AsesorEx eliminado con el ID: " + id);
		comser.eliminar(id);
		return "redirect:/convenio/index";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Convenio convenio = comser.buscarPorId(id);
		model.addAttribute("e", emser.buscarTodo());
		if (convenio != null) {
			model.addAttribute("c", convenio);
			return "Form/FormConvenio"; // Nombre de la vista donde se editará el producto
		} else {
			return "redirect:/convenio/index"; // Si no existe, redirige a la lista
		}
	}

	@PostMapping("/save")
    public String guardarConvenio(@ModelAttribute("c") Convenio convenio) {
        try {
            // Verifica si es una actualización o un nuevo registro
            boolean esNuevo = (convenio.getId_convenio() == null);
            
            // Si hay un archivo nuevo, procésalo
            if (convenio.getArchivoAdjunto() != null && !convenio.getArchivoAdjunto().isEmpty()) {
                String nombreArchivo = StringUtils.cleanPath(convenio.getArchivoAdjunto().getOriginalFilename());
                Path rutaArchivo = Paths.get("uploads/convenios").resolve(nombreArchivo);
                Files.createDirectories(rutaArchivo.getParent());
                Files.copy(convenio.getArchivoAdjunto().getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
                convenio.setArchivo(nombreArchivo);
            } else if (!esNuevo) {
                // Si es una actualización y no hay archivo nuevo, mantén el archivo existente
                Convenio convenioExistente = comser.buscarPorId(convenio.getId_convenio());
                convenio.setArchivo(convenioExistente.getArchivo());
            }

            comser.guardar(convenio);
            return "redirect:/convenio/index";
            
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
	@GetMapping("/verArchivo/{nombreArchivo:.+}")
	public ResponseEntity<Resource> verArchivo(@PathVariable String nombreArchivo) {
		try {
			// Decodificar el nombre del archivo
			nombreArchivo = URLDecoder.decode(nombreArchivo, StandardCharsets.UTF_8);

			// Construir la ruta al archivo
			Path archivoPath = Paths.get("uploads/convenios").resolve(nombreArchivo).normalize();
			Resource recurso = new UrlResource(archivoPath.toUri());

			if (!recurso.exists() || !recurso.isReadable()) {
				return ResponseEntity.notFound().build();
			}

			// Obtener el tipo de contenido
			String contentType;
			try {
				contentType = Files.probeContentType(archivoPath);
				if (contentType == null) {
					contentType = "application/octet-stream";
				}
			} catch (IOException e) {
				contentType = "application/octet-stream";
			}

			// Construir la respuesta
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
					.body(recurso);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}




//public String guardarConvenio(Convenio convenio, @RequestParam("archivo") MultipartFile archivo,Model model) {
//
//	try {
//		// Guardar el archivo en una carpeta
//		String nombreArchivo = StringUtils.cleanPath(archivo.getOriginalFilename());
//		Path rutaArchivo = Paths.get("uploads/convenios").resolve(nombreArchivo);
//		Files.createDirectories(rutaArchivo.getParent()); // Asegura que la carpeta exista
//		Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
//
//		//Crear y guardar el convenio
//		Convenio con = new Convenio();
//		con.setNombre(convenio.getNombre());
//		con.setFecha(convenio.getFecha());
//		con.setArchivo(nombreArchivo);
//		con.setEmpresa(convenio.getEmpresa());// o setFarchivo si así se llama en tu clase
///*
//		Empresa empresa = new Empresa(); // supondremos que tienes la entidad empresa
//		empresa.setId_empresa(id_empresa);
//		convenio.setEmpresa(empresa);
//*/
//		comser.guardar(con);
//
//		return "redirect:/convenio/index";
//	} catch (IOException e) {
//		e.printStackTrace();
//		return "error"; // puedes redirigir a una página de error si lo prefieres
//	}
//}