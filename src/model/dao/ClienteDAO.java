package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.cnx.Cnx;

import model.vo.ClienteVO;

public class ClienteDAO{

	public static ArrayList<ClienteVO> DAO_MostrarClientes(String cod_cliente, String razon_social) {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<ClienteVO> lista = new ArrayList<ClienteVO>();

		boolean existe = false;

		try {

			StringBuffer SBsql = new StringBuffer(); 
			SBsql.append( " SELECT TOP 100 COD_CLIENTE,CLIENTE FROM VIEW_ALL_CLIENT_ACTIVE WHERE 1 = 1 " );
			
			if( !cod_cliente.isEmpty()   ){
				SBsql.append( " AND COD_CLIENTE ='"+cod_cliente+"' " );
			}			
			if( !razon_social.isEmpty()   ){
				SBsql.append( " AND CLIENTE LIKE '%"+razon_social+"%' " );
			}
			
			SBsql.append(" GROUP BY COD_CLIENTE,CLIENTE ORDER BY COD_CLIENTE ASC ");			
			
			String sql = SBsql.toString();			
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				ClienteVO clientevo = new ClienteVO() {
				};
				existe = true;

				clientevo.setCODIGO_CLIENTE(res.getString("COD_CLIENTE"));
				clientevo.setCLIENTE(res.getString("CLIENTE"));
				lista.add(clientevo);
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