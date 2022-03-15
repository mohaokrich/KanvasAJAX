
package des.kanban.servicios;

import java.util.List;
import des.kanban.entidades.Proyecto;

public interface ProyectoServicio {

	public Proyecto obtenerProyectoPorId(Long idProyecto);
	
	public Proyecto crearProyecto(Proyecto proyecto, Long idTrabajador );
	
	public Boolean  borrarProyecto(Proyecto proyecto, Long IdProyecto);
	
	public Proyecto modificarProyecto(Proyecto proyecto);
	
	public List<Proyecto> listarProyectos();
	
	public List<Proyecto> findProyectosByName(String nombre);

}