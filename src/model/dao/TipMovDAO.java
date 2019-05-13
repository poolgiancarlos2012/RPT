package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.cnx.Cnx;

import model.vo.TipMovVO;

public class TipMovDAO{

    public static ArrayList<TipMovVO> DAO_MostrarTipMov(String codigo, String descripcion) {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<TipMovVO> lista = new ArrayList<TipMovVO>();

		boolean existe = false;

		try {

			StringBuffer SBsql = new StringBuffer(); 
			SBsql.append( " SELECT RTRIM(LTRIM(TM_CCODMOV)) AS CODMOV,UPPER(RTRIM(LTRIM(TM_CDESCRI))) AS DESCRIP FROM RSFACCAR..AL0004TABM WHERE TM_CTIPMOV='E'  " );
			
			if( !codigo.isEmpty()   ){
				SBsql.append( " AND RTRIM(LTRIM(TM_CCODMOV)) ='"+codigo+"' " );
			}			
			if( !descripcion.isEmpty()   ){
				SBsql.append( " AND UPPER(RTRIM(LTRIM(TM_CDESCRI))) LIKE '%"+descripcion+"%' " );
			}
			
			SBsql.append(" ORDER BY TM_CTIPMOV,TM_CCODMOV ");			
			
			String sql = SBsql.toString();			
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				TipMovVO tipmovvo = new TipMovVO() {
				};
				existe = true;

				tipmovvo.setCODIGO(res.getString("CODMOV"));
				tipmovvo.setDESCRIPCION(res.getString("DESCRIP"));
				lista.add(tipmovvo);
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