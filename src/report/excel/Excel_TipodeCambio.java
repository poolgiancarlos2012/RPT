package report.excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.cnx.Cnx;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class Excel_TipodeCambio{

	public Excel_TipodeCambio(String xIni, String xFin) throws Exception{
		
		try {
		
			SXSSFWorkbook book = new SXSSFWorkbook();
			Sheet reporte = book.createSheet("Tipo de Cambio");			

			String NombreArchivo = "Tipo de Cambio " + new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());

			SXSSFRow rowhead = (SXSSFRow) reporte.createRow((short) 0);

			CellStyle headerStylo = book.createCellStyle();
			headerStylo.setAlignment(HorizontalAlignment.CENTER);
			headerStylo.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteHeader = book.createFont();
			fuenteHeader.setFontName("Arial");
			fuenteHeader.setBold(true);
			fuenteHeader.setFontHeightInPoints((short) 9);
			headerStylo.setFont(fuenteHeader);

			Cell cell_fecha = rowhead.createCell((short) 0);
			cell_fecha.setCellStyle(headerStylo);
			cell_fecha.setCellValue("FECHA");

			Cell cell_tpc = rowhead.createCell((short) 1);
			cell_tpc.setCellStyle(headerStylo);
			cell_tpc.setCellValue("TPC");

			Cell cell_tpv = rowhead.createCell((short) 2);
			cell_tpv.setCellStyle(headerStylo);
			cell_tpv.setCellValue("TPV");

			// SQL
			
			SimpleDateFormat Ini = new SimpleDateFormat("yyyyMMdd");
			Date dIni = Ini.parse(xIni);
			Ini.applyPattern("dd/MM/yyyy");

			String newIni;
			newIni = Ini.format(dIni);

			SimpleDateFormat Fin = new SimpleDateFormat("yyyyMMdd");
			Date dFin = Fin.parse(xFin);
			Fin.applyPattern("dd/MM/yyyy");

			String newFin;
			newFin = Fin.format(dFin);
			
			Cnx cnn = new Cnx();
			PreparedStatement ps;
			ResultSet res;
			StringBuilder sql = new StringBuilder();
			
			int numFilaDatos = 1; 
			
			sql.append("	SELECT \n" +
						"TOP 100 PERCENT\n" +
						"CONVERT(VARCHAR(10),XFECCAM2,112) AS 'NUMM', \n" +
						"CONVERT(VARCHAR(10),XFECCAM2,103) AS 'FECHA', \n" +
						"XMEIMP AS 'TPC', \n" +
						"XMEIMP2 AS 'TPV' \n" +
						"FROM RSCONCAR..CTCAMB \n" +
						"WHERE \n" +
						"CONVERT(DATETIME, XFECCAM2, 103) BETWEEN CONVERT(DATETIME,'"+newIni+"',103) AND CONVERT(DATETIME,'"+newFin+"',103) AND \n" +
						"XCODMON='US' \n" +
						"ORDER BY CONVERT(VARCHAR(10),XFECCAM2,112)  ASC	");
						
			ps = cnn.getConnection().prepareStatement(sql.toString());
			res = ps.executeQuery();
			
			while ( res.next() ) {	
				
				
				SXSSFRow row = (SXSSFRow) reporte.createRow((short) numFilaDatos);

				CellStyle rowStyloFECH = book.createCellStyle();
				rowStyloFECH.setAlignment(HorizontalAlignment.CENTER);
				rowStyloFECH.setVerticalAlignment(VerticalAlignment.CENTER);
				org.apache.poi.ss.usermodel.Font fuenteFECH = book.createFont();
				fuenteFECH.setFontName("Arial");
				fuenteFECH.setBold(false);
				fuenteFECH.setFontHeightInPoints((short) 8);
				rowStyloFECH.setFont(fuenteFECH);

				Cell cell_FECH = row.createCell((short) 0);
				cell_FECH.setCellStyle(rowStyloFECH);
				cell_FECH.setCellValue(res.getString("FECHA"));

				
				CellStyle rowStyloTPC = book.createCellStyle();
				rowStyloTPC.setAlignment(HorizontalAlignment.CENTER);
				rowStyloTPC.setVerticalAlignment(VerticalAlignment.CENTER);
				org.apache.poi.ss.usermodel.Font fuenteTPC = book.createFont();
				fuenteTPC.setFontName("Arial");
				fuenteTPC.setBold(false);
				fuenteTPC.setFontHeightInPoints((short) 8);
				rowStyloTPC.setFont(fuenteTPC);
				
				Cell cell_TPC = row.createCell((short) 1);
				cell_TPC.setCellStyle(rowStyloTPC);
				cell_TPC.setCellValue(res.getString("TPC"));
				
				CellStyle rowStyloTPV = book.createCellStyle();
				rowStyloTPV.setAlignment(HorizontalAlignment.CENTER);
				rowStyloTPV.setVerticalAlignment(VerticalAlignment.CENTER);
				org.apache.poi.ss.usermodel.Font fuenteTPV = book.createFont();
				fuenteTPV.setFontName("Arial");
				fuenteTPV.setBold(false);
				fuenteTPV.setFontHeightInPoints((short) 8);
				rowStyloTPV.setFont(fuenteTPV);
				
				Cell cell_TPV = row.createCell((short) 2);
				cell_TPV.setCellStyle(rowStyloTPV);
				cell_TPV.setCellValue(res.getString("TPV"));
			
				numFilaDatos = numFilaDatos + 1;
				
			}
			
			res.close();
			cnn.desconectar();

			File PathC = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();
			System.out.println(PathC);

			String Documents = PathC.toString() + "Users" + "\\" + System.getProperty("user.name") + "\\" + "Documents" + "\\" + "Andina";

			System.out.println(Documents);

			File FolderDown = new File(Documents);
			FolderDown.mkdirs();

			FileOutputStream fileOut = new FileOutputStream( FolderDown + "\\" + NombreArchivo + ".xlsx");
			book.write(fileOut);
			fileOut.close();

			Desktop.getDesktop().open(new File( FolderDown + "\\" + NombreArchivo + ".xlsx"));
		
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Excel_TipodeCambio.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Excel_TipodeCambio.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Excel_EstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	

}