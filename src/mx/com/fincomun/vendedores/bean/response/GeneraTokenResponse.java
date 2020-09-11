package mx.com.fincomun.vendedores.bean.response;

public class GeneraTokenResponse {

	private int codigo;
	private String mensaje;
	private String token;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "GeneraTokenResponse [codigo=" + codigo + ", mensaje=" + mensaje + ", token=" + token + "]";
	}
}