package des.kanban.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import des.kanban.entidades.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{ 

}
