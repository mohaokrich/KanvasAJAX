package des.kanban.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import des.kanban.entidades.Rol;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.RolRepository;
import des.kanban.modelo.UsuarioRepository;


@Transactional
@Service
public class UsuarioServicioImpl implements UsuarioServicio{

	@Autowired
	UsuarioRepository usuarioJPA;
	@Autowired
	RolRepository rolJPA;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario obtenerUsuarioPorId(Long idUsuario) {
		return usuarioJPA.findById(idUsuario).orElse(null);
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		return usuarioJPA.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioJPA.findAll();
	}

	@Override
	public Usuario crearUsuario(Usuario u) {
		Rol rolUser = rolJPA.getById(1L);
		u.addRol(rolUser);
		u.setPasswdUsuario(bCryptPasswordEncoder.encode(u.getPasswdUsuario()));
		return usuarioJPA.save(u);
	}

	@Override
	public Usuario getUsuarioByidTarea(long idTareaUsuario) {
		return usuarioJPA.findAllByTarea(idTareaUsuario);
	}

	@Override
	public Usuario FindPorId(Long idUsuario) {
		return usuarioJPA.getById(idUsuario);
	}

	
}
