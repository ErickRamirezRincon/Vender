package mx.com.fincomun.vendedores.bean.response;

public class ValidaTokenRespose {
	
	private int codigo;
	private String mensaje;
	
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
	@Override
	public String toString() {
		return "ValidaTokenRespose [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}
}