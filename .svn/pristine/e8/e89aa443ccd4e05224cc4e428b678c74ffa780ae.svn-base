package mx.com.fincomun.vendedores.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.request.ReporteRequest;


@Service
public class ReporteDao extends GeneralDao {

	private static Logger log = Logger.getLogger(ReporteDao.class);
	
	public float totalVentasPorTipo(ReporteRequest request, int tipoPago){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        float response = 0;
		try {
            conn = this.getConnectionOracle();
            
            String query = "SELECT SUM(MONTO_TOTAL) AS TOTAL_VENTAS  "
            		+ "FROM TENDEROS_VENTAS "
            		+ "WHERE TENDEROS_VENDEDORES_ID = "+request.getIdVendedor()+" "
            		+ "AND FECHA between to_timestamp('"+request.getFecha()+"') AND to_timestamp('"+request.getFecha()+"')+.9999999 "
            		+ "AND TIPO_PAGO_ID = "+tipoPago+" ";

            log.info("query : " + query);

            cs = conn.prepareCall(query);   
            cs.executeUpdate();
            rs = (ResultSet) cs.getResultSet();
            
            while (rs.next()){
            	response = rs.getFloat("TOTAL_VENTAS");
            }       
            conn.commit();
        } catch (Exception e) {
            log.info("Exception ERROR:" + e);
            try {
                closeResultSet(rs);
                closeStatement(cs);
                closeConnection(conn);
            } catch (SQLException ex) {
                e.printStackTrace();
                log.error(e +"");
            }
        } finally {
            try {
                closeResultSet(rs);
                closeStatement(cs);
                closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e + "");
            }
        }
		return response; 
	}
}
