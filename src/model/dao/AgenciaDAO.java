package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.cnx.Cnx;
import model.vo.AgenciaVO;


public class AgenciaDAO {

	public static ArrayList<AgenciaVO> ConsultarAgenciaCAISAC() {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<AgenciaVO> lista = new ArrayList<AgenciaVO>();

		boolean existe = false;

		try {
			String sql = " SELECT TG_CCOD, LTRIM(RTRIM(TG_CCLAVE)) AS TG_CCLAVE, LTRIM(RTRIM(TG_CCLAVE))+' - '+LTRIM(RTRIM(TG_CDESCRI)) AS TG_AGENCIAS FROM RSFACCAR..AL0002TABL Where TG_CCOD='14' Order by TG_CCLAVE ";
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				AgenciaVO agenciavo = new AgenciaVO() {
				};
				existe = true;

				agenciavo.setCOD(res.getString("TG_CCOD"));
				agenciavo.setCLAVE(res.getString("TG_CCLAVE"));
				agenciavo.setAGENCIA(res.getString("TG_AGENCIAS"));
				lista.add(agenciavo);
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
	
	public static ArrayList<AgenciaVO> ConsultarAgenciaANDEX() {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<AgenciaVO> lista = new ArrayList<AgenciaVO>();

		boolean existe = false;

		try {
			String sql = " SELECT TG_CCOD, LTRIM(RTRIM(TG_CCLAVE)) AS TG_CCLAVE, LTRIM(RTRIM(TG_CCLAVE))+' - '+LTRIM(RTRIM(TG_CDESCRI)) AS TG_AGENCIAS FROM RSFACCAR..AL0003TABL Where TG_CCOD='14' Order by TG_CCLAVE ";
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				AgenciaVO agenciavo = new AgenciaVO() {
				};
				existe = true;

				agenciavo.setCOD(res.getString("TG_CCOD"));
				agenciavo.setCLAVE(res.getString("TG_CCLAVE"));
				agenciavo.setAGENCIA(res.getString("TG_AGENCIAS"));
				lista.add(agenciavo);
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
	
	public static ArrayList<AgenciaVO> ConsultarAgenciaSEMILLAS() {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<AgenciaVO> lista = new ArrayList<AgenciaVO>();

		boolean existe = false;

		try {
			String sql = " SELECT TG_CCOD, LTRIM(RTRIM(TG_CCLAVE)) AS TG_CCLAVE, LTRIM(RTRIM(TG_CCLAVE))+' - '+LTRIM(RTRIM(TG_CDESCRI)) AS TG_AGENCIAS FROM RSFACCAR..AL0004TABL Where TG_CCOD='14' Order by TG_CCLAVE ";
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				AgenciaVO agenciavo = new AgenciaVO() {
				};
				existe = true;

				agenciavo.setCOD(res.getString("TG_CCOD"));
				agenciavo.setCLAVE(res.getString("TG_CCLAVE"));
				agenciavo.setAGENCIA(res.getString("TG_AGENCIAS"));
				lista.add(agenciavo);
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
	
	public static ArrayList<AgenciaVO> ConsultarAgenciaSUNNY() {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<AgenciaVO> lista = new ArrayList<AgenciaVO>();

		boolean existe = false;

		try {
			String sql = " SELECT TG_CCOD, LTRIM(RTRIM(TG_CCLAVE)) AS TG_CCLAVE, LTRIM(RTRIM(TG_CCLAVE))+' - '+LTRIM(RTRIM(TG_CDESCRI)) AS TG_AGENCIAS FROM RSFACCAR..AL0016TABL Where TG_CCOD='14' Order by TG_CCLAVE ";
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				AgenciaVO agenciavo = new AgenciaVO() {
				};
				existe = true;

				agenciavo.setCOD(res.getString("TG_CCOD"));
				agenciavo.setCLAVE(res.getString("TG_CCLAVE"));
				agenciavo.setAGENCIA(res.getString("TG_AGENCIAS"));
				lista.add(agenciavo);
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
