package mx.com.fincomun.vendedores.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.fincomun.vendedores.bean.response.QiuboCatalogoResponse;
import mx.com.fincomun.vendedores.service.CatalogoService;
import mx.com.fincomun.vendedores.util.Constantes;
import mx.com.fincomun.vendedores.util.Util;

@Controller
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	private static Logger log = Logger.getLogger(CatalogoController.class);
	
	@RequestMapping(value = "/restful/catalogo", method = RequestMethod.GET)
	public @ResponseBody String catalogo() {
		QiuboCatalogoResponse response = null;	
		log.info("ServicioRest [/restful/catalogo] .... ");
		
		response = catalogoService.getCatalogo();
		if(response != null){
			response.setCode(Constantes.CODIGO_EXITO);
			response.setMensaje(Constantes.MSJ_EXITO);
		} else {
			response = new QiuboCatalogoResponse();
			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
			response.setMensaje(Constantes.MSJ_ERROR);
		}
		return Util.getResponse(response);
	}

}
