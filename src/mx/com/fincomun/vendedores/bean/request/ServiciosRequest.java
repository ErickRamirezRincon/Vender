package mx.com.fincomun.vendedores.bean.request;

public class ServiciosRequest extends Request {
	
	private String accountNumber;
	private String accountNumber1;
	private String accountNumber2;
	private String accountNumber3;
	private String verificationDigit;
	private String numCuenta;
	private float montoTotal;
	private float comision;
	private String codigoQR;
	private long idTipoPago;
	private long idVendedor;
	private String codigoServicio;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountNumber1() {
		return accountNumber1;
	}
	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}
	public String getAccountNumber2() {
		return accountNumber2;
	}
	public void setAccountNumber2(String accountNumber2) {
		this.accountNumber2 = accountNumber2;
	}
	public String getAccountNumber3() {
		return accountNumber3;
	}
	public void setAccountNumber3(String accountNumber3) {
		this.accountNumber3 = accountNumber3;
	}
	public String getVerificationDigit() {
		return verificationDigit;
	}
	public void setVerificationDigit(String verificationDigit) {
		this.verificationDigit = verificationDigit;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public String getCodigoQR() {
		return codigoQR;
	}
	public void setCodigoQR(String codigoQR) {
		this.codigoQR = codigoQR;
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
	public String getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	@Override
	public String toString() {
		return "ServiciosRequest [accountNumber=" + accountNumber + ", accountNumber1=" + accountNumber1
				+ ", accountNumber2=" + accountNumber2 + ", accountNumber3=" + accountNumber3 + ", verificationDigit="
				+ verificationDigit + ", numCuenta=" + numCuenta + ", montoTotal=" + montoTotal + ", comision="
				+ comision + ", codigoQR=" + codigoQR + ", idTipoPago=" + idTipoPago + ", idVendedor=" + idVendedor
				+ ", codigoServicio=" + codigoServicio + ", toString()="
				+ super.toString() + "]";
	}
}
