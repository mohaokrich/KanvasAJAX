package des.kanban.controladores;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class ProyectoController {
	
	@Autowired
	ProyectoServicio proyectoService;
	@Autowired
	UsuarioServicio userService;
	@Autowired
	TareaServicio tareaService;
	
	@GetMapping("/proyecto/perfil/{id}")
	public ModelAndView getIndex (@PathVariable("id") long id, Model modelo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proyecto/perfilProyecto");
		
		List<Tarea>tareas = tareaService.findAllTareasByid(id);
		modelo.addAttribute("tareas",tareas);
		
		
		List<Usuario> listaUsuarios = userService.obtenerTodosLosUsuarios();
		modelo.addAttribute("ListaUsuario", listaUsuarios);
		
		//PASAR LISTA USUARIOS PARA MOSTRARLA EN EL SELECT
//		Usuario usuario = userService.getUsuarioByidTarea(id);
//		modelo.addAttribute("usuario", usuario);
		
		//OBTENER EL PROYECTO POR ID
		Proyecto proyecto = proyectoService.obtenerProyectoPorId(id);
		modelo.addAttribute("proyecto", proyecto);
        
		return mav;
	}
	@PostMapping("/proyecto/crear")
	public String crearProyecto(Proyecto proyecto, @RequestParam Long idTrabajador) {
		 proyectoService.crearProyecto(proyecto, idTrabajador);
		 
		 long idProyecto = proyecto.getIdProyecto();
		 
		 return "redirect:/proyecto/perfil/"+idProyecto;
	}
	@GetMapping("/proyecto/borrar/{idProyecto}")
	public String borrarProyecto (@PathVariable("idProyecto") long id, Proyecto proyecto) {
		
		proyectoService.borrarProyecto(proyecto, id);
        
		return "redirect:/index";
	}
	@GetMapping("/proyecto/buscar/{nombreProyecto}")
	public String buscarProyecto (Model modelo, @RequestParam String nombreProyecto) {
		List<Proyecto> ListaProyectos = proyectoService.findProyectosByName(nombreProyecto);
		modelo.addAttribute("ListaProyectos", ListaProyectos);
		return "/index";
	}

}
