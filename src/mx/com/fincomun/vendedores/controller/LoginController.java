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

import mx.com.fincomun.vendedores.bean.request.LoginRequest;
import mx.com.fincomun.vendedores.bean.response.LoginResponse;
import mx.com.fincomun.vendedores.service.LoginService;
import mx.com.fincomun.vendedores.util.Constantes;
import mx.com.fincomun.vendedores.util.StringEncrypt;
import mx.com.fincomun.vendedores.util.Util;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/restful/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String requestString) {
		LoginResponse response = null;	
		log.info("ServicioRest [/restful/login] .... ");
		
		LoginRequest request = converJsonToRequest(requestString);

		String error = validateRequest(request);
		if(error == null){
			response = loginService.login(request);
			if(response != null){
				response.setCode(Constantes.CODIGO_EXITO);
				response.setMensaje(Constantes.MSJ_EXITO);
			} else {
				response = new LoginResponse();
				response.setCode(Constantes.CODIGO_ERROR);
				response.setMensaje(Constantes.MSJ_ERROR_LOGIN);
			}
		} else {
			response = new LoginResponse();
			response.setCode(Constantes.CODIGO_ERROR_REQUEST);
			response.setMensaje(error);
		}

		return Util.getResponse(response);
	}
	
	private LoginRequest converJsonToRequest(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		LoginRequest request = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String requestDecrypt = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			request = mapper.readValue(requestDecrypt, LoginRequest.class);
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return request;
	}
	
	private String validateRequest(LoginRequest request){
		String error = null;
		if(request != null){
			log.info("loginRequest: "+request.toString());
			if(request.getUsuario() == null || request.getUsuario().trim().length() == 0){
				error = "Usuario es vacio";
			} else if(request.getContrasenia() == null || request.getContrasenia().trim().length() == 0){
				error = "Contrase\u00f1a es vacio";
			} 
		} else {
			error = "Request es null";
		}
		return error;
	}
}
