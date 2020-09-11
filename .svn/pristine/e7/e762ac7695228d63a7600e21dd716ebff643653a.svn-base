package mx.com.fincomun.vendedores.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.Servicios;

@Service
public class ServiciosDao extends GeneralDao {
	
	private static Logger log = Logger.getLogger(ServiciosDao.class);
	
	public Servicios buscarPorCodigo(String codigo){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Servicios response = null;
		try {
            conn = this.getConnectionOracle();
            String query = "SELECT * FROM TENDEROS_SERVICIOS WHERE CODIGO LIKE '"+codigo+"' ";

            log.info("query : " + query);

            cs = conn.prepareCall(query);   
            cs.executeUpdate();
            rs = (ResultSet) cs.getResultSet();
            
            while (rs.next()){
            	response = new Servicios();
            	response.setId(rs.getLong("ID"));
            	response.setNombre(rs.getString("NOMBRE"));
            	response.setCodigo(rs.getString("CODIGO"));
            	response.setIdTipoServicio(rs.getLong("CAT_TIPO_SERVICIO_ID"));
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