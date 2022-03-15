
package des.kanban.entidades;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name = "rol")
public class Rol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long idRol;
	
	@Column(name = "nombre_rol")
	private String nombreRol;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usuario_rol", 
	joinColumns = @JoinColumn(name = "id_rol"), 
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<Usuario> usuarios = new HashSet<>();

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.getRoles().add(this);
	}
	public void eliminarUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
	}
	
}

