package mx.com.fincomun.vendedores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.request.VentaDirectaRequest;
import mx.com.fincomun.vendedores.bean.request.VentaRequest;
import mx.com.fincomun.vendedores.dao.GeneralDao;

@Service
public class VentaDao extends GeneralDao {
	
	private static Logger log = Logger.getLogger(VentaDao.class);
	
	public int insertVentaPorArticulo(VentaRequest request){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int idVenta = 0;
		
		try{
			int sequenceValue = callSequence("TENDEROS_VENTAS_SEQ");
			
			conn = this.getConnectionOracle();
			
			String in = "INSERT INTO TENDEROS_VENTAS VALUES (?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setInt(1, sequenceValue);
			pstmt.setTimestamp(2, new Timestamp(new Date().getTime()));
			
			java.sql.Clob clobImagen = oracle.sql.CLOB.createTemporary(conn, false, oracle.sql.CLOB.DURATION_SESSION);
			clobImagen.setString(1, request.getCodigoQR());
			pstmt.setClob(3, clobImagen);
			
			
			pstmt.setFloat(4, request.getMontoTotal());
			pstmt.setLong(5, request.getIdUsuario());
			pstmt.setLong(6, request.getIdTipoPago());
			pstmt.setLong(7, request.getIdTipoServicio());
			pstmt.setLong(8, request.getIdVendedor());
			pstmt.setFloat(9, request.getComision());
			
			pstmt.execute();
			
			idVenta = sequenceValue;
			
		} catch (Exception e) {
			log.error("Error al registrar proveedor: " + e);
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
		return idVenta;
	}
	
	public int insertVentaDirecta(VentaDirectaRequest request){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int idVenta = 0;
		
		try{
			int sequenceValue = callSequence("TENDEROS_VENTAS_SEQ");
			
			conn = this.getConnectionOracle();
			
			String in = "INSERT INTO TENDEROS_VENTAS (ID, MONTO_TOTAL, FECHA, TIPO_PAGO_ID, ID_USUARIO, TENDEROS_VENDEDORES_ID) VALUES (?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setInt(1, sequenceValue);
			pstmt.setFloat(2, request.getMontoTotal());
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.setLong(4, request.getIdTipoPago());
			pstmt.setLong(5, request.getIdUsuario());
			pstmt.setLong(6, request.getIdVendedor());
			
			pstmt.execute();
			
			idVenta = sequenceValue;
			
		} catch (Exception e) {
			log.error("Error al registrar proveedor: " + e);
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
		return idVenta;
	}
	
	public boolean insertVentaArticulos(int idVenta, int idArticulo, int numeroArticulos){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		boolean isInsert = true;
		
		try{
			int sequenceValue = callSequence("TENDEROS_VENTA_ARTICULOS_SEQ");
			
			conn = this.getConnectionOracle();
			
			String in = "INSERT INTO TENDEROS_VENTA_ARTICULOS VALUES (?,?,?,?)";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setInt(1, sequenceValue);
			pstmt.setInt(2, idVenta);
			pstmt.setInt(3, idArticulo);
			pstmt.setInt(4, numeroArticulos);
			
			pstmt.execute();
			
		} catch (Exception e) {
			isInsert = false;
			log.error("Error al registrar proveedor: " + e);
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
}
