package des.kanban.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.ProyectoRepository;
import des.kanban.modelo.UsuarioRepository;

@Transactional
@Service
public class ProyectoServicioImpl implements ProyectoServicio{

	
	@Autowired
	ProyectoRepository proyectoJPA;
	@Autowired
	UsuarioRepository trabajadorJPA;
	
	@Override
	public Proyecto obtenerProyectoPorId(Long idProyecto) {
		return proyectoJPA.getById(idProyecto);
	}

//	@Override
//	public Proyecto crearProyecto(Proyecto proyecto) {
//		Usuario u = new Usuario(); 
//		
//	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Usuario authUser = trabajadorJPA.findByNombreUsuario(userDetails.getUsername());
//        u = trabajadorJPA.findById(authUser.getIdUsuario()).orElse(u);
//
//        u.addProyecto(proyecto);
//        proyecto.addTrabajador(u);
//        return proyectoJPA.save(proyecto);
//	}
//	
	@Override
	public Proyecto crearProyecto(Proyecto proyecto, Long idTrabajador) {
		//buscamos el trabajador por nombre
		Usuario t = new Usuario();
		t = trabajadorJPA.findById(idTrabajador).orElse(null);
		//añadimos el proyecto al trabajador
		t.addProyecto(proyecto);
        return proyectoJPA.save(proyecto);
	}

	@Override
	public Boolean borrarProyecto(Proyecto proyecto, Long idProyecto) {
		proyecto = proyectoJPA.findById(idProyecto).orElse(null);
		try {
			proyectoJPA.delete(proyecto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		

	@Override
	public Proyecto modificarProyecto(Proyecto proyecto) {

		Proyecto obtenerProyecto = proyectoJPA.getById(proyecto.getIdProyecto());
		
		Proyecto editarProyecto = proyectoJPA.save(obtenerProyecto);
		
		return editarProyecto;
	}

	@Override
	public List<Proyecto> listarProyectos() {
		return proyectoJPA.findAll();
	}

	@Override
	public List<Proyecto> findProyectosByName(String nombre) {
		List<Proyecto> proyectosEncontrados = proyectoJPA.findByNombreContains(nombre);
		return proyectosEncontrados;
	}
	
}
