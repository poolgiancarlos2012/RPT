package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.cnx.Cnx;
import model.vo.ProveedorVO;

public class ProveedorDAO {
	
	public static ArrayList<ProveedorVO> DAO_MostrarProveedor(String codigo, String proveedor) {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<ProveedorVO> lista = new ArrayList<ProveedorVO>();

		boolean existe = false;

		try {

			StringBuffer SBsql = new StringBuffer(); 
			SBsql.append( " SELECT AC_CVANEXO,RTRIM(LTRIM(AC_CCODIGO)) AS CODIGO,RTRIM(LTRIM(AC_CNOMBRE)) AS PROVEEDOR FROM RSCONCAR..CP0004MAES WHERE AC_CVANEXO='P' " );
			
			if( !codigo.isEmpty()   ){
				SBsql.append( " AND AC_CCODIGO ='"+codigo+"' " );
			}			
			if( !proveedor.isEmpty()   ){
				SBsql.append( " AND AC_CNOMBRE LIKE '%"+proveedor+"%' " );
			}
			
			SBsql.append(" ORDER BY AC_CCODIGO ");			
			
			String sql = SBsql.toString();			
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				ProveedorVO proveedorvo = new ProveedorVO() {
				};
				existe = true;

				proveedorvo.setCODIGO(res.getString("CODIGO"));
				proveedorvo.setPROVEEDOR(res.getString("PROVEEDOR"));
				lista.add(proveedorvo);
			}

			res.close();
			cnn.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		if (existe) {
			return lista;
		} else {
			return null;
		}

	}
	
}