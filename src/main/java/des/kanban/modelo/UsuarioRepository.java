package des.kanban.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.nombreUsuario = :nombreUsuario")
	Long findByNombreAlternative(@Param("nombreUsuario") String nombreUsuario);
	
	Usuario findByNombreUsuario(String nombreUsuario);
	
	@Query("from Usuario u INNER JOIN Tarea  where u.idUsuario LIKE :usuario")
	Usuario findAllByTarea(Long usuario);
}
