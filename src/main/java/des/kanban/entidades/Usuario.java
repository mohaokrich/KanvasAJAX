package des.kanban.entidades;

import java.io.Serializable;


import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@Column(name = "contrasena")
	private String passwdUsuario;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "usuarios")
	private Set<Rol> roles = new HashSet<>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "trabajadores")
	private Set<Proyecto> proyectos = new HashSet<>();
	

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<Tarea> tareas_usuario = new HashSet<>();


	public Usuario() {
		super();
	}

	public Usuario(Long idUsuario, String passwdUsuario, String nombreUsuario, Set<Rol> roles) {
		super();
		this.idUsuario = idUsuario;
		this.passwdUsuario = passwdUsuario;
		this.nombreUsuario = nombreUsuario;
		this.roles = roles;
	}

	//GETTERS Y SETTERS
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPasswdUsuario() {
		return passwdUsuario;
	}

	public void setPasswdUsuario(String passwdUsuario) {
		this.passwdUsuario = passwdUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	// roles---------------------------------------
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public boolean addRol(Rol rol) {
		rol.addUsuario(this);
		return getRoles().add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
		rol.getUsuarios().remove(this);
	}
	
	
	
	
	
	// proyectos---------------------------------------
	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	public boolean addProyecto(Proyecto proyecto) {
		proyecto.addTrabajador(this);
		return getProyectos().add(proyecto);
	}

	public void eliminarProyecto(Proyecto proyecto) {
		this.proyectos.remove(proyecto);
		proyecto.getTrabajadores().remove(this);
	}
	
	
	
	// tareas---------------------------------------
	public Set<Tarea> getTareas_usuario() {
		return tareas_usuario;
	}

	public void setTareas_usuario(Set<Tarea> tareas_usuario) {
		this.tareas_usuario = tareas_usuario;
	}
	
	
	

}
