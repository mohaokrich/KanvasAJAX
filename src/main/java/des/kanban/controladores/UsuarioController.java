package des.kanban.controladores;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import des.kanban.entidades.Usuario;
import des.kanban.servicios.UsuarioServicio;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioServicio userService;
	
	
	@GetMapping("/alta")
	public ModelAndView getAlta () {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuario/alta");
		return mav;
	}
	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	@PostMapping(value = "/alta")
	public String crearUsuario(Usuario usuario) {
		userService.crearUsuario(usuario);
		return "redirect:/login";
	}
//	@RequestMapping(value = "/user/userInfo{id}", method = RequestMethod.GET)
//	public String getUsuario(Model modelo, @PathVariable long id) {
//		Usuario usuario = userService.obtenerUsuarioPorId(id);
//		modelo.addAttribute("usuario", usuario);
//		return "/user/userInfo";
//	}

	
}
