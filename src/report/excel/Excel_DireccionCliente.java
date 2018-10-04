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

public class Excel_DireccionCliente{

	public Excel_DireccionCliente() throws Exception {
		
		try {
		
			SXSSFWorkbook book = new SXSSFWorkbook();
			Sheet reporte = book.createSheet("Dir.Cli.");	

			String NombreArchivo = "Direccion de los Cliente " + new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());

			SXSSFRow rowhead = (SXSSFRow) reporte.createRow((short) 0);

			CellStyle headerStylo = book.createCellStyle();
			headerStylo.setAlignment(HorizontalAlignment.CENTER);
			headerStylo.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteHeader = book.createFont();
			fuenteHeader.setFontName("Arial");
			fuenteHeader.setBold(true);
			fuenteHeader.setFontHeightInPoints((short) 9);
			headerStylo.setFont(fuenteHeader);

			Cell cell_COD = rowhead.createCell((short) 0);
			cell_COD.setCellStyle(headerStylo);
			cell_COD.setCellValue("COD");
			
			Cell cell_EMPRESA = rowhead.createCell((short) 1);
			cell_EMPRESA.setCellStyle(headerStylo);
			cell_EMPRESA.setCellValue("EMPRESA");
			
			Cell cell_COD_CLIENTE = rowhead.createCell((short) 2);
			cell_COD_CLIENTE.setCellStyle(headerStylo);
			cell_COD_CLIENTE.setCellValue("COD_CLIENTE");
			
			Cell cell_CLIENTE = rowhead.createCell((short) 3);
			cell_CLIENTE.setCellStyle(headerStylo);
			cell_CLIENTE.setCellValue("CLIENTE");
			
			Cell cell_DEPARTAMENTO = rowhead.createCell((short) 4);
			cell_DEPARTAMENTO.setCellStyle(headerStylo);
			cell_DEPARTAMENTO.setCellValue("DEPARTAMENTO");
			
			Cell cell_PROVINCIA = rowhead.createCell((short) 5);
			cell_PROVINCIA.setCellStyle(headerStylo);
			cell_PROVINCIA.setCellValue("PROVINCIA");
			
			Cell cell_DISTRITO = rowhead.createCell((short) 6);
			cell_DISTRITO.setCellStyle(headerStylo);
			cell_DISTRITO.setCellValue("DISTRITO");
			
			Cell cell_DIRECCION = rowhead.createCell((short) 7);
			cell_DIRECCION.setCellStyle(headerStylo);
			cell_DIRECCION.setCellValue("DIRECCION");
			
			Cell cell_ESTADO = rowhead.createCell((short) 8);
			cell_ESTADO.setCellStyle(headerStylo);
			cell_ESTADO.setCellValue("ESTADO");

			int colvar = 256; // Valor por defecto para poder setear el ancho de las columnas
//			
			reporte.setColumnWidth(0, 8 * colvar);
			reporte.setColumnWidth(1, 10 * colvar);
			reporte.setColumnWidth(2, 14 * colvar);
			reporte.setColumnWidth(3, 18 * colvar);
			reporte.setColumnWidth(4, 18 * colvar);
			reporte.setColumnWidth(5, 18 * colvar);
			reporte.setColumnWidth(6, 18 * colvar);
			reporte.setColumnWidth(7, 18 * colvar);
			reporte.setColumnWidth(8, 18 * colvar);
			
			Cnx cnn = new Cnx();
			PreparedStatement ps;
			ResultSet res;
			StringBuilder sql = new StringBuilder();
			
			
			
			sql.append("	SELECT\n" +
						"COD,\n" +
						"EMPRESA,\n" +
						"COD_CLIENTE,\n" +
						"CLIENTE,\n" +
						"CASE COD\n" +
						"								WHEN '0002' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0002CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0005' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0005CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0003' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0003CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0006' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0006CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0004' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0004CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0007' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0007CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0009' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0009CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0016' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0016CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"								WHEN '0017' THEN (SELECT LTRIM(RTRIM(CL_CDEPT)) FROM RSFACCAR..FT0017CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"ELSE ''\n" +
						"END AS 'DEPARTAMENTO',\n" +
						"CASE COD\n" +
						"						WHEN '0002' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0002CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0005' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0005CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0003' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0003CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0006' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0006CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0004' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0004CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0007' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0007CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0009' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0009CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0016' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0016CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0017' THEN (SELECT LTRIM(RTRIM(CL_CPROV)) FROM RSFACCAR..FT0017CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"\n" +
						"ELSE ''\n" +
						"END AS 'PROVINCIA',\n" +
						"CASE COD\n" +
						"								WHEN '0002' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0002TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0005' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0005TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0003' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0003TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0006' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0006TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0004' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0004TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0007' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0007TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0009' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0009TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0016' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0016TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"								WHEN '0017' THEN ((SELECT LTRIM(RTRIM(TG_CDESCRI)) FROM RSFACCAR..AL0017TABL WHERE TG_CCOD='A2' AND TG_CCLAVE=CL_CUBIGEO))\n" +
						"ELSE ''\n" +
						"END AS 'DISTRITO',\n" +
						"CASE COD\n" +
						"						WHEN '0002' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0002CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0005' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0005CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0003' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0003CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0006' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0006CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0004' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0004CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0007' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0007CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0009' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0009CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0016' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0016CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"						WHEN '0017' THEN (SELECT LTRIM(RTRIM(CL_CDIRCLI)) FROM RSFACCAR..FT0017CLIE WHERE CL_CCODCLI = COD_CLIENTE)\n" +
						"ELSE ''\n" +
						"END AS 'DIRECCION',\n" +
						"ESTADO\n" +
						"FROM VIEW_ALL_CLIENT_ACTIVE WHERE COD IN ('0002','0003','0004','0016')	");
			
			System.out.println(sql.toString());
			ps = cnn.getConnection().prepareStatement(sql.toString());
			res = ps.executeQuery();
			
			int numFilaDatos = 1;

			CellStyle rowStyloCOD = book.createCellStyle();
			rowStyloCOD.setAlignment(HorizontalAlignment.CENTER);
			rowStyloCOD.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteCOD = book.createFont();
			fuenteCOD.setFontName("Arial");
			fuenteCOD.setBold(false);
			fuenteCOD.setFontHeightInPoints((short) 8);
			rowStyloCOD.setFont(fuenteCOD);

			CellStyle rowStyloEMPRESA = book.createCellStyle();
			rowStyloEMPRESA.setAlignment(HorizontalAlignment.CENTER);
			rowStyloEMPRESA.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteEMPRESA = book.createFont();
			fuenteEMPRESA.setFontName("Arial");
			fuenteEMPRESA.setBold(false);
			fuenteEMPRESA.setFontHeightInPoints((short) 8);
			rowStyloEMPRESA.setFont(fuenteEMPRESA);

			CellStyle rowStyloCOD_CLIENTE = book.createCellStyle();
			rowStyloCOD_CLIENTE.setAlignment(HorizontalAlignment.CENTER);
			rowStyloCOD_CLIENTE.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteCOD_CLIENTE = book.createFont();
			fuenteCOD_CLIENTE.setFontName("Arial");
			fuenteCOD_CLIENTE.setBold(false);
			fuenteCOD_CLIENTE.setFontHeightInPoints((short) 8);
			rowStyloCOD_CLIENTE.setFont(fuenteCOD_CLIENTE);
			
			CellStyle rowStyloCLIENTE = book.createCellStyle();
			rowStyloCLIENTE.setAlignment(HorizontalAlignment.LEFT);
			rowStyloCLIENTE.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteCLIENTE = book.createFont();
			fuenteCLIENTE.setFontName("Arial");
			fuenteCLIENTE.setBold(false);
			fuenteCLIENTE.setFontHeightInPoints((short) 8);
			rowStyloCLIENTE.setFont(fuenteCLIENTE);
			
			CellStyle rowStyloDEPARTAMENTO = book.createCellStyle();
			rowStyloDEPARTAMENTO.setAlignment(HorizontalAlignment.LEFT);
			rowStyloDEPARTAMENTO.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteDEPARTAMENTO = book.createFont();
			fuenteDEPARTAMENTO.setFontName("Arial");
			fuenteDEPARTAMENTO.setBold(false);
			fuenteDEPARTAMENTO.setFontHeightInPoints((short) 8);
			rowStyloDEPARTAMENTO.setFont(fuenteDEPARTAMENTO);
			
			CellStyle rowStyloPROVINCIA = book.createCellStyle();
			rowStyloPROVINCIA.setAlignment(HorizontalAlignment.LEFT);
			rowStyloPROVINCIA.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuentePROVINCIA = book.createFont();
			fuentePROVINCIA.setFontName("Arial");
			fuentePROVINCIA.setBold(false);
			fuentePROVINCIA.setFontHeightInPoints((short) 8);
			rowStyloPROVINCIA.setFont(fuentePROVINCIA);
			
			CellStyle rowStyloDISTRITO = book.createCellStyle();
			rowStyloDISTRITO.setAlignment(HorizontalAlignment.LEFT);
			rowStyloDISTRITO.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteDISTRITO = book.createFont();
			fuenteDISTRITO.setFontName("Arial");
			fuenteDISTRITO.setBold(false);
			fuenteDISTRITO.setFontHeightInPoints((short) 8);
			rowStyloDISTRITO.setFont(fuenteDISTRITO);
			
			CellStyle rowStyloDIRECCION = book.createCellStyle();
			rowStyloDIRECCION.setAlignment(HorizontalAlignment.LEFT);
			rowStyloDIRECCION.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteDIRECCION = book.createFont();
			fuenteDIRECCION.setFontName("Arial");
			fuenteDIRECCION.setBold(false);
			fuenteDIRECCION.setFontHeightInPoints((short) 8);
			rowStyloDIRECCION.setFont(fuenteDIRECCION);
			
			CellStyle rowStyloESTADO = book.createCellStyle();
			rowStyloESTADO.setAlignment(HorizontalAlignment.CENTER);
			rowStyloESTADO.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteESTADO = book.createFont();
			fuenteESTADO.setFontName("Arial");
			fuenteESTADO.setBold(false);
			fuenteESTADO.setFontHeightInPoints((short) 8);
			rowStyloESTADO.setFont(fuenteESTADO);
			
			while ( res.next() ) {
				
				SXSSFRow row = (SXSSFRow) reporte.createRow((short) numFilaDatos);
				
				Cell cell_rCOD = row.createCell((short) 0);
				cell_rCOD.setCellStyle(rowStyloCOD);
				cell_rCOD.setCellValue(res.getString("COD"));
				
				Cell cell_rEMPRESA = row.createCell((short) 1);
				cell_rEMPRESA.setCellStyle(rowStyloEMPRESA);
				cell_rEMPRESA.setCellValue(res.getString("EMPRESA"));

				Cell cell_rCOD_CLIENTE = row.createCell((short) 2);
				cell_rCOD_CLIENTE.setCellStyle(rowStyloCOD_CLIENTE);
				cell_rCOD_CLIENTE.setCellValue(res.getString("COD_CLIENTE"));
				
				Cell cell_rCLIENTE = row.createCell((short) 3);
				cell_rCLIENTE.setCellStyle(rowStyloCLIENTE);
				cell_rCLIENTE.setCellValue(res.getString("CLIENTE"));
				
				Cell cell_rDEPARTAMENTO = row.createCell((short) 4);
				cell_rDEPARTAMENTO.setCellStyle(rowStyloDEPARTAMENTO);
				cell_rDEPARTAMENTO.setCellValue(res.getString("DEPARTAMENTO"));
				
				Cell cell_rPROVINCIA = row.createCell((short) 5);
				cell_rPROVINCIA.setCellStyle(rowStyloPROVINCIA);
				cell_rPROVINCIA.setCellValue(res.getString("PROVINCIA"));
				
				Cell cell_rDISTRITO = row.createCell((short) 6);
				cell_rDISTRITO.setCellStyle(rowStyloDISTRITO);
				cell_rDISTRITO.setCellValue(res.getString("DISTRITO"));
				
				Cell cell_rDIRECCION = row.createCell((short) 7);
				cell_rDIRECCION.setCellStyle(rowStyloDIRECCION);
				cell_rDIRECCION.setCellValue(res.getString("DIRECCION"));
				
				Cell cell_rESTADO = row.createCell((short) 8);
				cell_rESTADO.setCellStyle(rowStyloESTADO);
				cell_rESTADO.setCellValue(res.getString("ESTADO"));
				
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
		} 
//		catch (SQLException ex) {
//			Logger.getLogger(Excel_EstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
//		}
		
	}

}