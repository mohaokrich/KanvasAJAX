package des.kanban.entidades;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "tarea")
public class Tarea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarea")
	private Long idTarea;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "prioridad")
	private String prioridad;
	
	@Column(name = "estado")
	private String estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proyecto")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Proyecto proyecto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonBackReference
	private Usuario usuario;


	public Tarea() {
		super();
	}

	public Tarea(Long idTarea, String titulo, String descripcion, String prioridad, String estado, Proyecto proyecto,
			Usuario usuario) {
		super();
		this.idTarea = idTarea;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.estado = estado;
		this.proyecto = proyecto;
		this.usuario = usuario;
	}
	
	public Tarea(String titulo, String descripcion, String prioridad) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
	}

	public Long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

//	public Proyecto getProyecto() {
//		return proyecto;
//	}
//
//	public void setProyecto(Proyecto proyecto) {
//		this.proyecto = proyecto;
//	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}
