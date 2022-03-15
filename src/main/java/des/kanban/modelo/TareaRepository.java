package des.kanban.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
	
	@Query ("from Tarea where id_proyecto LIKE :id_proyecto")
	List<Tarea> findAllByProyecto(Long id_proyecto);
	
	@Query ("from Usuario where id_usuario LIKE :id_usuario")
	List<Tarea> findAllByUsuario(Long id_usuario);
}
