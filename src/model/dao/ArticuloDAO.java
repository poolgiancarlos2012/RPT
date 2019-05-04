package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.cnx.Cnx;

import model.vo.ArticuloVO;

public class ArticuloDAO{

	public static ArrayList<ArticuloVO> DAO_MostrarArticulos(String codprod, String prod) {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<ArticuloVO> lista = new ArrayList<ArticuloVO>();
		
		boolean existe = false;
		
		try {

			StringBuffer SBsql = new StringBuffer(); 
			SBsql.append( " SELECT LTRIM(RTRIM(AR_CCODIGO)) AS CODPROD,LTRIM(RTRIM(AR_CDESCRI)) AS PROD,LTRIM(RTRIM(AR_CUNIDAD)) AS UM FROM AL0004ARTI WHERE 1 = 1 " );
			
			if( !codprod.isEmpty()   ){
				SBsql.append( " AND LTRIM(RTRIM(AR_CCODIGO)) = '"+codprod+"' " );
			}			
			if( !prod.isEmpty()   ){
				SBsql.append( " AND LTRIM(RTRIM(AR_CDESCRI)) LIKE '%"+prod+"%' " );
			}
			
			SBsql.append(" ORDER BY LTRIM(RTRIM(AR_CCODIGO))  ASC ");			
			
			String sql = SBsql.toString();			
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				ArticuloVO Productovo = new ArticuloVO() {
				};
				existe = true;

				Productovo.setCODPROD(res.getString("CODPROD"));
				Productovo.setPROD(res.getString("PROD"));
				Productovo.setUM(res.getString("UM"));
				lista.add(Productovo);
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