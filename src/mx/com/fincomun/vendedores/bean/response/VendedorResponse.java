package mx.com.fincomun.vendedores.bean.response;

import java.util.ArrayList;
import java.util.List;

import mx.com.fincomun.vendedores.bean.Vendedor;

public class VendedorResponse extends Response {
	
	List<Vendedor> vendedores = new ArrayList<Vendedor>();

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	@Override
	public String toString() {
		return "VendedorResponse [vendedores=" + vendedores.size() + ", toString()=" + super.toString() + "]";
	}

}
