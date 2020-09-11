package mx.com.fincomun.vendedores.bean.request;

public class VendedorActualizarRequest {
	
	private int id;
	private String nombre;
	private String numero;
	private String correo;
	private String contrasena;
	private long cuentaId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public long getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(long cuentaId) {
		this.cuentaId = cuentaId;
	}
	@Override
	public String toString() {
		return "VendedorActualizarRequest [id=" + id + ", nombre=" + nombre + ", numero=" + numero + ", correo="
				+ correo + ", contrasena=" + contrasena + ", cuentaId=" + cuentaId + "]";
	}
}
