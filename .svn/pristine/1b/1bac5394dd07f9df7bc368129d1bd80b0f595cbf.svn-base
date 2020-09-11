package mx.com.fincomun.vendedores.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.fincomun.vendedores.bean.Producto;
import mx.com.fincomun.vendedores.bean.Servicios;
import mx.com.fincomun.vendedores.bean.TipoPago;
import mx.com.fincomun.vendedores.bean.Vendedor;
import mx.com.fincomun.vendedores.bean.request.ProductoVenta;
import mx.com.fincomun.vendedores.bean.request.QiuboRecargasRequest;
import mx.com.fincomun.vendedores.bean.request.QiuboServiciosRequest;
import mx.com.fincomun.vendedores.bean.request.RecargaRequest;
import mx.com.fincomun.vendedores.bean.request.ServiciosRequest;
import mx.com.fincomun.vendedores.bean.request.VentaDirectaRequest;
import mx.com.fincomun.vendedores.bean.request.VentaRequest;
import mx.com.fincomun.vendedores.bean.response.QiuboResponse;
import mx.com.fincomun.vendedores.dao.ProductoDao;
import mx.com.fincomun.vendedores.dao.ServiciosDao;
import mx.com.fincomun.vendedores.dao.TipoPagoDao;
import mx.com.fincomun.vendedores.dao.VendedorDao;
import mx.com.fincomun.vendedores.dao.VentaDao;
import mx.com.fincomun.vendedores.util.Constantes;
import mx.com.fincomun.vendedores.util.Util;

@Service
public class VentaService {

	@Autowired
	private VentaDao ventaDao;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private TipoPagoDao tipoPagoDao;
	
	@Autowired
	private ServiciosDao serviciosDao;
	
	@Autowired
	private VendedorDao vendedorDao;
	
	private static Logger log = Logger.getLogger(VentaService.class);
	
	public int altaVentaPorArticulo(VentaRequest request) throws Exception {
		int error = 0;
		int numInsert = 0;
		if(validaTipoPago(request.getIdTipoPago())){
			if(validaVendedor(request.getIdVendedor())){
				if(validaStock(request)){
					Servicios response = serviciosDao.buscarPorCodigo(Constantes.SERVICIO_ARTICULOS);
					if(response != null){
						request.setIdTipoServicio(response.getId());
						log.info("Venta: ["+request.toString()+"]");
						int idVenta = ventaDao.insertVentaPorArticulo(request);
						log.info("IdVenta Generado: ["+idVenta+"]");
						if(idVenta != 0){
							for(ProductoVenta producto: request.getListaProductos()){
								log.info("Producto: ["+producto.toString()+"]");
								ventaDao.insertVentaArticulos(idVenta, producto.getIdProducto(), producto.getNumeroProductos());
								
								restaStock(producto);
								
								numInsert ++;
							}
							log.info("Articulos insertados: ["+numInsert+"]");
						} else {
							error = -2;
						}
					} else {
						error = -2;
					}
				} else {
					error = -5;
				}
			} else {
				error = -4;
			}
		} else {
			error = -1;
		} 
		return error;
	}
	
	public int altaVentaRecargas(RecargaRequest request) throws Exception {
		int error = 0;
		if(validaTipoPago(request.getIdTipoPago())){
			if(validaTipoServicio(request.getCodigoServicio())){
				if(validaVendedor(request.getIdVendedor())){
					QiuboResponse qiuboResponse = enviaRecargasQiubo(getRequestRecargasQiubo(request));
					if(qiuboResponse != null && qiuboResponse.getResponseCode().equalsIgnoreCase("0")){
						VentaRequest ventaRequest = getVentaRequest(request);
						log.info("Venta: ["+ventaRequest.toString()+"]");
						int idVenta = ventaDao.insertVentaPorArticulo(ventaRequest);
						log.info("IdVenta Generado: ["+idVenta+"]");
						if(idVenta == 0){
							error = -2;
						}
					} else {
						error = -5;
					}
				} else {
					error = -4;
				}
			} else {
				error = -3;
			}
		} else {
			error = -1;
		} 
		return error;
	}
	
	public int altaVentaServicios(ServiciosRequest request) throws Exception {
		int error = 0;
		if(validaTipoPago(request.getIdTipoPago())){
			if(validaVendedor(request.getIdVendedor())){
				QiuboResponse qiuboResponse = enviaServiciosQiubo(getRequestServiciosQiubo(request));
				if(qiuboResponse != null && qiuboResponse.getResponseCode().equalsIgnoreCase("0")){
					VentaRequest ventaRequest = getVentaRequest(request);
					log.info("Venta: ["+ventaRequest.toString()+"]");
					int idVenta = ventaDao.insertVentaPorArticulo(ventaRequest);
					log.info("IdVenta Generado: ["+idVenta+"]");
					if(idVenta == 0){
						error = -2;
					}
				} else {
					error = -5;
				}
			} else {
				error = -3;
			}
		} else {
			error = -1;
		} 
		return error;
	}
	
