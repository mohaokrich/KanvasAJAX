package des.kanban.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import des.kanban.entidades.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{
	
	List<Proyecto> findByNombreContains(String nombre);
	
}
