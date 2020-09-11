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

import mx.com.fincomun.vendedores.bean.request.VendedorActualizarRequest;
import mx.com.fincomun.vendedores.bean.request.VendedorEliminaRequest;
import mx.com.fincomun.vendedores.bean.request.VendedorRequest;
import mx.com.fincomun.vendedores.bean.response.Response;
import mx.com.fincomun.vendedores.bean.response.VendedorResponse;
import mx.com.fincomun.vendedores.service.VendedorService;
import mx.com.fincomun.vendedores.util.Constantes;
import mx.com.fincomun.vendedores.util.StringEncrypt;
import mx.com.fincomun.vendedores.util.Util;

@Controller
public class VendedorController {
	
//	@Autowired
//	private ValidaTokenService validaTokenService;
	
	@Autowired
	private VendedorService vendedorService;
	
	private static Logger log = Logger.getLogger(VendedorController.class);
	
	@RequestMapping(value = "/restful/vendedor/alta", method = RequestMethod.POST)
	public @ResponseBody String vendedroAlta(@RequestBody String requestString) {
		Response response = new Response();	
		log.info("ServicioRest [/restful/vendedor/alta] .... "+requestString);
		
		try{
			VendedorRequest request = converJsonToRequest(requestString);
			
//			ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//			if(validaTokenResponse != null) { 
//				if(validaTokenResponse.getCodigo() == 0) {
					String error = validateRequest(request);
					if(error == null){
						int isInsert= vendedorService.altaVendedor(request);
						if(isInsert == 0){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else if(isInsert == -1){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_USUARIO);
						} else if(isInsert == -2){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR);
						}
					}else {
						response.setCode(Constantes.CODIGO_ERROR_REQUEST);
						response.setMensaje(error);
					}
//				} else {
//					response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//					response.setMensaje(validaTokenResponse.getMensaje());
//				}
//			} else {
//				response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//				response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//			}
		} catch (Exception e){
			log.error("Error: "+e.getCause());
			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
			response.setMensaje(Constantes.MSJ_ERROR);
		}
		return Util.getResponse(response);
	}
	
	@RequestMapping(value = "/restful/vendedor/actualiza", method = RequestMethod.POST)
	public @ResponseBody String vendedroActualiza(@RequestBody String requestString) {
		Response response = new Response();	
		log.info("ServicioRest [/restful/vendedor/actualiza] .... "+requestString);
		
		try{
			VendedorActualizarRequest request = converJsonToRequestActualiza(requestString);
			
//			ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//			if(validaTokenResponse != null) { 
//				if(validaTokenResponse.getCodigo() == 0) {
					String error = validateRequestActualiza(request);
					if(error == null){
						boolean isUpdate= vendedorService.actualizaVendedor(request);
						if(isUpdate){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else {
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR);
						}
					}else {
						response.setCode(Constantes.CODIGO_ERROR_REQUEST);
						response.setMensaje(error);
					}
//				} else {
//					response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//					response.setMensaje(validaTokenResponse.getMensaje());
//				}
//			} else {
//				response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//				response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//			}
		} catch (Exception e){
			log.error("Error: "+e.getCause());
			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
			response.setMensaje(Constantes.MSJ_ERROR);
		}
		return Util.getResponse(response);
	}
	
	@RequestMapping(value = "/restful/vendedor/elimina", method = RequestMethod.POST)
	public @ResponseBody String vendedroElimina(@RequestBody String requestString) {
		Response response = new Response();	
		log.info("ServicioRest [/restful/vendedor/elimina] .... ");
		
		try{
			VendedorEliminaRequest request = converJsonToRequestElimina(requestString);
			
//			ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());			
//			if(validaTokenResponse != null) { 
//				if(validaTokenResponse.getCodigo() == 0) {
					String error = validateRequestElimina(request);
					if(error == null){
						boolean isDelete= vendedorService.eliminaVendedor(request);
						if(isDelete){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else {
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR);
						}
					}else {
						response.setCode(Constantes.CODIGO_ERROR_REQUEST);
						response.setMensaje(error);
					}
//				} else {
//					response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//					response.setMensaje(validaTokenResponse.getMensaje());
//				}
//			} else {
//				response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//				response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//			}
		} catch (Exception e){
			log.error("Error: "+e.getCause());
			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
			response.setMensaje(Constantes.MSJ_ERROR);
		}
		return Util.getResponse(response);
	}
	
	@RequestMapping(value = "/restful/vendedor/lista", method = RequestMethod.POST)
	public @ResponseBody String listaVendedores(@RequestBody String requestString) {
		VendedorResponse response = null;	
		log.info("ServicioRest [/restful/vendedor/lista] .... ");
		
		try{
			VendedorRequest request = converJsonToRequest(requestString);
			
//			ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//			if(validaTokenResponse != null) { 
//				if(validaTokenResponse.getCodigo() == 0) {
					if(request.getIdUsuario() !=  0){
						response= vendedorService.listaVendedores(request.getIdUsuario());
						if(response != null){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else {
							response = new VendedorResponse();
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTEN_VENDEDORES);
						}
					}else {
						response = new VendedorResponse();
						response.setCode(Constantes.CODIGO_ERROR_REQUEST);
						response.setMensaje("El campo [IdUsuario] es vacio");
					}
//				} else {
//					response = new VendedorResponse();
//					response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//					response.setMensaje(validaTokenResponse.getMensaje());
//				}
//			} else {
//				response = new VendedorResponse();
//				response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//				response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//			}
		} catch (Exception e){
			log.error("Error: "+e.getCause());
			response = new VendedorResponse();
			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
			response.setMensaje(Constantes.MSJ_ERROR);
		}
		return Util.getResponse(response);
	}
	
	private VendedorRequest converJsonToRequest(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		VendedorRequest request = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String requestDecrypt = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			request = mapper.readValue(requestDecrypt, VendedorRequest.class);
			log.info("RequestConvert: ["+request+"]");
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return request;
	}
	
	private VendedorEliminaRequest converJsonToRequestElimina(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		VendedorEliminaRequest request = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String requestDecrypt = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			request = mapper.readValue(requestDecrypt, VendedorEliminaRequest.class);
			log.info("RequestConvert: ["+request+"]");
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return request;
	}
	
	private VendedorActualizarRequest converJsonToRequestActualiza(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		VendedorActualizarRequest request = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String requestDecrypt = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			request = mapper.readValue(requestDecrypt, VendedorActualizarRequest.class);
			log.info("RequestConvert: ["+request+"]");
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return request;
	}
	
	private String validateRequest(VendedorRequest request){
		String error = null;
		if(request!= null){
			log.info("Validando request: "+request.toString());
			if(request.getNombre() ==  null || request.getNombre().trim().length() == 0){
				error = "El campo [Nombre] es vacio";
			} else if(request.getNumero() == null || request.getNumero().trim().length() == 0){
				error = "El campo [Numero Celular] es vacio";
			} else if(request.getCorreo() == null || request.getCorreo().trim().length() == 0){
				error = "El campo [Correo] es vacio";
			} else if(request.getUsuario() == null || request.getUsuario().trim().length() == 0){
				error = "El campo [Usuario] es vacio";
			} else if(request.getContrasena() == null || request.getContrasena().trim().length() == 0){
				error = "El campo [Contrasena] es vacio";
			} else if(request.getCuentaId() == 0){
				error = "El campo [Cuenta] es vacio";
			} else if(request.getIdUsuario() == 0){
				error = "El campo [UsuarioId] es vacio";
			} else if(request.getNumCliente() == 0){
				error = "Numero de cliente es vacio";
			}
		} else  {
			error = "Request es null";
		}
		return error;
		
	}
	
	private String validateRequestActualiza(VendedorActualizarRequest request){
		String error = null;
		if(request!= null){
			log.info("Validando request: "+request.toString());
			if(request.getId() == 0){
				error = "El campo [Id vendedor] es vacio";
			} else if(request.getNombre() ==  null || request.getNombre().trim().length() == 0){
				error = "El campo [Nombre] es vacio";
			} else if(request.getNumero() == null || request.getNumero().trim().length() == 0){
				error = "El campo [Numero Celular] es vacio";
			} else if(request.getCorreo() == null || request.getCorreo().trim().length() == 0){
				error = "El campo [Correo] es vacio";
			} else if(request.getContrasena() == null || request.getContrasena().trim().length() == 0){
				error = "El campo [Contrasena] es vacio";
			} else if(request.getCuentaId() == 0){
				error = "El campo [Cuenta] es vacio";
			} 
		} else  {
			error = "Request es null";
		}
		return error;
		
	}
	
	private String validateRequestElimina(VendedorEliminaRequest request){
		String error = null;
		if(request!= null){
			log.info("Validando request: "+request.toString());
			if(request.getId() == 0){
				error = "El campo [Id vendedor] es vacio";
			} 
		} else  {
			error = "Request es null";
		}
		return error;
		
	}

}
