package mx.com.fincomun.vendedores.bean;

import java.sql.Date;

public class Vendedor {
	
	private long id;
	private String nombre;
	private long telefono;
	private String correo;
	private String usuario;
	private String contrasena;
	private long cuentaId;
	private long usuarioId;
	private Date fechaAlta;
	private Date fechaActualiza;
	private String numCliente;
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
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaActualiza() {
		return fechaActualiza;
	}
	public void setFechaActualiza(Date fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}
	public String getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}
	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo
				+ ", usuario=" + usuario + ", contrasena=" + contrasena + ", cuentaId=" + cuentaId + ", usuarioId="
				+ usuarioId + ", fechaAlta=" + fechaAlta + ", fechaActualiza=" + fechaActualiza + ", numCliente="
				+ numCliente + "]";
	}
	
}
