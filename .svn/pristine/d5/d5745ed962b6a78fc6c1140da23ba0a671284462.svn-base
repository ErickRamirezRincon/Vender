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
import mx.com.fincomun.vendedores.bean.EnvioSMSBean;
import mx.com.fincomun.vendedores.bean.RespuestaSMSBean;

import mx.com.fincomun.vendedores.bean.response.Response;
import mx.com.fincomun.vendedores.bean.response.ValidaTokenRespose;
import mx.com.fincomun.vendedores.service.ValidaTokenService;
import mx.com.fincomun.vendedores.service.VentaService;
import mx.com.fincomun.vendedores.bean.request.RecargaRequest;
import mx.com.fincomun.vendedores.bean.request.ServiciosRequest;
import mx.com.fincomun.vendedores.bean.request.VentaDirectaRequest;
import mx.com.fincomun.vendedores.bean.request.VentaRequest;
import mx.com.fincomun.vendedores.service.EnvioSMS;
import mx.com.fincomun.vendedores.util.Constantes;
import mx.com.fincomun.vendedores.util.StringEncrypt;
import mx.com.fincomun.vendedores.util.Util;
import org.apache.commons.lang3.StringUtils;

@Controller
public class VentaController {
	
	@Autowired
	private ValidaTokenService validaTokenService;
	
	@Autowired
	private VentaService ventaService;
        
        @Autowired
        EnvioSMS SMS;
	
	private static Logger log = Logger.getLogger(VentaController.class);
	
	@RequestMapping(value = "/restful/venta/articulo", method = RequestMethod.POST)
	public @ResponseBody String ventaPorArticulo(@RequestBody String requestString) {
		Response response = new Response(); 
		log.info("ServicioRest [/restful/venta/articulo] .... ");
		
		VentaRequest request = converJsonToVentaArticulo(requestString);
		
//		ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//		if(validaTokenResponse != null){ 
//			if(validaTokenResponse.getCodigo() == 0){
				String error = validateVentaArticulo(request);
				if(error == null){
					int isAltaVenta;
					try {
						isAltaVenta = ventaService.altaVentaPorArticulo(request);
						if(isAltaVenta == 0){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else if(isAltaVenta == -1){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTEN_TIPO_PAGO);
						} else if(isAltaVenta == -2){
							response.setCode(Constantes.CODIGO_ERROR_OPERACION);
							response.setMensaje(Constantes.MSJ_ERROR);
						} else if(isAltaVenta == -3){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_TIPPO_SERVICIO);
						} else if(isAltaVenta == -4){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_VENDEDOR);
						} else if(isAltaVenta == -5){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_STOCK);
						}

					} catch (Exception e) {
						log.error("Error: "+e.getCause());
						response = new Response();
						response.setCode(Constantes.CODIGO_ERROR_OPERACION);
						response.setMensaje(Constantes.MSJ_ERROR);
					}
				} else {
					response.setCode(Constantes.CODIGO_ERROR_REQUEST);
					response.setMensaje(error);
				}
//			} else {
//				response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//				response.setMensaje(validaTokenResponse.getMensaje());
//			}
//		} else {
//			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//			response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//		}
		return Util.getResponse(response);
	}

	
	@RequestMapping(value = "/restful/venta/recargas", method = RequestMethod.POST)
	public @ResponseBody String recargas(@RequestBody String requestString) {
		Response response = new Response(); 
		log.info("ServicioRest [/restful/venta/recargas] .... ");
		
		RecargaRequest request = converJsonToVentaRecargas(requestString);
		
//		ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//		if(validaTokenResponse != null){ 
//			if(validaTokenResponse.getCodigo() == 0){
				String error = validateVentaRecargas(request);
				if(error == null){
					int isAltaVenta;
					try {
						isAltaVenta = ventaService.altaVentaRecargas(request);
						if(isAltaVenta == 0){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else if(isAltaVenta == -1){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTEN_TIPO_PAGO);
						} else if(isAltaVenta == -2){
							response.setCode(Constantes.CODIGO_ERROR_OPERACION);
							response.setMensaje(Constantes.MSJ_ERROR);
						} else if(isAltaVenta == -3){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_TIPPO_SERVICIO);
						} else if(isAltaVenta == -4){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_VENDEDOR);
						} else if(isAltaVenta == -5){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_RECARGA);
						}
					} catch (Exception e) {
						log.error("Error: "+e.getCause());
						response = new Response();
						response.setCode(Constantes.CODIGO_ERROR_OPERACION);
						response.setMensaje(Constantes.MSJ_ERROR);
					}
				} else {
					response.setCode(Constantes.CODIGO_ERROR_REQUEST);
					response.setMensaje(error);
				}
