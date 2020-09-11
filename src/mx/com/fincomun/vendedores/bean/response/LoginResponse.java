package mx.com.fincomun.vendedores.bean.response;

public class LoginResponse extends Response {
	
	private long id;
	private long idTendero;
	private String numCliente; 
	private String nombre;
	private long numCuenta;
	private long telefono;
	//private String tokenJwt;
	private String imei;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdTendero() {
		return idTendero;
	}
	public void setIdTendero(long idTendero) {
		this.idTendero = idTendero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	public String getTokenJwt() {
//		return tokenJwt;
//	}
//	public void setTokenJwt(String tokenJwt) {
//		this.tokenJwt = tokenJwt;
//	}
	public String getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}
	public long getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(long numCuenta) {
		this.numCuenta = numCuenta;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	@Override
	public String toString() {
		return "LoginResponse [id=" + id + ", idTendero=" + idTendero + ", numCliente=" + numCliente + ", nombre="
				+ nombre + ", numCuenta=" + numCuenta + ", telefono=" + telefono + ", imei=" + imei + ", getCode()="
				+ getCode() + ", getMensaje()=" + getMensaje() + "]";
	}
}