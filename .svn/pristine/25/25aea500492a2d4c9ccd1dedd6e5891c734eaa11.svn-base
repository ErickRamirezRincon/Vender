package mx.com.fincomun.vendedores.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.fincomun.vendedores.bean.request.ReporteRequest;
import mx.com.fincomun.vendedores.bean.response.ReporteResponse;
import mx.com.fincomun.vendedores.bean.response.ValidaTokenRespose;
import mx.com.fincomun.vendedores.service.ReporteService;
import mx.com.fincomun.vendedores.service.ValidaTokenService;
import mx.com.fincomun.vendedores.util.Constantes;
import mx.com.fincomun.vendedores.util.StringEncrypt;
import mx.com.fincomun.vendedores.util.Util;

@Controller
public class ReporteController {
	
	@Autowired
	private ReporteService reporteService;
	
//	@Autowired
//	private ValidaTokenService validaTokenService;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/restful/reporte/dia", method = RequestMethod.POST)
	public @ResponseBody String reporteDia(@RequestBody String requestString) {
		ReporteResponse response = null; 
		log.info("ServicioRest [/restful/reporte/dia] .... ");
		
		ReporteRequest request = converJsonToReporte(requestString);
		
//		ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//		
//		if(validaTokenResponse != null){ 
//			if(validaTokenResponse.getCodigo() == 0){
				String error = validateReporteDia(request);
				if(error == null){
					try {
						response = reporteService.getReporte(request);
						if(response != null){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else {
							response = new ReporteResponse();
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTEN_TIPO_PAGO);
						}
					} catch (Exception e) {
						log.error("Error: "+e.getCause());
						response = new ReporteResponse();
						response.setCode(Constantes.CODIGO_ERROR_OPERACION);
						response.setMensaje(Constantes.MSJ_ERROR);
					}
				} else {
					response = new ReporteResponse();
					response.setCode(Constantes.CODIGO_ERROR_REQUEST);
					response.setMensaje(error);
				}
//			} else {
//				response = new ReporteResponse();
//				response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//				response.setMensaje(validaTokenResponse.getMensaje());
//			}
//		} else {
//			response = new ReporteResponse();
//			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//			response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//		}
		return Util.getResponse(response);
	}
	
	private String validateReporteDia(ReporteRequest request){
		String error = null;
		if(request != null){
			log.info("Validando Request Reporte: [ "+request.toString()+" ]");
			if(request.getFecha() == null && request.getFecha().trim().length() == 0){
				error = "Fecha es vacia";
			} else if(request.getIdVendedor() == 0){
				error = "IdVendedor en 0";
			}
		} 
		return error;
	}
	
	private ReporteRequest converJsonToReporte(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		ReporteRequest ventaRequest = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String request = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			ventaRequest = mapper.readValue(request, ReporteRequest.class);
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return ventaRequest;
	}

}
