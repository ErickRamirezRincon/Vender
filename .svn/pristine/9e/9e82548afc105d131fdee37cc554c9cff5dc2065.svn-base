package mx.com.fincomun.vendedores.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import mx.com.fincomun.vendedores.bean.Producto;
import mx.com.fincomun.vendedores.dao.GeneralDao;

@Service
public class ProductoDao extends GeneralDao {
	
	public boolean updateProductoStock(Producto request){
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		boolean isUpdate = false;
		
		try{
			conn = this.getConnectionOracle();
			
			String in = "UPDATE TENDEROS_ARTICULO SET STOCK = ? WHERE ID = ?";
			
			pstmt = conn.prepareStatement(in.toString());
			pstmt.setInt(1, request.getStock());
			pstmt.setInt(2, request.getId());
			
			int update = pstmt.executeUpdate();
			
			if(update == 1){
				isUpdate = true;
			}
			
		} catch (Exception e) {
			log.error("Error al actualizar producto: " + e);
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

	public Producto getPorId(long idProducto){
		Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Producto response = null;
		try {
            conn = this.getConnectionOracle();
            String query = "SELECT * FROM  TENDEROS_ARTICULO WHERE ID = "+idProducto;

            log.info("query : " + query);

            cs = conn.prepareCall(query);   
            cs.executeUpdate();
            rs = (ResultSet) cs.getResultSet();
            
            while (rs.next()){
            	response = new Producto();
            	response.setId(rs.getInt("ID"));
            	response.setNombre(rs.getString("NOMBRE"));
            	response.setPrecioVenta(rs.getFloat("PRECIO_VENTA"));
            	response.setImagen(rs.getString("IMAGEN"));
            	response.setCodigoQR(rs.getString("CODIGO_QR"));
            	response.setPrecioCosto(rs.getFloat("PRECIO_COSTO"));
            	response.setIdProveedor(rs.getInt("TENDEROS_PROVEEDOR_ID"));
            	response.setStock(rs.getInt("STOCK"));
            	response.setStockInicial(rs.getInt("STOCK_INICIAL"));
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
