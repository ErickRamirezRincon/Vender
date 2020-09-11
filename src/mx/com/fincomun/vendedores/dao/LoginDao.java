package mx.com.fincomun.vendedores.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.Vendedor;
import mx.com.fincomun.vendedores.bean.request.LoginRequest;
import mx.com.fincomun.vendedores.util.Util;

@Service
public class LoginDao extends GeneralDao{
	
	public Vendedor login(LoginRequest request){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Vendedor response = null;
		try {
            conn = this.getConnectionOracle();
            String query = "SELECT * FROM TENDEROS_VENDEDORES WHERE UPPER(USUARIO) = '"+request.getUsuario().toUpperCase()+"' AND CONTRASENA = '"+Util.cifrarBase64(request.getContrasenia())+"'";

            log.info("query : " + query);

            cs = conn.prepareCall(query);   
            cs.executeUpdate();
            rs = (ResultSet) cs.getResultSet();
            
            while (rs.next()){
            	response = new Vendedor();
            	response.setId(rs.getInt("ID"));
            	response.setNombre(rs.getString("NOMBRE"));
            	response.setTelefono(rs.getLong("TELEFONO"));
            	response.setCorreo(rs.getString("CORREO"));
            	response.setUsuario(rs.getString("USUARIO"));
            	response.setContrasena(rs.getString("CONTRASENA"));
            	response.setCuentaId(rs.getLong("CUENTA_ID"));
            	response.setUsuarioId(rs.getLong("ID_USUARIO"));
            	response.setNumCliente(rs.getString("NUM_CLIENTE"));
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
	
	public String getImei(String numCliente){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String imei = null;
		try {
            conn = this.getConnectionOracleCoDi();
            String query = "SELECT MOV_IMEI FROM MOVILCODI WHERE CAST(MOV_NUMCLI AS INT) = '"+numCliente+"'";

            log.info("query : " + query);

            cs = conn.prepareCall(query);   
            cs.executeUpdate();
            rs = (ResultSet) cs.getResultSet();
            
            while (rs.next()){
            	imei = rs.getString("MOV_IMEI");
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
		return imei; 
		
	}
}