	public int altaVentaDirecta(VentaDirectaRequest request) throws Exception {
		int error = 0;
		if(validaTipoPago(request.getIdTipoPago())){
			if(validaVendedor(request.getIdVendedor())){
				log.info("Venta: ["+request.toString()+"]");
				int idVenta = ventaDao.insertVentaDirecta(request);
				log.info("IdVenta Generado: ["+idVenta+"]");
				if(idVenta == 0){
					error = -2;
				} 
			} else {
				error = -3;
			}
		} else {
			error = -1;
		} 
		return error;
	}
	
	private QiuboResponse enviaRecargasQiubo(QiuboRecargasRequest requestRecargas){
		log.info("Enviando Peticion Qiubo: ["+requestRecargas.toString()+"]");
		QiuboResponse responseQiubo = new QiuboResponse();
		ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(converObjectToJson(requestRecargas), headers);
        
        try {
        	log.info("Enviando peticion a ["+Constantes.RECARGAS_SERVICE_QIUBO+"]");
            String response = restTemplate.postForObject(Constantes.RECARGAS_SERVICE_QIUBO, request, String.class);
        	
        	log.info("Response Qiubo POST: ["+response+"]");
        	responseQiubo = mapper.readValue(response, QiuboResponse.class);
			
		} catch (JsonParseException e) {
			log.error("Error al convertir JSON: "+e.getCause());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		} catch (JsonMappingException e) {
			log.error("Error al convertir JSON: "+e.getCause());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		} catch (IOException e) {
			log.error("Error al convertir JSON: "+e.getCause());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		} catch(Exception e){
			log.error("Error: "+e.getMessage());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		}
        log.info("Response ValidaToken: "+responseQiubo.toString());
        
        return responseQiubo;
	}
	
	private QiuboResponse enviaServiciosQiubo(QiuboServiciosRequest serviciosRequest){
		log.info("Enviando Peticion Qiubo: ["+serviciosRequest.toString()+"]");
		QiuboResponse responseQiubo = new QiuboResponse();
		ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(converObjectToJson(serviciosRequest), headers);
        
        try {
        	log.info("Enviando peticion a ["+Constantes.SERVICIOS_SERVICE_QIUBO+"]");
            String response = restTemplate.postForObject(Constantes.SERVICIOS_SERVICE_QIUBO, request, String.class);
        	
        	log.info("Response Qiubo POST: ["+response+"]");
        	responseQiubo = mapper.readValue(response, QiuboResponse.class);
			
		} catch (JsonParseException e) {
			log.error("Error al convertir JSON: "+e.getCause());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		} catch (JsonMappingException e) {
			log.error("Error al convertir JSON: "+e.getCause());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		} catch (IOException e) {
			log.error("Error al convertir JSON: "+e.getCause());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		} catch(Exception e){
			log.error("Error: "+e.getMessage());
			responseQiubo.setResponseCode("-1");
			responseQiubo.setResponseDescription(Constantes.MSJ_ERROR);
		}
        log.info("Response ValidaToken: "+responseQiubo.toString());
        
        return responseQiubo;
	}
	
	
	private QiuboRecargasRequest getRequestRecargasQiubo(RecargaRequest request){
		QiuboRecargasRequest requestQiubo = new QiuboRecargasRequest();
		requestQiubo.setAmount(String.valueOf(request.getMontoTotal()));
		requestQiubo.setCodeCompania(request.getCodigoServicio());
		requestQiubo.setMobileNumber(request.getMobileNumber());
		requestQiubo.setNumCtaSibamex(request.getNumCuenta());
		return requestQiubo;
	}
	
