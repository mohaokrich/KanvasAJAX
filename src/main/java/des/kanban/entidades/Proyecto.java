
package des.kanban.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proyecto")
	private Long idProyecto;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "proyecto_usuario",
	joinColumns = @JoinColumn(name = "id_proyecto"),
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<Usuario> trabajadores = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Tarea> tareas_proyecto = new HashSet<>();
	

	public Proyecto() {
		super();
	}

	public Proyecto(Long idProyecto, String nombre, String descripcion, Set<Usuario> trabajadores) {
		super();
		this.idProyecto = idProyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.trabajadores = trabajadores;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

	public Set<Usuario> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(Set<Usuario> trabajadores) {
		this.trabajadores = trabajadores;
	}

	
	public void addTrabajador(Usuario usuario) {
		this.trabajadores.add(usuario);
		usuario.getProyectos().add(this);
	}
	public void eliminarTrabajador(Usuario usuario) {
		this.trabajadores.remove(usuario);
	}

	public Set<Tarea> getTareas_proyecto() {
		return tareas_proyecto;
	}

	public void setTareas_proyecto(Set<Tarea> tareas_proyecto) {
		this.tareas_proyecto = tareas_proyecto;
	}
	
	
	
	
	
	

	
	
	
	

}

