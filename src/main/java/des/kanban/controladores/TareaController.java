package des.kanban.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import des.kanban.dtos.TareaDTO;
import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.ProyectoRepository;
import des.kanban.modelo.UsuarioRepository;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class TareaController {

	@Autowired
	TareaServicio tareaService;
	@Autowired
	UsuarioServicio usuarioServicio;
	@Autowired
	UsuarioRepository usuarioJPA;
	@Autowired
	ProyectoRepository proyectoJPA;

//	@ResponseBody
//	@RequestMapping(value = "/tarea/crear/{idProyecto}", method = RequestMethod.POST)
//	public ResponseEntity<Tarea> crearTarea (@PathVariable("idProyecto") Long idProyecto, Tarea tarea, @RequestBody Map<String, Usuario> json) {
//		Tarea crearTarea = tareaJPA.crearTarea(idProyecto, tarea, json.get("usuario").getNombreUsuario());
//		ResponseEntity<Tarea> resp = new ResponseEntity<Tarea>(crearTarea, HttpStatus.OK);
//		return resp;
//	}

	@ResponseBody
	@RequestMapping(value = "/tarea/crear/{idProyecto}", method = RequestMethod.POST)
	public ResponseEntity<TareaDTO> crearTarea(@PathVariable("idProyecto") Long idProyecto,
			@RequestBody Map<String, Object> json) {

		Tarea tarea = new Tarea();

		tarea.setTitulo((String) json.get("titulo"));
		tarea.setPrioridad((String) json.get("prioridad"));
		tarea.setDescripcion((String) json.get("descripcion"));

		Tarea tareaCreada = tareaService.crearTarea(idProyecto, tarea);

		TareaDTO dto = new TareaDTO(tareaCreada.getIdTarea(), tareaCreada.getTitulo(), tareaCreada.getDescripcion(),
				tareaCreada.getPrioridad(), tareaCreada.getEstado(), tareaCreada.getProyecto().getIdProyecto(),
				tareaCreada.getUsuario().getIdUsuario(), tareaCreada.getUsuario().getNombreUsuario());

		// Tarea crearTarea = tareaService.crearTarea(idProyecto, tarea);
		ResponseEntity<TareaDTO> resp = new ResponseEntity<TareaDTO>(dto, HttpStatus.OK);
		return resp;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/tareas/{idProyecto}")
	public List<TareaDTO> obtenerAllTareas(@PathVariable("idProyecto") long idProyecto) {
		List<Tarea> tareasEncontradas = tareaService.findAllTareasByid(idProyecto);
		List<TareaDTO> tareaDto = new ArrayList<TareaDTO>();

		for (Tarea t : tareasEncontradas) {
			TareaDTO dto = new TareaDTO(t.getIdTarea(), t.getTitulo(), t.getDescripcion(), t.getPrioridad(),
					t.getEstado(), t.getProyecto().getIdProyecto(), t.getUsuario().getIdUsuario(),
					t.getUsuario().getNombreUsuario());
			tareaDto.add(dto);
		}
		return tareaDto;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/editar/tarea{idTarea}")
	public ResponseEntity<TareaDTO> editarTarea(@PathVariable("idTarea") long idTarea,
			@RequestBody Map<String, Object> json) {
		Tarea tareaModified = new Tarea();
		tareaModified.setTitulo((String) json.get("titulo"));
		tareaModified.setPrioridad((String) json.get("prioridad"));
		tareaModified.setDescripcion((String) json.get("descripcion"));

		ArrayList<Object> id = (ArrayList<Object>) json.get("usuario");
		long idUsuario = Long.valueOf((String) id.get(0));
		
		Usuario u = usuarioServicio.FindPorId(idUsuario);
		tareaModified.setUsuario(u);

		Tarea tareaSave = tareaService.modificarTarea(tareaModified, idTarea);
		
		TareaDTO dto = new TareaDTO(tareaSave.getIdTarea(), tareaSave.getTitulo(), tareaSave.getDescripcion(),
				tareaSave.getPrioridad(), tareaSave.getEstado(), tareaSave.getProyecto().getIdProyecto(),
				tareaModified.getUsuario().getIdUsuario(), tareaSave.getUsuario().getNombreUsuario());

		ResponseEntity<TareaDTO> resp = new ResponseEntity<TareaDTO>(dto, HttpStatus.OK);
		return resp;
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/editEstado/tarea{idTarea}")
	public ResponseEntity<Tarea> editarEstado(@PathVariable("idTarea") long idTarea,
			@RequestBody Map<String, Object> json, Tarea tarea) {
		

		tarea.setEstado((String) json.get("estado"));

		Tarea tareaSave = tareaService.editarEstado(tarea, idTarea);

		ResponseEntity<Tarea> resp = new ResponseEntity<Tarea>(tareaSave, HttpStatus.OK);
		return resp;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/borrar/tarea{idTarea}")
	public void getBorrarIdProducto(@PathVariable("idTarea") Long id) {
		Tarea tareaBuscada = new Tarea();
		tareaService.borrarTarea(tareaBuscada, id);
	}

//	@ResponseBody
//	@RequestMapping(method = RequestMethod.GET, value = "/tareas/{idProyecto}")
//	public List<Tarea> obtenerAllTareas(@PathVariable("idProyecto") long idProyecto) {
//		List<Tarea> tareasEncontradas = tareaService.findAllTareasByid(idProyecto);
//		return tareasEncontradas;
//	}
}
