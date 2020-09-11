package mx.com.fincomun.vendedores.bean.request;

public class ProductoVenta {
	
	private int idProducto;
	private int numeroProductos;
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getNumeroProductos() {
		return numeroProductos;
	}
	public void setNumeroProductos(int numeroProductos) {
		this.numeroProductos = numeroProductos;
	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", numeroProductos=" + numeroProductos + "]";
	}
}