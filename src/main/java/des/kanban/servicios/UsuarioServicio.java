package des.kanban.servicios;

import java.util.List;

import des.kanban.entidades.Usuario;

public interface UsuarioServicio {
	public Usuario FindPorId(Long idUsuario);
	
	public Usuario obtenerUsuarioPorId(Long idUsuario);
	
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario);
	
	public List<Usuario> obtenerTodosLosUsuarios();
	
	public Usuario crearUsuario(Usuario usuario);
	
	public Usuario getUsuarioByidTarea(long idTareaUsuario);
	
	
}
