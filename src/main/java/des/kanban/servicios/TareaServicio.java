package des.kanban.servicios;

import java.util.List;

import des.kanban.entidades.Tarea;

public interface TareaServicio {

	public Tarea obtenerTareaPorId(Long idTarea);
	
	public Tarea crearTarea(Long idProyecto,Tarea tarea);
	
	public Boolean  borrarTarea(Tarea tarea, Long idTarea);
	
	public Tarea modificarTarea(Tarea tarea, Long idTarea);
	
	public List<Tarea> findAllTareasByid(Long idProyecto);
	
	public Tarea editarEstado(Tarea t,Long idTarea);
	
}
