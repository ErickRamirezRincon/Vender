package mx.com.fincomun.vendedores.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.Vendedor;
import mx.com.fincomun.vendedores.bean.request.LoginRequest;
import mx.com.fincomun.vendedores.bean.response.GeneraTokenResponse;
import mx.com.fincomun.vendedores.bean.response.LoginResponse;
import mx.com.fincomun.vendedores.dao.LoginDao;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
//	
//	@Autowired
//	private ValidaTokenService validaTokenService;
	
	private static Logger log = Logger.getLogger(LoginService.class);

	public LoginResponse login(LoginRequest request){
		LoginResponse response = null;
		Vendedor vendedor = loginDao.login(request);
		if(vendedor != null){
			log.info("Vendedor: "+vendedor.toString());
			String numCliente = vendedor.getNumCliente();
			String imei = loginDao.getImei(numCliente);
			
			log.info("IMEI obtenido: ["+imei+"]");
			
//			GeneraTokenResponse generaTokenResponse = validaTokenService.getToken(request);
//			if(generaTokenResponse != null){
				response = new LoginResponse();
				response.setNombre(vendedor.getNombre());
				response.setId(vendedor.getId());
				response.setIdTendero(vendedor.getUsuarioId());
				response.setNumCliente(vendedor.getNumCliente());
//				response.setTokenJwt(generaTokenResponse.getToken());
				response.setNumCuenta(vendedor.getCuentaId());
				response.setTelefono(vendedor.getTelefono());
				response.setImei(imei);
//			}
		}
		return response;
	}
}