//			} else {
//				response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//				response.setMensaje(validaTokenResponse.getMensaje());
//			}
//		} else {
//			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//			response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//		}
		return Util.getResponse(response);
	}
	
	@RequestMapping(value = "/restful/venta/servicios", method = RequestMethod.POST)
	public @ResponseBody String servicios(@RequestBody String requestString) {
		Response response = new Response(); 
		log.info("ServicioRest [/restful/venta/servicios] .... ");
		
		ServiciosRequest request = converJsonToVentaServicios(requestString);
		
//		ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//		if(validaTokenResponse != null){ 
//			if(validaTokenResponse.getCodigo() == 0){
				String error = validateVentaServicios(request);
				if(error == null){
					int isAltaVenta;
					try {
						isAltaVenta = ventaService.altaVentaServicios(request);
						if(isAltaVenta == 0){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else if(isAltaVenta == -1){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTEN_TIPO_PAGO);
						} else if(isAltaVenta == -2){
							response.setCode(Constantes.CODIGO_ERROR_OPERACION);
							response.setMensaje(Constantes.MSJ_ERROR);
						} else if(isAltaVenta == -3){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_TIPPO_SERVICIO);
						} else if(isAltaVenta == -4){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_VENDEDOR);
						} else if(isAltaVenta == -5){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_PAGO_SERVICIO);
						}
					} catch (Exception e) {
						log.error("Error: "+e.getCause());
						response = new Response();
						response.setCode(Constantes.CODIGO_ERROR_OPERACION);
						response.setMensaje(Constantes.MSJ_ERROR);
					}
				} else {
					response.setCode(Constantes.CODIGO_ERROR_REQUEST);
					response.setMensaje(error);
				}
//			} else {
//				response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//				response.setMensaje(validaTokenResponse.getMensaje());
//			}
//		} else {
//			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//			response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//		}
		return Util.getResponse(response);
	}
	
	@RequestMapping(value = "/restful/venta/directa", method = RequestMethod.POST)
	public @ResponseBody String directa(@RequestBody String requestString) {
		Response response = new Response(); 
		log.info("ServicioRest [/restful/venta/directa] .... ");
		
		VentaDirectaRequest request = converJsonToVentaDirecta(requestString);
		
//		ValidaTokenRespose validaTokenResponse = validaTokenService.validaToken(request.getTokenJwt());
//		if(validaTokenResponse != null){ 
//			if(validaTokenResponse.getCodigo() == 0){
				String error = validateVentaDirecta(request);
				if(error == null){
					int isAltaVenta;
					try {
						isAltaVenta = ventaService.altaVentaDirecta(request);
						if(isAltaVenta == 0){
							response.setCode(Constantes.CODIGO_EXITO);
							response.setMensaje(Constantes.MSJ_EXITO);
						} else if(isAltaVenta == -1){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTEN_TIPO_PAGO);
						} else if(isAltaVenta == -2){
							response.setCode(Constantes.CODIGO_ERROR_OPERACION);
							response.setMensaje(Constantes.MSJ_ERROR);
						} else if(isAltaVenta == -3){
							response.setCode(Constantes.CODIGO_ERROR);
							response.setMensaje(Constantes.MSJ_ERROR_NO_EXISTE_VENDEDOR);
						} 
					} catch (Exception e) {
						log.error("Error: "+e.getCause());
						response = new Response();
						response.setCode(Constantes.CODIGO_ERROR_OPERACION);
						response.setMensaje(Constantes.MSJ_ERROR);
					}
				} else {
					response.setCode(Constantes.CODIGO_ERROR_REQUEST);
					response.setMensaje(error);
				}
