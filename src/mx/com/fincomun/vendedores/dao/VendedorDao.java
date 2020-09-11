package mx.com.fincomun.vendedores.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.Vendedor;
import mx.com.fincomun.vendedores.bean.request.VendedorActualizarRequest;
import mx.com.fincomun.vendedores.bean.request.VendedorEliminaRequest;
import mx.com.fincomun.vendedores.bean.request.VendedorRequest;
import mx.com.fincomun.vendedores.util.Util;


@Service
public class VendedorDao  extends GeneralDao {
	
	private static Logger log = Logger.getLogger(VendedorDao.class);

	public boolean insertVendedor(VendedorRequest request){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		boolean isInsert = true;
		
		try{
			int sequenceValue = callSequence("TENDEROS_VENDEDORES_SEQ");
			
			conn = this.getConnectionOracle();
			
			String in = "INSERT INTO TENDEROS_VENDEDORES(ID,NOMBRE,TELEFONO,CORREO,USUARIO,CONTRASENA,CUENTA_ID,ID_USUARIO,FECHA_ALTA,NUM_CLIENTE) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setInt(1, sequenceValue);
			pstmt.setString(2, request.getNombre());
			pstmt.setLong(3, Long.parseLong(request.getNumero()));
			pstmt.setString(4, request.getCorreo());
			pstmt.setString(5, request.getUsuario());
			pstmt.setString(6, Util.cifrarBase64(request.getContrasena()));
			pstmt.setLong(7, request.getCuentaId());
			pstmt.setLong(8, request.getIdUsuario()); //IdTendero
			pstmt.setTimestamp(9, new Timestamp(new Date().getTime()));
			pstmt.setLong(10, request.getNumCliente());
			
			pstmt.execute();
			
		} catch (Exception e) {
			isInsert = false;
			log.error("Error al registrar vendedor: " + e);
			try {
				closeResultSet(rs);
				closeStatement(pstmt);
				closeConnection(conn);
			}catch (SQLException ex) {
				e.printStackTrace();log.error(e);
			}
		} finally{
			try {
				closeResultSet(rs);
				closeStatement(pstmt);
				closeConnection(conn);
			}catch (SQLException e) {
				e.printStackTrace();log.error(e);		
			}
		}
		return isInsert;
	}
	
	public boolean actualizaVendedor(VendedorActualizarRequest request){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		boolean isUpdate = false;
		
		try{
			conn = this.getConnectionOracle();
			
			String in = "UPDATE TENDEROS_VENDEDORES SET "
					+ "NOMBRE = ? , "
					+ "TELEFONO = ?, "
					+ "CORREO = ?, "
					+ "CONTRASENA = ?, "
					+ "FECHA_ACTUALIZA = ?, "
					+ "CUENTA_ID = ? "
					+ "WHERE ID = ?";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setString(1, request.getNombre());
			pstmt.setLong(2, Long.parseLong(request.getNumero()));
			pstmt.setString(3, request.getCorreo());
			pstmt.setString(4, Util.cifrarBase64(request.getContrasena()));
			pstmt.setTimestamp(5, new Timestamp(new Date().getTime()));
			pstmt.setLong(6, request.getCuentaId());
			pstmt.setInt(7, request.getId());
			
			int update = pstmt.executeUpdate();
			
			if(update == 1){
				isUpdate = true;
			}
			
		} catch (Exception e) {
			log.error("Error al actualizar vendedor: " + e);
			try {
				closeResultSet(rs);
				closeStatement(pstmt);
				closeConnection(conn);
			}catch (SQLException ex) {
				e.printStackTrace();log.error(e);
			}
		} finally{
			try {
				closeResultSet(rs);
				closeStatement(pstmt);
				closeConnection(conn);
			}catch (SQLException e) {
				e.printStackTrace();log.error(e);		
			}
		}
		return isUpdate;
	}
	
	public boolean deleteVendedor(VendedorEliminaRequest request){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		boolean isDelete = false;
		
		try{
			conn = this.getConnectionOracle();
			
			String in = "DELETE FROM TENDEROS_VENDEDORES WHERE ID = ?";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setInt(1, request.getId());
			
			int delete = pstmt.executeUpdate();
			
			if(delete == 1){
				isDelete = true;
			}
			
		} catch (Exception e) {
			log.error("Error al eliminar vendedor: " + e);
			try {
				closeResultSet(rs);
				closeStatement(pstmt);
				closeConnection(conn);
			}catch (SQLException ex) {
				e.printStackTrace();log.error(e);
			}
		} finally{
			try {
				closeResultSet(rs);
				closeStatement(pstmt);
				closeConnection(conn);
			}catch (SQLException e) {
				e.printStackTrace();log.error(e);		
			}
		}
		return isDelete;
	}
	
	public Vendedor getPorUsuario(String usuario){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Vendedor response = null;
		try {
            conn = this.getConnectionOracle();
            String query = "SELECT * FROM TENDEROS_VENDEDORES WHERE UPPER(USUARIO) = '"+usuario+"'";

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
	
	public Vendedor getPorId(long idUsuario){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Vendedor response = null;
		try {
            conn = this.getConnectionOracle();
            String query = "SELECT * FROM TENDEROS_VENDEDORES WHERE ID = "+idUsuario+"";

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
	
	public List<Vendedor> listaVendedores(long idUsuario){
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
		try {
            conn = this.getConnectionOracle();
            String query = "SELECT * FROM TENDEROS_VENDEDORES WHERE ID_USUARIO = "+idUsuario+" ";

            log.info("query : " + query);

            cs = conn.prepareCall(query);   
            cs.executeUpdate();
            rs = (ResultSet) cs.getResultSet();
            
            while (rs.next()){
            	Vendedor bean = new Vendedor();
            	bean.setId(rs.getInt("ID"));
            	bean.setNombre(rs.getString("NOMBRE"));
            	bean.setTelefono(rs.getLong("TELEFONO"));
            	bean.setCorreo(rs.getString("CORREO"));
            	bean.setUsuario(rs.getString("USUARIO"));
            	bean.setContrasena(rs.getString("CONTRASENA"));
            	bean.setCuentaId(rs.getLong("CUENTA_ID"));
            	vendedores.add(bean);
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
		return vendedores;
	}
}
