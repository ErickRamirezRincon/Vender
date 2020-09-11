package mx.com.fincomun.vendedores.bean.request;

public class ReporteRequest extends Request {
	
	private String fecha;
	private long idVendedor;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public long getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(long idVendedor) {
		this.idVendedor = idVendedor;
	}
	@Override
	public String toString() {
		return "ReporteRequest [fecha=" + fecha + ", idVendedor=" + idVendedor + ", toString()=" + super.toString()
				+ "]";
	}
}