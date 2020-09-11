package mx.com.fincomun.vendedores.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.fincomun.vendedores.bean.response.CatalogoResponse;
import mx.com.fincomun.vendedores.bean.response.QiuboCatalogoResponse;
import mx.com.fincomun.vendedores.util.Constantes;

@Service
public class CatalogoService {
	
	private static Logger log = Logger.getLogger(CatalogoService.class);
	
	public QiuboCatalogoResponse getCatalogo(){
		log.info("Enviando Peticion Qiubo para obtener Catalogo");
		QiuboCatalogoResponse response = null;
		ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
        	log.info("Enviando peticion a ["+Constantes.CATALOGO_SERVICE_QIUBO+"]");
            String responseQiubo= restTemplate.getForObject(Constantes.CATALOGO_SERVICE_QIUBO, String.class);
        	
        	log.info("Response Qiubo POST: ["+responseQiubo+"]");
        	CatalogoResponse responseCatalogo = mapper.readValue(responseQiubo, CatalogoResponse.class);
        	
        	if(responseCatalogo != null){
        		response = new QiuboCatalogoResponse();
        		response.setProductList(responseCatalogo.getProductList());
        	}
			
		} catch (JsonParseException e) {
			log.error("Error al convertir JSON: "+e.getCause());
		} catch (JsonMappingException e) {
			log.error("Error al convertir JSON: "+e.getCause());
		} catch (IOException e) {
			log.error("Error al convertir JSON: "+e.getCause());
		} catch(Exception e){
			log.error("Error: "+e.getMessage());
		}
        return response;
	}

}
