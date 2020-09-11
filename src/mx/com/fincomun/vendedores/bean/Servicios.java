package mx.com.fincomun.vendedores.bean;

public class Servicios {
	
	private long id;
	private String nombre;
	private String codigo;
	private long idTipoServicio;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public long getIdTipoServicio() {
		return idTipoServicio;
	}
	public void setIdTipoServicio(long idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}
	@Override
	public String toString() {
		return "Servicios [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", idTipoServicio="
				+ idTipoServicio + "]";
	}
}
