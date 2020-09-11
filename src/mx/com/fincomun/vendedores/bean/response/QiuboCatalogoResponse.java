package mx.com.fincomun.vendedores.bean.response;

import java.util.ArrayList;

public class QiuboCatalogoResponse extends Response {
	
	private ArrayList<CatalogoProductos> productList = new ArrayList<CatalogoProductos>();

	public ArrayList<CatalogoProductos> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<CatalogoProductos> productList) {
		this.productList = productList;
	}
}