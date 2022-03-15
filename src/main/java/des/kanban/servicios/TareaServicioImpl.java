package des.kanban.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.ProyectoRepository;
import des.kanban.modelo.TareaRepository;
import des.kanban.modelo.UsuarioRepository;

@Transactional
@Service
public class TareaServicioImpl implements TareaServicio{
	@Autowired
	TareaRepository tareaJPA;
	@Autowired
	ProyectoRepository proyectoJPA;
	@Autowired
	UsuarioRepository usuarioJPA;;
	
	@Override
	public Tarea obtenerTareaPorId(Long idTarea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tarea crearTarea(Long idProyecto, Tarea tarea) {
		Proyecto proyecto = proyectoJPA.getById(idProyecto);
		tarea.setProyecto(proyecto);
		
		Usuario u = new Usuario(); 
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario authUser = usuarioJPA.findByNombreUsuario(userDetails.getUsername());
		
		u = usuarioJPA.findById(authUser.getIdUsuario()).orElse(u);
		
		tarea.setUsuario(u);
		tarea.setEstado("Preparada");
		return tareaJPA.save(tarea);
	}

	@Override
	public Boolean borrarTarea(Tarea tarea, Long idTarea) {
		tarea = tareaJPA.findById(idTarea).orElse(null);
		try {
			tareaJPA.delete(tarea);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Tarea modificarTarea(Tarea t, Long idTarea) {
		Tarea encontrarporId =  tareaJPA.findById(idTarea).orElse(null);
		if (encontrarporId != null) {
			
			encontrarporId.setTitulo(t.getTitulo());
			encontrarporId.setDescripcion(t.getDescripcion());
			encontrarporId.setPrioridad(t.getPrioridad());
			encontrarporId.setUsuario(t.getUsuario());

			Tarea tareaSalvar = tareaJPA.save(encontrarporId);

			return tareaSalvar;
		}
		return null;
	}

	@Override
	public List<Tarea> findAllTareasByid(Long idProyecto) {
		List<Tarea> allTareas = tareaJPA.findAllByProyecto(idProyecto);
		return allTareas;
	}

	@Override
	public Tarea editarEstado(Tarea t, Long idTarea) {
		Tarea encontrarporId =  tareaJPA.findById(idTarea).orElse(null);
		if (encontrarporId != null) {
			encontrarporId.setEstado(t.getEstado());
			Tarea tareaSalvar= tareaJPA.save(encontrarporId);
			return tareaSalvar;
		}
		return null;
	}
	
}
