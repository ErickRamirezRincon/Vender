package mx.com.fincomun.vendedores.bean.request;

public class QiuboServiciosRequest {
	
	private String accountNumber;
	private String accountNumber1;
	private String accountNumber2;
	private String accountNumber3;
	private String amount;
	private String vendorReference;
	private String verificationDigit;
	private String tipoServicio;
	private String numCtaSibamex;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVendorReference() {
		return vendorReference;
	}
	public void setVendorReference(String vendorReference) {
		this.vendorReference = vendorReference;
	}
	public String getVerificationDigit() {
		return verificationDigit;
	}
	public void setVerificationDigit(String verificationDigit) {
		this.verificationDigit = verificationDigit;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getNumCtaSibamex() {
		return numCtaSibamex;
	}
	public void setNumCtaSibamex(String numCtaSibamex) {
		this.numCtaSibamex = numCtaSibamex;
	}
	@Override
	public String toString() {
		return "QiuboServiciosRequest [accountNumber=" + accountNumber + ", accountNumber1=" + accountNumber1
				+ ", accountNumber2=" + accountNumber2 + ", accountNumber3=" + accountNumber3 + ", amount=" + amount
				+ ", vendorReference=" + vendorReference + ", verificationDigit=" + verificationDigit
				+ ", tipoServicio=" + tipoServicio + ", numCtaSibamex=" + numCtaSibamex + "]";
	}
}
