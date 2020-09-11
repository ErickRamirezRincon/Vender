package mx.com.fincomun.vendedores.bean.response;

public class Response {

	private String code;
	private String mensaje;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public String toString() {
		return "Response [code=" + code + ", mensaje=" + mensaje + "]";
	}
}
