package des.kanban.dtos;


public class TareaDTO {

	private Long idTarea;
	
	private String titulo;
	
	private String descripcion;
	
	private String prioridad;
	
	private String estado;


	private Long  id_proyecto;
	
	
	private Long id_usuario;

	private String nombre_usuario;

	
	public TareaDTO() {
		super();
	}
	


	public TareaDTO(Long idTarea, String titulo, String descripcion, String prioridad, String estado, Long id_proyecto,
			Long id_usuario, String nombre_usuario) {
		super();
		this.idTarea = idTarea;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.estado = estado;
		this.id_proyecto = id_proyecto;
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
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

	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	
	
	
	
}
