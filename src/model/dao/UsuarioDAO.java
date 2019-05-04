package model.dao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import model.cnx.Cnx;
import model.vo.UsuarioVO;
import controller.Manager;

public class UsuarioDAO {

	private Manager mgr = new Manager();
	
	public UsuarioVO ValidarAcceso(String user, String pass) throws Exception {

		Cnx cnn = new Cnx();
		PreparedStatement ps = null;

		UsuarioVO usuariovo = new UsuarioVO();
		boolean existe = false;

		try {

			String sql = " SELECT \n"
					+ " VEND AS 'DATOS',\n"
					+ " CAST(DecryptByPassPhrase('ClaveUsuarios', USUARIO) AS VARCHAR(200)) AS ALIAS,\n"
					+ " DNI AS 'USU',\n"
					+ " EMPRESA AS 'CARGO',\n"
					+ " 1 AS ESTADO,\n"
					+ " RUTA AS RUTA\n"
					+ " FROM \n"
					+ " VIEW_DISTRIBUCION_PERSONAL \n"
					+ " WHERE \n"
					+ " EMPRESA IN ('CAISAC','SUPERV_CRED','OPERACIONES','ADMINISTRADOR','GERENCIA','JEFE_OPE','CONTABILIDAD') AND\n"
					+ " CAST(DecryptByPassPhrase('ClaveUsuarios', USUARIO) AS VARCHAR(200)) = ? AND\n"
					+ " CAST(DecryptByPassPhrase('ClaveUsuarios', CLAVE) AS VARCHAR(200)) = ? ";

			ps = cnn.getConnection().prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);

			ResultSet res = ps.executeQuery();

			while (res.next()) {
				existe = true;

				usuariovo.setAlias(res.getString("ALIAS"));
				usuariovo.setUsuario(res.getString("USU"));
				usuariovo.setPassword(res.getString("USU"));
				usuariovo.setCargo(res.getString("CARGO"));
				usuariovo.setRuta(res.getString("RUTA"));
				
				System.out.println("DAO ALIAS: "+res.getString("ALIAS")+"\t"+" DNI: "+res.getString("USU"));
				
				mgr.User = res.getString("USU");
				mgr.Alias = res.getString("ALIAS");
				mgr.Cargo = res.getString("CARGO");

			}

			res.close();
			cnn.desconectar();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}

		if (existe) {
			return usuariovo;
		} else {
			return null;
		}
	}

	public boolean ActualizarDirectoryDownload(String dni, String ruta) throws Exception {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;

		String sql = " UPDATE PERSONAL SET RUTA = ? WHERE DNI = ?";

		System.out.println(dni);
		System.out.println(ruta);
		
		ps = cnn.getConnection().prepareStatement(sql);
		ps.setString(1, ruta);
		ps.setString(2, dni);

		int rowsUpdated = ps.executeUpdate();

		cnn.desconectar();
		ps.close();

		if (rowsUpdated > 0) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean ActualizarContrasenia(String xalias,String NewPass) throws Exception {
		
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;

		String sql = " UPDATE PERSONAL SET CLAVE = EncryptByPassPhrase('ClaveUsuarios', ? ) WHERE CAST(DecryptByPassPhrase('ClaveUsuarios', USUARIO) AS VARCHAR(200)) = ? ";

		System.out.println(xalias);
		System.out.println(NewPass);
		
		ps = cnn.getConnection().prepareStatement(sql);
		ps.setString(1, NewPass);
		ps.setString(2, xalias);

		int rowsUpdated = ps.executeUpdate();

		cnn.desconectar();
		ps.close();

		if (rowsUpdated > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	

}
