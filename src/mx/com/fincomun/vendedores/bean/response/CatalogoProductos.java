package mx.com.fincomun.vendedores.bean.response;

import java.util.ArrayList;
import java.util.List;

public class CatalogoProductos {
	
	private String name;
	private String code;
	private String cadDenomination;
	private List<String> denomination = new ArrayList<String>();
	private Comision comision = new Comision();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCadDenomination() {
		return cadDenomination;
	}
	public void setCadDenomination(String cadDenomination) {
		this.cadDenomination = cadDenomination;
	}
	public List<String> getDenomination() {
		return denomination;
	}
	public void setDenomination(List<String> denomination) {
		this.denomination = denomination;
	}
	public Comision getComision() {
		return comision;
	}
	public void setComision(Comision comision) {
		this.comision = comision;
	}
}