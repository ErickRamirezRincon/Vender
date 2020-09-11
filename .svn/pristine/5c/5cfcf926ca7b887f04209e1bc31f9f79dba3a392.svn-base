package mx.com.fincomun.vendedores.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.dao.VendedorDao;
import mx.com.fincomun.vendedores.bean.Vendedor;
import mx.com.fincomun.vendedores.bean.request.VendedorActualizarRequest;
import mx.com.fincomun.vendedores.bean.request.VendedorEliminaRequest;
import mx.com.fincomun.vendedores.bean.request.VendedorRequest;
import mx.com.fincomun.vendedores.bean.response.VendedorResponse;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorDao vendedorDao;
	
	private static Logger log = Logger.getLogger(VendedorService.class);
	
	public int altaVendedor(VendedorRequest request){
		int error = 0;
		if(!usuarioExiste(request.getUsuario(), request.getIdUsuario())){
			if(!vendedorDao.insertVendedor(request)){
				error = -2; 
			}
		} else {
			error = -1;
		}
		log.info("Alta Vendedor: ["+error+"]");
		return error;
	}
	
	public boolean actualizaVendedor(VendedorActualizarRequest request) throws Exception {
		boolean isModifica = false;
		isModifica = vendedorDao.actualizaVendedor(request);
		log.info("Modifica Vendedor: ["+isModifica+"]");
		return isModifica;
	}
	
	public boolean eliminaVendedor(VendedorEliminaRequest request) throws Exception {
		boolean isElimina = false;
		isElimina = vendedorDao.deleteVendedor(request);
		log.info("Elimina Vendedor: ["+isElimina+"]");
		return isElimina;
	}
	
	public VendedorResponse listaVendedores(long idUsuario) throws Exception {
		VendedorResponse response = null;
		
		List<Vendedor> vendedores = vendedorDao.listaVendedores(idUsuario);
		if(vendedores != null){
			log.info("Vendedores obtenidos ["+vendedores.size()+"] de Usuario ["+idUsuario+"]");
			response = new VendedorResponse();
			response.setVendedores(vendedores);
		}
		return response;
	}
	
	private boolean usuarioExiste(String usuario, long idUsuario){
		boolean isExiste = true;
		log.info("UsuarioExiste: Usuario["+usuario+"]  IdUsuario["+idUsuario+"]");
		Vendedor vendedor = vendedorDao.getPorUsuario(usuario.toUpperCase());
		if(vendedor == null){
			isExiste = false;
		} else {
			log.info("Vendedor: "+vendedor.toString());
		}
		return isExiste;
	}
	
}