//			} else {
//				response.setCode(String.valueOf(validaTokenResponse.getCodigo()));
//				response.setMensaje(validaTokenResponse.getMensaje());
//			}
//		} else {
//			response.setCode(Constantes.CODIGO_ERROR_OPERACION);
//			response.setMensaje(Constantes.MSJ_ERROR_TOKEN);
//		}
		return Util.getResponse(response);
	}
        
        
        ////////////////////////
        @RequestMapping(value = "rest/EnvioSMS", method = RequestMethod.POST,produces ={"application/json", "application/xml"})
        public @ResponseBody String EnviaSMS(@RequestBody String requestString) throws Exception {
            RespuestaSMSBean response = new RespuestaSMSBean();
            response.setCodigo(0);
            String respuesta="";
            log.info("ServicioRest [/rest/EnviaSMS] .... ");
            //Request requiere token,nombre,fecha,total.
            EnvioSMSBean mensajeJson=convertJSONSMS(requestString);
            log.info("Json recibido:\n"+mensajeJson);
            /*ValidaTokenRespose validacionT=validaTokenService.validaToken(mensajeJson.getToken());
            response.setCodigo(validacionT.getCodigo());
            response.setMensaje(validacionT.getMensaje());*/
            response.setMensaje(validaEnvioSMS(mensajeJson));
            if (response.getCodigo()== 0) {
                if (response.getMensaje() == null) {
                    log.info("Datos a enviar: " + mensajeJson.toString());
                    boolean respuestaMensaje = SMS.msjTokenMX(mensajeJson);
                    if (respuestaMensaje) {
                        response.setCodigo(0);
                        response.setMensaje("Success");
                    }else{
                        response.setCodigo(-1);
                    }
                }
            }
            log.info("Respuesta EnvioSMS: \n"+response);
            respuesta=StringEncrypt.encrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV, response.toString());
            log.info("Despues de encriptar:\n"+respuesta);
            return respuesta;
        }

        private String validaEnvioSMS(EnvioSMSBean request) {
            String sms = null;
            log.info("Validando datos de mensaje");
            log.info("Datos Recibidos: "+request);
            if (request != null) {
                if (request.getNombre().isEmpty()) {
                    sms = "Falta campo nombre";
                } else if (request.getTotal() == 0) {
                    sms = "Falta campo total";
                } else if (request.getFecha().isEmpty()) {
                    sms = "Fecha erronea";
                }else if(request.getCelular().isEmpty()){
                    sms="Falta numero celular";
                }else if(request.getCelular().length()<10){
                    sms="Telefono no valido";
                }
            }
            return sms;
        }

        private EnvioSMSBean convertJSONSMS(String requestEncrypt) {
            ObjectMapper mapper = new ObjectMapper();
            EnvioSMSBean mensajeJson = null;
            try {
                log.info("Request String: [" + requestEncrypt + "]");
                String request = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV, requestEncrypt);
                mensajeJson = mapper.readValue(request, EnvioSMSBean.class);
            } catch (IOException e) {
                log.error("Error al convertir Object: " + e.getMessage());
            } catch (Exception e) {
                log.error("Error al desencriptar Object: " + e.getMessage());
            }
            return mensajeJson;
        }

        ////////////////////////
	
	private String validateVentaArticulo(VentaRequest request){
		String error = null;
		if(request != null){
			log.info("Validando Request venta por articulo: [ "+request.toString()+" ]");
			if(request.getMontoTotal() == 0){
				error = "Monto total es 0";
			} else if(request.getIdTipoPago() == 0){
				error = "IdTipoPago es 0";
			} else if(request.getIdUsuario() == 0){
				error = "IdUsuario es 0";
			} else if(request.getCodigoQR() == null && request.getCodigoQR().trim().length() == 0){
				error = "CodigoQR es vacio";
			} else if(request.getListaProductos() == null || request.getListaProductos().size() == 0){
				error = "La lista de los productos es vacia";
			} else if(request.getIdVendedor() == 0){
				error = "IdVendedor en 0";
			}
		} 
		return error;
	}
	
	private String validateVentaRecargas(RecargaRequest request){
		String error = null;
		if(request != null){
			log.info("Validando Request venta de recargas: [ "+request.toString()+" ]");
			if(request.getCodigoServicio() == null || request.getCodigoServicio().trim().length() == 0){
				error = "Codigo es vacio";
//			} else if(request.getComision() == 0){
//				error = "Comision es 0";
			} else if(request.getIdTipoPago() == 0){ 
				error = "Id tipo pago es 0";
			} else if(request.getCodigoServicio() == null || request.getCodigoServicio().trim().length() == 0){
				error = "codigo servicio es vacio";
			} else if(request.getIdUsuario() == 0){
				error = "Id Usuario es 0";
			} else if(request.getIdVendedor() == 0){
				error = "Id Vendedor es 0";
			} else if(request.getMobileNumber() == null || request.getMobileNumber().trim().length() == 0){
				error = "Numero de telefono es vacio";
			} else if(request.getMontoTotal() == 0){
				error = "Monto es 0";
			} else if(request.getNumCuenta() == null || request.getNumCuenta().trim().length() == 0){
				error = "numero de cuenta es vacio";
			}
		} 
		return error;
	}
	
	private String validateVentaServicios(ServiciosRequest request){
		String error = null;
		 if(request != null) {
			 log.info("Validando Request venta de servicios: [ "+request.toString()+" ]");
			 if(request.getCodigoServicio() != null && request.getCodigoServicio().trim().length() > 0){
				 if(request.getCodigoServicio().equals(Constantes.SERVICIO_CFE_CODIGO)){
					 if(request.getMontoTotal() == 0){
						 error = "Monto es vacio";
					 } else if(request.getAccountNumber1() == null || request.getAccountNumber1().trim().length() == 0){
						 error = "AccountNumber1 es vacio";
					 } else if(request.getAccountNumber2() == null || request.getAccountNumber2().trim().length() == 0){
						 error = "AccountNumber2 es vacio";
					 } else if(request.getAccountNumber3() == null || request.getAccountNumber3().trim().length() == 0){
						 error = "AccountNumber3 es vacio";
					 } 
				 }else if(request.getCodigoServicio().equals(Constantes.SERVICIO_GAS_NATURAL_CODIGO)){
					 if(request.getMontoTotal() == 0){
						 error = "Monto es vacio";
					 } else if(request.getAccountNumber1() == null || request.getAccountNumber1().trim().length() == 0){
						 error = "AccountNumber1 es vacio";
					 } else if(request.getAccountNumber2() == null || request.getAccountNumber2().trim().length() == 0){
						 error = "AccountNumber2 es vacio";
					 }
				 } else if(request.getCodigoServicio().equals(Constantes.SERVICIO_IZZI_CODIGO) ||
						 request.getCodigoServicio().equals(Constantes.SERVICIO_MAXCOM_CODIGO) ||
						 request.getCodigoServicio().equals(Constantes.SERVICIO_OMNIBUS_CODIGO) ||
						 request.getCodigoServicio().equals(Constantes.SERVICIO_PASE_URBANO_CODIGO) ||
						 request.getCodigoServicio().equals(Constantes.SERVICIO_SKY_CODIGO) ||
						 request.getCodigoServicio().equals(Constantes.SERVICIO_TELEVIA_CODIGO)){
					 if(request.getMontoTotal() == 0){
						 error = "Monto es vacio";
					 } else if(request.getAccountNumber() == null || request.getAccountNumber().trim().length() == 0){
						 error = "AccountNumber es vacio";
					 } 
				 } else if(request.getCodigoServicio().equals(Constantes.SERVICIO_TELMEX_CODIGO)){
					 if(request.getMontoTotal() == 0){
						 error = "Monto es vacio";
					 } else if(request.getAccountNumber() == null || request.getAccountNumber().trim().length() == 0){
						 error = "AccountNumber es vacio";
					 } else if(request.getVerificationDigit() == null || request.getVerificationDigit().trim().length() == 0){
						 error = "VerificationDigit es vacio";
					 }
				 } else if(request.getCodigoServicio().equals(Constantes.SERVICIO_DISH_CODIGO)){
					 if(request.getAccountNumber() == null || request.getAccountNumber().trim().length() == 0) {
						 error = "AccountNumber es vacio";
					 }
				 }
			 } else {
				 error = "codigo servicio es vacio";
			 }
			 
//			 if(request.getComision() == 0){
//				 error = "Comision es 0";
//			 } else 
			 if(request.getIdTipoPago() == 0){ 
				 error = "Id tipo pago es 0";
			 } else if(request.getIdUsuario() == 0){
				 error = "Id Usuario es 0";
			 } else if(request.getIdVendedor() == 0){
				 error = "Id Vendedore es 0";
			 } else if(request.getNumCuenta() == null || request.getNumCuenta().trim().length() == 0){
				 error = "numero de cuenta es vacio";
			 }
			
		 }
		 return error;
	}
	
	private String validateVentaDirecta(VentaDirectaRequest request){
		String error = null;
		if(request != null){
			log.info("Validando Request venta directa: [ "+request.toString()+" ]");
			if(request.getMontoTotal() == 0){
				error = "Monto total es 0";
			} else if(request.getIdTipoPago() == 0){
				error = "IdTipoPago es 0";
			} else if(request.getIdUsuario() == 0){
				error = "IdUsuario es 0";
			} else if(request.getIdVendedor() == 0){
				error = "IdVendedor es 0";
			}
		} 
		return error;
	}
	
	private VentaRequest converJsonToVentaArticulo(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		VentaRequest ventaRequest = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String request = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			ventaRequest = mapper.readValue(request, VentaRequest.class);
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return ventaRequest;
	}
	
	private ServiciosRequest converJsonToVentaServicios(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		ServiciosRequest serviciosRequest = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String request = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			serviciosRequest = mapper.readValue(request, ServiciosRequest.class);
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return serviciosRequest;
	}
	
	private RecargaRequest converJsonToVentaRecargas(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		RecargaRequest recargaRequest = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String request = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			recargaRequest = mapper.readValue(request, RecargaRequest.class);
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return recargaRequest;
	}
	
	private VentaDirectaRequest converJsonToVentaDirecta(String requestEncrypt){
		ObjectMapper mapper = new ObjectMapper();
		VentaDirectaRequest ventaDirectaRequest = null;
		try {
			log.info("Request String: ["+requestEncrypt+"]");
			String request = StringEncrypt.decrypt(Constantes.PASSWORD_KEY, Constantes.PASSWORD_IV,requestEncrypt);
			ventaDirectaRequest = mapper.readValue(request, VentaDirectaRequest.class);
		} catch (IOException e) {
			log.error("Error al convertir Object: "+e.getMessage());
		} catch (Exception e) {
			log.error("Error al desencriptar Object: "+e.getMessage());
		}
		return ventaDirectaRequest;
	}
}
