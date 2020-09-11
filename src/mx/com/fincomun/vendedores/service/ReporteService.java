package mx.com.fincomun.vendedores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.request.ReporteRequest;
import mx.com.fincomun.vendedores.bean.response.ReporteResponse;
import mx.com.fincomun.vendedores.dao.ReporteDao;
import mx.com.fincomun.vendedores.util.Constantes;

@Service
public class ReporteService {
	
	@Autowired
	ReporteDao reporteDao;

	public ReporteResponse getReporte(ReporteRequest request){
		ReporteResponse response = new ReporteResponse();
		
		float ventasEfectivo = reporteDao.totalVentasPorTipo(request, Constantes.TIPO_PAGO_EFECTIVO);
		float ventasCodi = reporteDao.totalVentasPorTipo(request, Constantes.TIPO_PAGO_CODI);
		
		float totalVentas = ventasEfectivo + ventasCodi;
		response.setVentasEfectivo(ventasEfectivo);
		response.setVentasQr(ventasCodi);
		response.setVentasTotal(totalVentas);
		
		return response;
	}
}
