package mx.com.fincomun.vendedores.bean.request;

public class VentaDirectaRequest extends Request {
	
	private float montoTotal;
	private long idTipoPago;
	private long idVendedor;
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public long getIdTipoPago() {
		return idTipoPago;
	}
	public void setIdTipoPago(long idTipoPago) {
		this.idTipoPago = idTipoPago;
	}
	public long getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(long idVendedor) {
		this.idVendedor = idVendedor;
	}
	@Override
	public String toString() {
		return "VentaDirectaRequest [montoTotal=" + montoTotal + ", idTipoPago=" + idTipoPago + ", idVendedor="
				+ idVendedor + ", getIdUsuario()=" + getIdUsuario() + "]";
	}
}
