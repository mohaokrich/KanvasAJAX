package des.kanban.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class IndexController {

	@Autowired
	UsuarioServicio userService;
	@Autowired
	ProyectoServicio proyectoService;

	
	@GetMapping("/index")
	public ModelAndView getIndex (Model modelo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		//PASAR LISTA USUARIOS PARA MOSTRARLA EN EL SELECT
		List<Usuario> listaUsuarios = userService.obtenerTodosLosUsuarios();
		modelo.addAttribute("ListaUsuario", listaUsuarios);
		
		List<Proyecto> listarProyectos = proyectoService.listarProyectos();
		modelo.addAttribute("ListaProyectos", listarProyectos);
		
		return mav;
	}

	
	@GetMapping("/login")
	public ModelAndView getLogin () {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		
		return mav;
	}

	
	
}
