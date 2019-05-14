package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.cnx.Cnx;
import model.vo.AlmacenVO;

public class AlmacenDAO {
	
	public static ArrayList<AlmacenVO> ConsultarAlmacen() {
		Cnx cnn = new Cnx();
		PreparedStatement ps = null;
		ArrayList<AlmacenVO> lista = new ArrayList<AlmacenVO>();

		boolean existe = false;

		try {
			String sql = " SELECT RTRIM(LTRIM(A1_CALMA)) AS CODIGO ,RTRIM(LTRIM(A1_CDESCRI)) AS ALMACEN FROM RSFACCAR..AL0004ALMA ORDER BY A1_CALMA ";
			ps = cnn.getConnection().prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				AlmacenVO almacenvo = new AlmacenVO() {
				};
				existe = true;

				almacenvo.setCOD(res.getString("CODIGO"));
				almacenvo.setALMACEN(res.getString("ALMACEN"));
				lista.add(almacenvo);
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