	private QiuboServiciosRequest getRequestServiciosQiubo(ServiciosRequest request){
		QiuboServiciosRequest requestQiubo = new QiuboServiciosRequest();
		requestQiubo.setTipoServicio(request.getCodigoServicio());
		requestQiubo.setNumCtaSibamex(request.getNumCuenta());
		
		if(request.getCodigoServicio().equals(Constantes.SERVICIO_CFE_CODIGO)){
			requestQiubo.setAmount(String.valueOf(request.getMontoTotal()));
			requestQiubo.setAccountNumber1(request.getAccountNumber1());
			requestQiubo.setAccountNumber2(request.getAccountNumber2());
			requestQiubo.setAccountNumber3(request.getAccountNumber3()); 
		}else if(request.getCodigoServicio().equals(Constantes.SERVICIO_GAS_NATURAL_CODIGO)){
			requestQiubo.setAmount(String.valueOf(request.getMontoTotal()));
			requestQiubo.setAccountNumber1(request.getAccountNumber1());
			requestQiubo.setAccountNumber2(request.getAccountNumber2());
		} else if(request.getCodigoServicio().equals(Constantes.SERVICIO_IZZI_CODIGO) ||
				request.getCodigoServicio().equals(Constantes.SERVICIO_MAXCOM_CODIGO) ||
				request.getCodigoServicio().equals(Constantes.SERVICIO_OMNIBUS_CODIGO) ||
				request.getCodigoServicio().equals(Constantes.SERVICIO_PASE_URBANO_CODIGO) ||
				request.getCodigoServicio().equals(Constantes.SERVICIO_SKY_CODIGO) ||
				request.getCodigoServicio().equals(Constantes.SERVICIO_TELEVIA_CODIGO)){
			requestQiubo.setAmount(String.valueOf(request.getMontoTotal()));
			requestQiubo.setAccountNumber(request.getAccountNumber());
		} else if(request.getCodigoServicio().equals(Constantes.SERVICIO_TELMEX_CODIGO)){
			requestQiubo.setAmount(String.valueOf(request.getMontoTotal()));
			requestQiubo.setAccountNumber(request.getAccountNumber());
			requestQiubo.setVerificationDigit(request.getVerificationDigit());
		} else if(request.getCodigoServicio().equals(Constantes.SERVICIO_DISH_CODIGO)){
			requestQiubo.setAccountNumber(request.getAccountNumber());  
		}
		return requestQiubo;
	}
	
	private VentaRequest getVentaRequest(RecargaRequest request){
		VentaRequest ventaRequest = new VentaRequest();
		
		Servicios response = serviciosDao.buscarPorCodigo(request.getCodigoServicio());
		
		ventaRequest.setCodigoQR("");
		ventaRequest.setComision(request.getComision());
		ventaRequest.setIdTipoPago(request.getIdTipoPago());
		ventaRequest.setIdTipoServicio(response.getId());
		ventaRequest.setIdUsuario(String.valueOf(request.getIdUsuario()));
		ventaRequest.setIdVendedor(request.getIdVendedor());
		ventaRequest.setMontoTotal(request.getMontoTotal());
		return ventaRequest;
	}
	
	private VentaRequest getVentaRequest(ServiciosRequest request){
		VentaRequest ventaRequest = new VentaRequest();
		
		Servicios response = serviciosDao.buscarPorCodigo(request.getCodigoServicio());
		
		ventaRequest.setCodigoQR("");
		ventaRequest.setComision(request.getComision());
		ventaRequest.setIdTipoPago(request.getIdTipoPago());
		ventaRequest.setIdTipoServicio(response.getId());
		ventaRequest.setIdUsuario(String.valueOf(request.getIdUsuario()));
		ventaRequest.setIdVendedor(request.getIdVendedor());
		ventaRequest.setMontoTotal(request.getMontoTotal());
		return ventaRequest;
	}
	
	private String converObjectToJson(QiuboRecargasRequest request){
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			log.error("Error al convertir a JSON..."+request);
		}
		return jsonInString;
	}
	
	private String converObjectToJson(QiuboServiciosRequest request){
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			log.error("Error al convertir a JSON..."+request);
		}
		return jsonInString;
	}
	
	private boolean validaVendedor(long idVendedor){
		boolean isExist = false;
		Vendedor response = vendedorDao.getPorId(idVendedor);
		if(response != null){
			isExist = true;
		}
		return isExist;
	}
	
	private boolean validaTipoServicio(String idTipoServicio){
		boolean isExist = false;
		Servicios response = serviciosDao.buscarPorCodigo(idTipoServicio);
		if(response != null){
			isExist = true;
		}
		return isExist;
	}
	
	private boolean validaStock(VentaRequest request){
		boolean isValid = false;
		int error = 0;
		for(ProductoVenta producto: request.getListaProductos()){
			Producto productoInventario = productoDao.getPorId(producto.getIdProducto());
			if(productoInventario != null){
				if(producto.getNumeroProductos() > productoInventario.getStock()){
					error++;
				}
			}
		}
		if(error == 0){
			isValid = true;
		}
		log.info("ValidaStock:" +isValid);
		
		return isValid;
	}
	
	private void restaStock(ProductoVenta producto){
		Producto productoInt = productoDao.getPorId(producto.getIdProducto());
		if(productoInt != null){
			int stockFix = productoInt.getStock() - producto.getNumeroProductos();
			log.info("Stock actual: "+stockFix);
			
			Producto request = new Producto();
			request.setId(productoInt.getId());
			request.setStock(stockFix);
			productoDao.updateProductoStock(request);
		}
	}
	
	private boolean validaTipoPago(long idTipoPago){
		boolean isExist = false;
		TipoPago response = tipoPagoDao.buscarTipoPago(idTipoPago);
		if(response != null){
			isExist = true;
		}
		return isExist;
	}
	
}