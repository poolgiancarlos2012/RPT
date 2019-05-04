package report.excel;

import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.cnx.Cnx;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class Excel_EstadoCuentaFecha{

	public Excel_EstadoCuentaFecha(String xcod_cliente, boolean xAllClientes, String xCAISAC , String xANDEX , String xSEMILLAS , String xSUNNY , String xstrage0002,String xstrage0003,String xstrage0004,String xstrage0016 , String xstriniemi , String xstrfinemi, String strPendCancel, String strAbo, String  TMP ) throws Exception{
	
		reporteEstadoCuentaFecha( xcod_cliente , xAllClientes , xCAISAC , xANDEX , xSEMILLAS , xSUNNY , xstrage0002,xstrage0003,xstrage0004,xstrage0016,xstriniemi,xstrfinemi, strPendCancel, strAbo, TMP );
		
	}
	
	
	public static void reporteEstadoCuentaFecha(String xcod_cliente , boolean xAllClientes , String xCAISAC , String xANDEX , String xSEMILLAS , String xSUNNY , String xstrage0002, String xstrage0003, String xstrage0004, String xstrage0016, String xstriniemi , String xstrfinemi, String strPendCancel, String strAbo,String  TMP ) throws Exception {
		
		
		
		try {
			
//			String TMP = java.util.UUID.randomUUID().toString().replaceAll("-", "");
//			System.out.println(TMP);
			
			SXSSFWorkbook book = new SXSSFWorkbook();
			Sheet reporte = book.createSheet("Est.Cu.Fecha");		


			SimpleDateFormat IniEmi = new SimpleDateFormat("yyyyMMdd");
			Date dIniEmi = IniEmi.parse(xstriniemi);
			IniEmi.applyPattern("dd/MM/yyyy");

			String newIniEmi;
			newIniEmi = IniEmi.format(dIniEmi);


			SimpleDateFormat FinEmi = new SimpleDateFormat("yyyyMMdd");
			Date dFinEmi = FinEmi.parse(xstrfinemi);
			FinEmi.applyPattern("dd/MM/yyyy");

			String newFinEmi;
			newFinEmi = IniEmi.format(dFinEmi);

			CellStyle tituloStylo = book.createCellStyle();
			tituloStylo.setAlignment(HorizontalAlignment.CENTER);
			tituloStylo.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteTitle = book.createFont();
			fuenteTitle.setFontName("Arial");
			fuenteTitle.setBold(true);
			fuenteTitle.setFontHeightInPoints((short) 11);			
			tituloStylo.setFont(fuenteTitle);
			
			SXSSFRow rowtitle = (SXSSFRow) reporte.createRow((short) 1);
			Cell celdaTiltle = rowtitle.createCell((short) 1);
			celdaTiltle.setCellStyle(tituloStylo);
			celdaTiltle.setCellValue("ESTADO DE CUENTA POR CLIENTE DEL "+newIniEmi+" HASTA "+newFinEmi);
			reporte.addMergedRegion(new CellRangeAddress(
                        1, // mention first row here
                        1, //mention last row here, it is 1 as we are doing a column wise merging
                        1, //mention first column of merging
                        8  //mention last column to include in merge
                        ));
			
			
			CellStyle headerStylo = book.createCellStyle();
			headerStylo.setAlignment(HorizontalAlignment.CENTER);
			headerStylo.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteHeader = book.createFont();
			fuenteHeader.setFontName("Arial");
			fuenteHeader.setBold(true);
			fuenteHeader.setFontHeightInPoints((short) 9);			
			headerStylo.setFont(fuenteHeader);
			
			SXSSFRow  rowhead  = (SXSSFRow) reporte.createRow((short) 5);
			String NombreArchivo = "EstadoCuentaFecha " + new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());
						
			Cell cell_empresa = rowhead.createCell((short) 0);
			cell_empresa.setCellStyle(headerStylo);
			cell_empresa.setCellValue("EMPRESA");
			
			Cell cell_cliente = rowhead.createCell((short) 1);
			cell_cliente.setCellStyle(headerStylo);
			cell_cliente.setCellValue("CLIENTE");
			
			Cell cell_nombre = rowhead.createCell((short) 2);
			cell_nombre.setCellStyle(headerStylo);
			cell_nombre.setCellValue("NOMBRE");
			
			Cell cell_td = rowhead.createCell((short) 3);
			cell_td.setCellStyle(headerStylo);
			cell_td.setCellValue("TD");
			
			Cell cell_nro = rowhead.createCell((short) 4);
			cell_nro.setCellStyle(headerStylo);
			cell_nro.setCellValue("NUMERO");
			
			Cell cell_emision = rowhead.createCell((short) 5);
			cell_emision.setCellStyle(headerStylo);
			cell_emision.setCellValue("EMISION");
			
			Cell cell_vcto = rowhead.createCell((short) 6);
			cell_vcto.setCellStyle(headerStylo);
			cell_vcto.setCellValue("VENCIMIENTO");
			
			Cell cell_mon = rowhead.createCell((short) 7);
			cell_mon.setCellStyle(headerStylo);
			cell_mon.setCellValue("MONEDA");
			
			Cell cell_imp = rowhead.createCell((short) 8);
			cell_imp.setCellStyle(headerStylo);
			cell_imp.setCellValue("IMPORTE");
			
			Cell cell_sald = rowhead.createCell((short) 9);
			cell_sald.setCellStyle(headerStylo);
			cell_sald.setCellValue("SALDO");
			
			int colvar = 256; // Valor por defecto para poder setear el ancho de las columnas
//			
			reporte.setColumnWidth(0, 10 * colvar);
			reporte.setColumnWidth(1, 13 * colvar);
			reporte.setColumnWidth(2, 25 * colvar);
			reporte.setColumnWidth(3, 5 * colvar);
			reporte.setColumnWidth(4, 18 * colvar);
			reporte.setColumnWidth(5, 18 * colvar);
			reporte.setColumnWidth(6, 18 * colvar);
//			reporte.autoSizeColumn(1);
			
			Cnx cnn = new Cnx();
			PreparedStatement ps;
			ResultSet res;
			StringBuilder sql = new StringBuilder();
			
			String tabla = TMP;
			
			sql.append("EXECUTE SP_ACCOUNT_STATUS_DATE\n" +
						" '"+ tabla +"', \n " +
						" '"+ xcod_cliente +"',\n" +
						" '"+ xCAISAC +"' , '"+ xANDEX +"' , '"+ xSEMILLAS +"' , '"+ xSUNNY +"',\n" +
						" '"+ xstrage0002 +"' , '"+ xstrage0003 +"' , '"+ xstrage0004 +"' , '"+ xstrage0016 +"',\n" +
						" '"+ xstriniemi +"',\n" +
						" '"+ xstrfinemi +"',\n" +
						" '"+ strPendCancel +"' "
						+ "");
			
			System.out.println(sql.toString());
			ps = cnn.getConnection().prepareStatement(sql.toString());
			res = ps.executeQuery();
			
			String COD = "";
			String EMP = "";
			String CLIENTE = "";
			String CODEMPRESA = "";
			String NOMBRE = "";
			String TD = "";
			String NUMERO = "";
			String EMISION = "";
			String VENCIMIENTO = "";
			String MONEDA = "";
			Double IMPORTE = 0.0;
			Double SALDO = 0.0;

			int numFilaDatos = 6; 
			
			int nrow = 0;
			
			while ( res.next() ) {

				/********************* PENDIENTES Y CANCELADOS **************************/
				
				if (strPendCancel.equals("PC")) {

					COD = res.getString("COD");

					if ( CODEMPRESA.equals(res.getString("COD")) &&  CLIENTE.equals(res.getString("CD_CCODCLI").replaceAll(" ", "")) ) {
						numFilaDatos = numFilaDatos + 1;
					} else {
						if (nrow == 0) {

							/********************* LA PRIMERA FILA **************************/

							if (res.getString("COD").equals("0002")) {
								EMP = "CAISAC";
							}

							if (res.getString("COD").equals("0003")) {
								EMP = "ANDEX";
							}

							if (res.getString("COD").equals("0004")) {
								EMP = "SEMILLAS";
							}

							if (res.getString("COD").equals("0016")) {
								EMP = "SUNNY";
							}
							
							SXSSFRow row = (SXSSFRow) reporte.createRow((short) numFilaDatos);

							CellStyle rowStyloEMP = book.createCellStyle();
							rowStyloEMP.setAlignment(HorizontalAlignment.CENTER);
							rowStyloEMP.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteEMP = book.createFont();
							fuenteEMP.setFontName("Arial");
							fuenteEMP.setBold(false);
							fuenteEMP.setFontHeightInPoints((short) 8);
							rowStyloEMP.setFont(fuenteEMP);

							Cell cell_rempresa = row.createCell((short) 0);
							cell_rempresa.setCellStyle(rowStyloEMP);
							cell_rempresa.setCellValue(EMP);

							CellStyle rowStyloCLI = book.createCellStyle();
							rowStyloCLI.setAlignment(HorizontalAlignment.CENTER);
							rowStyloCLI.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteCLI = book.createFont();
							fuenteCLI.setFontName("Arial");
							fuenteCLI.setBold(false);
							fuenteCLI.setFontHeightInPoints((short) 8);
							rowStyloCLI.setFont(fuenteEMP);

							Cell cell_rcliente = row.createCell((short) 1);
							cell_rcliente.setCellStyle(rowStyloCLI);
							cell_rcliente.setCellValue(res.getString("CD_CCODCLI").replaceAll(" ", ""));

							CellStyle rowStyloNOM = book.createCellStyle();
							rowStyloNOM.setAlignment(HorizontalAlignment.LEFT);
							rowStyloNOM.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteNOM = book.createFont();
							fuenteNOM.setFontName("Arial");
							fuenteNOM.setBold(false);
							fuenteNOM.setFontHeightInPoints((short) 8);
							rowStyloNOM.setFont(fuenteNOM);

							Cell cell_rnombre = row.createCell((short) 2);
							cell_rnombre.setCellStyle(rowStyloNOM);
							cell_rnombre.setCellValue(res.getString("CL_CNOMCLI"));

							row.createCell((short) 3).setCellValue(" ");

							numFilaDatos = numFilaDatos + 1;

						} else {
							numFilaDatos = numFilaDatos + 1;
							
							String EMP2="";
							if (res.getString("COD").equals("0002")) {
								EMP2 = "CAISAC";
							}

							if (res.getString("COD").equals("0003")) {
								EMP2 = "ANDEX";
							}

							if (res.getString("COD").equals("0004")) {
								EMP2 = "SEMILLAS";
							}

							if (res.getString("COD").equals("0016")) {
								EMP2 = "SUNNY";
							}

							SXSSFRow row2 = (SXSSFRow) reporte.createRow((short) numFilaDatos);

							CellStyle rowStyloEMP = book.createCellStyle();
							rowStyloEMP.setAlignment(HorizontalAlignment.CENTER);
							rowStyloEMP.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteEMP = book.createFont();
							fuenteEMP.setFontName("Arial");
							fuenteEMP.setBold(false);
							fuenteEMP.setFontHeightInPoints((short) 8);
							rowStyloEMP.setFont(fuenteEMP);

							Cell cell_rempresa = row2.createCell((short) 0);
							cell_rempresa.setCellStyle(rowStyloEMP);
							cell_rempresa.setCellValue(EMP2);

							CellStyle rowStyloCLI = book.createCellStyle();
							rowStyloCLI.setAlignment(HorizontalAlignment.CENTER);
							rowStyloCLI.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteCLI = book.createFont();
							fuenteCLI.setFontName("Arial");
							fuenteCLI.setBold(false);
							fuenteCLI.setFontHeightInPoints((short) 8);
							rowStyloCLI.setFont(fuenteEMP);

							Cell cell_rcliente = row2.createCell((short) 1);
							cell_rcliente.setCellStyle(rowStyloCLI);
							cell_rcliente.setCellValue(res.getString("CD_CCODCLI").replaceAll(" ", ""));

							CellStyle rowStyloNOM = book.createCellStyle();
							rowStyloNOM.setAlignment(HorizontalAlignment.LEFT);
							rowStyloNOM.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteNOM = book.createFont();
							fuenteNOM.setFontName("Arial");
							fuenteNOM.setBold(false);
							fuenteNOM.setFontHeightInPoints((short) 8);
							rowStyloNOM.setFont(fuenteNOM);

							Cell cell_rnombre = row2.createCell((short) 2);
							cell_rnombre.setCellStyle(rowStyloNOM);
							cell_rnombre.setCellValue(res.getString("CL_CNOMCLI"));

							row2.createCell((short) 3).setCellValue(" ");

							numFilaDatos = numFilaDatos + 1;
						}
					}

					String EmiTime = res.getString("CD_DFECDOC");
					LocalDateTime datetimeEmi = LocalDateTime.parse(EmiTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
					String StrEmi = datetimeEmi.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

					String VctoTime = res.getString("CD_DFECVEN");
					LocalDateTime datetimeVcto = LocalDateTime.parse(VctoTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
					String StrVcto = datetimeVcto.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

					CODEMPRESA = res.getString("COD");
					CLIENTE = res.getString("CD_CCODCLI").replaceAll(" ", "");
					NOMBRE = res.getString("CL_CNOMCLI");
					TD = res.getString("CD_CTIPDOC");
					NUMERO = res.getString("CD_CNRODOC");
					EMISION = StrEmi;
					VENCIMIENTO = StrVcto;
					MONEDA = res.getString("CD_CTIPMON");

					if (MONEDA.equals("US")) {
						IMPORTE = res.getDouble("CD_NIMPTUS");
						SALDO = res.getDouble("SALDOUS");
					}

					if (MONEDA.equals("MN")) {
						IMPORTE = res.getDouble("CD_NIMPTMN");
						SALDO = res.getDouble("SALDOMN");
					}

					SXSSFRow row3 = (SXSSFRow) reporte.createRow((short) numFilaDatos);

					int signoA = 0;
					int signoB = 0;
					if (TD.equals("NC") || TD.equals("PA")) {
						signoA = -1;
						signoB = 1;
					} else {
						signoA = 1;
						signoB = -1;
					}

					CellStyle rowStyloXTD = book.createCellStyle();
					rowStyloXTD.setAlignment(HorizontalAlignment.CENTER);
					rowStyloXTD.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXTD = book.createFont();
					fuenteRowXTD.setFontName("Arial");
					fuenteRowXTD.setBold(false);
					fuenteRowXTD.setFontHeightInPoints((short) 8);
					rowStyloXTD.setFont(fuenteRowXTD);

					Cell cell_XTD = row3.createCell((short) 3);
					cell_XTD.setCellStyle(rowStyloXTD);
					cell_XTD.setCellValue(TD);

					CellStyle rowStyloXNRO = book.createCellStyle();
					rowStyloXNRO.setAlignment(HorizontalAlignment.LEFT);
					rowStyloXNRO.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXNRO = book.createFont();
					fuenteRowXNRO.setFontName("Arial");
					fuenteRowXNRO.setBold(false);
					fuenteRowXNRO.setFontHeightInPoints((short) 8);
					rowStyloXNRO.setFont(fuenteRowXNRO);

					Cell cell_XNRO = row3.createCell((short) 4);
					cell_XNRO.setCellStyle(rowStyloXNRO);
					cell_XNRO.setCellValue(NUMERO);

					CellStyle rowStyloXEMI = book.createCellStyle();
					rowStyloXEMI.setAlignment(HorizontalAlignment.CENTER);
					rowStyloXEMI.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXEMI = book.createFont();
					fuenteRowXEMI.setFontName("Arial");
					fuenteRowXEMI.setBold(false);
					fuenteRowXEMI.setFontHeightInPoints((short) 8);
					rowStyloXEMI.setFont(fuenteRowXEMI);

					Cell cell_XEMI = row3.createCell((short) 5);
					cell_XEMI.setCellStyle(rowStyloXEMI);
					cell_XEMI.setCellValue(EMISION);

					CellStyle rowStyloXVCTO = book.createCellStyle();
					rowStyloXVCTO.setAlignment(HorizontalAlignment.CENTER);
					rowStyloXVCTO.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXVCTO = book.createFont();
					fuenteRowXVCTO.setFontName("Arial");
					fuenteRowXVCTO.setBold(false);
					fuenteRowXVCTO.setFontHeightInPoints((short) 8);
					rowStyloXVCTO.setFont(fuenteRowXVCTO);

					Cell cell_XVCTO = row3.createCell((short) 6);
					cell_XVCTO.setCellStyle(rowStyloXVCTO);
					cell_XVCTO.setCellValue(VENCIMIENTO);

					CellStyle rowStyloXMON = book.createCellStyle();
					rowStyloXMON.setAlignment(HorizontalAlignment.CENTER);
					rowStyloXMON.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXMON = book.createFont();
					fuenteRowXMON.setFontName("Arial");
					fuenteRowXMON.setBold(false);
					fuenteRowXMON.setFontHeightInPoints((short) 8);
					rowStyloXMON.setFont(fuenteRowXMON);

					Cell cell_XMON = row3.createCell((short) 7);
					cell_XMON.setCellStyle(rowStyloXMON);
					cell_XMON.setCellValue(MONEDA);

					CellStyle rowStyloXIMP = book.createCellStyle();
					rowStyloXIMP.setAlignment(HorizontalAlignment.RIGHT);
					rowStyloXIMP.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXIMP = book.createFont();
					fuenteRowXIMP.setFontName("Arial");
					fuenteRowXIMP.setBold(false);
					fuenteRowXIMP.setFontHeightInPoints((short) 8);
					rowStyloXIMP.setFont(fuenteRowXIMP);

					Cell cell_XIMP = row3.createCell((short) 8);
					cell_XIMP.setCellStyle(rowStyloXIMP);
					cell_XIMP.setCellValue(signoA * IMPORTE);

					CellStyle rowStyloXSALD = book.createCellStyle();
					rowStyloXSALD.setAlignment(HorizontalAlignment.RIGHT);
					rowStyloXSALD.setVerticalAlignment(VerticalAlignment.CENTER);
					org.apache.poi.ss.usermodel.Font fuenteRowXSALD = book.createFont();
					fuenteRowXSALD.setFontName("Arial");
					fuenteRowXSALD.setBold(false);
					fuenteRowXSALD.setFontHeightInPoints((short) 8);
					rowStyloXSALD.setFont(fuenteRowXSALD);

					Cell cell_XSALD = row3.createCell((short) 9);
					cell_XSALD.setCellStyle(rowStyloXSALD);
					cell_XSALD.setCellValue(signoA * SALDO);

					if (strAbo.equals("ABO")) {

						PreparedStatement psLiq;
						ResultSet resLiq;
						StringBuilder sqlliquiq = new StringBuilder();

						sqlliquiq.append("	 SELECT\n"
								+ "TIPO,\n"
								+ "NUMLIQ,\n"
								+ "CONVERT(NVARCHAR(MAX), FECHA, 103) AS FECHA,\n"
								+ "MONTOMN,\n"
								+ "MONTOUS\n"
								+ "FROM \n"
								+ tabla
								+ " WHERE \n"
								+ " COD = '" + COD + "' AND\n"
								+ " CODCLIE = '" + CLIENTE + "' AND\n"
								+ " TIPDOC = '" + TD + "' AND\n"
								+ " NRODOC = '" + NUMERO + "'");

						System.out.println(sqlliquiq);

						psLiq = cnn.getConnection().prepareStatement(sqlliquiq.toString());
						resLiq = psLiq.executeQuery();

						while (resLiq.next()) {
							numFilaDatos = numFilaDatos + 1;

							SXSSFRow row4 = (SXSSFRow) reporte.createRow((short) numFilaDatos);

							CellStyle rowStyloATIP = book.createCellStyle();
							rowStyloATIP.setAlignment(HorizontalAlignment.CENTER);
							rowStyloATIP.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteRowATIP = book.createFont();
							fuenteRowATIP.setFontName("Arial");
							fuenteRowATIP.setBold(false);
							fuenteRowATIP.setFontHeightInPoints((short) 8);
							rowStyloATIP.setFont(fuenteRowATIP);

							Cell cell_ATIP = row4.createCell((short) 3);
							cell_ATIP.setCellStyle(rowStyloATIP);
							cell_ATIP.setCellValue(resLiq.getString("TIPO"));

							CellStyle rowStyloALIQ = book.createCellStyle();
							rowStyloALIQ.setAlignment(HorizontalAlignment.LEFT);
							rowStyloALIQ.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteRowALIQ = book.createFont();
							fuenteRowALIQ.setFontName("Arial");
							fuenteRowALIQ.setBold(false);
							fuenteRowALIQ.setFontHeightInPoints((short) 8);
							rowStyloALIQ.setFont(fuenteRowALIQ);

							Cell cell_ALIQ = row4.createCell((short) 4);
							cell_ALIQ.setCellStyle(rowStyloALIQ);
							cell_ALIQ.setCellValue(resLiq.getString("NUMLIQ"));

							CellStyle rowStyloAFECH = book.createCellStyle();
							rowStyloAFECH.setAlignment(HorizontalAlignment.CENTER);
							rowStyloAFECH.setVerticalAlignment(VerticalAlignment.CENTER);
							org.apache.poi.ss.usermodel.Font fuenteRowAFECH = book.createFont();
							fuenteRowAFECH.setFontName("Arial");
							fuenteRowAFECH.setBold(false);
							fuenteRowAFECH.setFontHeightInPoints((short) 8);
							rowStyloAFECH.setFont(fuenteRowAFECH);

							Cell cell_AFECH = row4.createCell((short) 5);
							cell_AFECH.setCellStyle(rowStyloAFECH);
							cell_AFECH.setCellValue(resLiq.getString("FECHA"));

							if (MONEDA.equals("US")) {
								CellStyle rowStyloAMUS = book.createCellStyle();
								rowStyloAMUS.setAlignment(HorizontalAlignment.RIGHT);
								rowStyloAMUS.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteRowAMUS = book.createFont();
								fuenteRowAMUS.setFontName("Arial");
								fuenteRowAMUS.setBold(false);
								fuenteRowAMUS.setFontHeightInPoints((short) 8);
								rowStyloAMUS.setFont(fuenteRowAMUS);

								Cell cell_AMUS = row4.createCell((short) 8);
								cell_AMUS.setCellStyle(rowStyloAMUS);
								cell_AMUS.setCellValue(resLiq.getDouble("MONTOUS") * signoB);
							}

							if (MONEDA.equals("MN")) {
								CellStyle rowStyloAMMN = book.createCellStyle();
								rowStyloAMMN.setAlignment(HorizontalAlignment.RIGHT);
								rowStyloAMMN.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteRowAMMN = book.createFont();
								fuenteRowAMMN.setFontName("Arial");
								fuenteRowAMMN.setBold(false);
								fuenteRowAMMN.setFontHeightInPoints((short) 8);
								rowStyloAMMN.setFont(fuenteRowAMMN);

								Cell cell_AMMN = row4.createCell((short) 8);
								cell_AMMN.setCellStyle(rowStyloAMMN);
								cell_AMMN.setCellValue(resLiq.getDouble("MONTOMN") * signoB);
							}
						}

						resLiq.close();
					}
					nrow++;
				}
				
				/*********************PENDIENTES**************************/
				
				String xmon = res.getString("CD_CTIPMON");
				Double xsald = 0.0;
				
				if(xmon.equals("MN")){
					xsald = res.getDouble("SALDOMN");
				}
				
				if(xmon.equals("US")){
					xsald = res.getDouble("SALDOUS");
				}
				
				if (strPendCancel.equals("P")) {
					if (!xsald.equals(0.0)) {						

						COD = res.getString("COD");						
						
						if ( CODEMPRESA.equals(res.getString("COD")) &&  CLIENTE.equals(res.getString("CD_CCODCLI").replaceAll(" ", "")) ) {
							numFilaDatos = numFilaDatos + 1;
						} else {
							if (nrow == 0) {

								if (res.getString("COD").equals("0002")) {
									EMP = "CAISAC";
								}

								if (res.getString("COD").equals("0003")) {
									EMP = "ANDEX";
								}

								if (res.getString("COD").equals("0004")) {
									EMP = "SEMILLAS";
								}

								if (res.getString("COD").equals("0016")) {
									EMP = "SUNNY";
								}
								
								SXSSFRow row = (SXSSFRow) reporte.createRow((short) numFilaDatos);

								CellStyle rowStyloEMP = book.createCellStyle();
								rowStyloEMP.setAlignment(HorizontalAlignment.CENTER);
								rowStyloEMP.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteEMP = book.createFont();
								fuenteEMP.setFontName("Arial");
								fuenteEMP.setBold(false);
								fuenteEMP.setFontHeightInPoints((short) 8);
								rowStyloEMP.setFont(fuenteEMP);

								Cell cell_rempresa = row.createCell((short) 0);
								cell_rempresa.setCellStyle(rowStyloEMP);
								cell_rempresa.setCellValue(EMP);
//								cell_rempresa.setCellValue(COD);

								CellStyle rowStyloCLI = book.createCellStyle();
								rowStyloCLI.setAlignment(HorizontalAlignment.CENTER);
								rowStyloCLI.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteCLI = book.createFont();
								fuenteCLI.setFontName("Arial");
								fuenteCLI.setBold(false);
								fuenteCLI.setFontHeightInPoints((short) 8);
								rowStyloCLI.setFont(fuenteEMP);

								Cell cell_rcliente = row.createCell((short) 1);
								cell_rcliente.setCellStyle(rowStyloCLI);
								cell_rcliente.setCellValue(res.getString("CD_CCODCLI").replaceAll(" ", ""));

								CellStyle rowStyloNOM = book.createCellStyle();
								rowStyloNOM.setAlignment(HorizontalAlignment.LEFT);
								rowStyloNOM.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteNOM = book.createFont();
								fuenteNOM.setFontName("Arial");
								fuenteNOM.setBold(false);
								fuenteNOM.setFontHeightInPoints((short) 8);
								rowStyloNOM.setFont(fuenteNOM);

								Cell cell_rnombre = row.createCell((short) 2);
								cell_rnombre.setCellStyle(rowStyloNOM);
								cell_rnombre.setCellValue(res.getString("CL_CNOMCLI"));

								row.createCell((short) 3).setCellValue(" ");

								numFilaDatos = numFilaDatos + 1;

							} else {
								numFilaDatos = numFilaDatos + 1;
								
								String EMP2="";
								if (res.getString("COD").equals("0002")) {
									EMP2 = "CAISAC";
								}

								if (res.getString("COD").equals("0003")) {
									EMP2 = "ANDEX";
								}

								if (res.getString("COD").equals("0004")) {
									EMP2 = "SEMILLAS";
								}

								if (res.getString("COD").equals("0016")) {
									EMP2 = "SUNNY";
								}

								SXSSFRow row2 = (SXSSFRow) reporte.createRow((short) numFilaDatos);

								CellStyle rowStyloEMP = book.createCellStyle();
								rowStyloEMP.setAlignment(HorizontalAlignment.CENTER);
								rowStyloEMP.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteEMP = book.createFont();
								fuenteEMP.setFontName("Arial");
								fuenteEMP.setBold(false);
								fuenteEMP.setFontHeightInPoints((short) 8);
								rowStyloEMP.setFont(fuenteEMP);

								Cell cell_rempresa = row2.createCell((short) 0);
								cell_rempresa.setCellStyle(rowStyloEMP);
//								cell_rempresa.setCellValue(EMP);
								cell_rempresa.setCellValue(EMP2);

								CellStyle rowStyloCLI = book.createCellStyle();
								rowStyloCLI.setAlignment(HorizontalAlignment.CENTER);
								rowStyloCLI.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteCLI = book.createFont();
								fuenteCLI.setFontName("Arial");
								fuenteCLI.setBold(false);
								fuenteCLI.setFontHeightInPoints((short) 8);
								rowStyloCLI.setFont(fuenteEMP);

								Cell cell_rcliente = row2.createCell((short) 1);
								cell_rcliente.setCellStyle(rowStyloCLI);
								cell_rcliente.setCellValue(res.getString("CD_CCODCLI").replaceAll(" ", ""));

								CellStyle rowStyloNOM = book.createCellStyle();
								rowStyloNOM.setAlignment(HorizontalAlignment.LEFT);
								rowStyloNOM.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteNOM = book.createFont();
								fuenteNOM.setFontName("Arial");
								fuenteNOM.setBold(false);
								fuenteNOM.setFontHeightInPoints((short) 8);
								rowStyloNOM.setFont(fuenteNOM);

								Cell cell_rnombre = row2.createCell((short) 2);
								cell_rnombre.setCellStyle(rowStyloNOM);
								cell_rnombre.setCellValue(res.getString("CL_CNOMCLI"));

								row2.createCell((short) 3).setCellValue(" ");

								numFilaDatos = numFilaDatos + 1;

							}
						}

						String EmiTime = res.getString("CD_DFECDOC");
						LocalDateTime datetimeEmi = LocalDateTime.parse(EmiTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
						String StrEmi = datetimeEmi.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

						String VctoTime = res.getString("CD_DFECVEN");
						LocalDateTime datetimeVcto = LocalDateTime.parse(VctoTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
						String StrVcto = datetimeVcto.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

						CODEMPRESA = res.getString("COD");
//						COD = res.getString("COD");
						
						CLIENTE = res.getString("CD_CCODCLI").replaceAll(" ", "");						
						NOMBRE = res.getString("CL_CNOMCLI");
						TD = res.getString("CD_CTIPDOC");
						NUMERO = res.getString("CD_CNRODOC");
						EMISION = StrEmi;
						VENCIMIENTO = StrVcto;
						MONEDA = res.getString("CD_CTIPMON");

						if (MONEDA.equals("US")) {
							IMPORTE = res.getDouble("CD_NIMPTUS");
							SALDO = res.getDouble("SALDOUS");
						}

						if (MONEDA.equals("MN")) {
							IMPORTE = res.getDouble("CD_NIMPTMN");
							SALDO = res.getDouble("SALDOMN");
						}

						SXSSFRow row3 = (SXSSFRow) reporte.createRow((short) numFilaDatos);

						int signoA = 0;
						int signoB = 0;
						if (TD.equals("NC") || TD.equals("PA")) {
							signoA = -1;
							signoB = 1;
						} else {
							signoA = 1;
							signoB = -1;
						}

						CellStyle rowStyloXTD = book.createCellStyle();
						rowStyloXTD.setAlignment(HorizontalAlignment.CENTER);
						rowStyloXTD.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXTD = book.createFont();
						fuenteRowXTD.setFontName("Arial");
						fuenteRowXTD.setBold(false);
						fuenteRowXTD.setFontHeightInPoints((short) 8);
						rowStyloXTD.setFont(fuenteRowXTD);

						Cell cell_XTD = row3.createCell((short) 3);
						cell_XTD.setCellStyle(rowStyloXTD);
						cell_XTD.setCellValue(TD);

						CellStyle rowStyloXNRO = book.createCellStyle();
						rowStyloXNRO.setAlignment(HorizontalAlignment.LEFT);
						rowStyloXNRO.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXNRO = book.createFont();
						fuenteRowXNRO.setFontName("Arial");
						fuenteRowXNRO.setBold(false);
						fuenteRowXNRO.setFontHeightInPoints((short) 8);
						rowStyloXNRO.setFont(fuenteRowXNRO);

						Cell cell_XNRO = row3.createCell((short) 4);
						cell_XNRO.setCellStyle(rowStyloXNRO);
						cell_XNRO.setCellValue(NUMERO);

						CellStyle rowStyloXEMI = book.createCellStyle();
						rowStyloXEMI.setAlignment(HorizontalAlignment.CENTER);
						rowStyloXEMI.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXEMI = book.createFont();
						fuenteRowXEMI.setFontName("Arial");
						fuenteRowXEMI.setBold(false);
						fuenteRowXEMI.setFontHeightInPoints((short) 8);
						rowStyloXEMI.setFont(fuenteRowXEMI);

						Cell cell_XEMI = row3.createCell((short) 5);
						cell_XEMI.setCellStyle(rowStyloXEMI);
						cell_XEMI.setCellValue(EMISION);

						CellStyle rowStyloXVCTO = book.createCellStyle();
						rowStyloXVCTO.setAlignment(HorizontalAlignment.CENTER);
						rowStyloXVCTO.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXVCTO = book.createFont();
						fuenteRowXVCTO.setFontName("Arial");
						fuenteRowXVCTO.setBold(false);
						fuenteRowXVCTO.setFontHeightInPoints((short) 8);
						rowStyloXVCTO.setFont(fuenteRowXVCTO);

						Cell cell_XVCTO = row3.createCell((short) 6);
						cell_XVCTO.setCellStyle(rowStyloXVCTO);
						cell_XVCTO.setCellValue(VENCIMIENTO);

						CellStyle rowStyloXMON = book.createCellStyle();
						rowStyloXMON.setAlignment(HorizontalAlignment.CENTER);
						rowStyloXMON.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXMON = book.createFont();
						fuenteRowXMON.setFontName("Arial");
						fuenteRowXMON.setBold(false);
						fuenteRowXMON.setFontHeightInPoints((short) 8);
						rowStyloXMON.setFont(fuenteRowXMON);

						Cell cell_XMON = row3.createCell((short) 7);
						cell_XMON.setCellStyle(rowStyloXMON);
						cell_XMON.setCellValue(MONEDA);

						CellStyle rowStyloXIMP = book.createCellStyle();
						rowStyloXIMP.setAlignment(HorizontalAlignment.RIGHT);
						rowStyloXIMP.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXIMP = book.createFont();
						fuenteRowXIMP.setFontName("Arial");
						fuenteRowXIMP.setBold(false);
						fuenteRowXIMP.setFontHeightInPoints((short) 8);
						rowStyloXIMP.setFont(fuenteRowXIMP);

						Cell cell_XIMP = row3.createCell((short) 8);
						cell_XIMP.setCellStyle(rowStyloXIMP);
						cell_XIMP.setCellValue(signoA * IMPORTE);

						CellStyle rowStyloXSALD = book.createCellStyle();
						rowStyloXSALD.setAlignment(HorizontalAlignment.RIGHT);
						rowStyloXSALD.setVerticalAlignment(VerticalAlignment.CENTER);
						org.apache.poi.ss.usermodel.Font fuenteRowXSALD = book.createFont();
						fuenteRowXSALD.setFontName("Arial");
						fuenteRowXSALD.setBold(false);
						fuenteRowXSALD.setFontHeightInPoints((short) 8);
						rowStyloXSALD.setFont(fuenteRowXSALD);

						Cell cell_XSALD = row3.createCell((short) 9);
						cell_XSALD.setCellStyle(rowStyloXSALD);
						cell_XSALD.setCellValue(signoA * SALDO);

						if (strAbo.equals("ABO")) {

							PreparedStatement psLiq;
							ResultSet resLiq;
							StringBuilder sqlliquiq = new StringBuilder();

							sqlliquiq.append("	 SELECT\n"
									+ "TIPO,\n"
									+ "NUMLIQ,\n"
									+ "CONVERT(NVARCHAR(MAX), FECHA, 103) AS FECHA,\n"
									+ "MONTOMN,\n"
									+ "MONTOUS\n"
									+ "FROM \n"
									+ tabla
									+ " WHERE \n"
									+ " COD = '" + COD + "' AND\n"
									+ " CODCLIE = '" + CLIENTE + "' AND\n"
									+ " TIPDOC = '" + TD + "' AND\n"
									+ " NRODOC = '" + NUMERO + "'");

							System.out.println(sqlliquiq);

							psLiq = cnn.getConnection().prepareStatement(sqlliquiq.toString());
							resLiq = psLiq.executeQuery();

							while (resLiq.next()) {
								numFilaDatos = numFilaDatos + 1;

								SXSSFRow row4 = (SXSSFRow) reporte.createRow((short) numFilaDatos);

								CellStyle rowStyloATIP = book.createCellStyle();
								rowStyloATIP.setAlignment(HorizontalAlignment.CENTER);
								rowStyloATIP.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteRowATIP = book.createFont();
								fuenteRowATIP.setFontName("Arial");
								fuenteRowATIP.setBold(false);
								fuenteRowATIP.setFontHeightInPoints((short) 8);
								rowStyloATIP.setFont(fuenteRowATIP);

								Cell cell_ATIP = row4.createCell((short) 3);
								cell_ATIP.setCellStyle(rowStyloATIP);
								cell_ATIP.setCellValue(resLiq.getString("TIPO"));

								CellStyle rowStyloALIQ = book.createCellStyle();
								rowStyloALIQ.setAlignment(HorizontalAlignment.LEFT);
								rowStyloALIQ.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteRowALIQ = book.createFont();
								fuenteRowALIQ.setFontName("Arial");
								fuenteRowALIQ.setBold(false);
								fuenteRowALIQ.setFontHeightInPoints((short) 8);
								rowStyloALIQ.setFont(fuenteRowALIQ);

								Cell cell_ALIQ = row4.createCell((short) 4);
								cell_ALIQ.setCellStyle(rowStyloALIQ);
								cell_ALIQ.setCellValue(resLiq.getString("NUMLIQ"));

								CellStyle rowStyloAFECH = book.createCellStyle();
								rowStyloAFECH.setAlignment(HorizontalAlignment.CENTER);
								rowStyloAFECH.setVerticalAlignment(VerticalAlignment.CENTER);
								org.apache.poi.ss.usermodel.Font fuenteRowAFECH = book.createFont();
								fuenteRowAFECH.setFontName("Arial");
								fuenteRowAFECH.setBold(false);
								fuenteRowAFECH.setFontHeightInPoints((short) 8);
								rowStyloAFECH.setFont(fuenteRowAFECH);

								Cell cell_AFECH = row4.createCell((short) 5);
								cell_AFECH.setCellStyle(rowStyloAFECH);
								cell_AFECH.setCellValue(resLiq.getString("FECHA"));

								if (MONEDA.equals("US")) {

									CellStyle rowStyloAMUS = book.createCellStyle();
									rowStyloAMUS.setAlignment(HorizontalAlignment.RIGHT);
									rowStyloAMUS.setVerticalAlignment(VerticalAlignment.CENTER);
									org.apache.poi.ss.usermodel.Font fuenteRowAMUS = book.createFont();
									fuenteRowAMUS.setFontName("Arial");
									fuenteRowAMUS.setBold(false);
									fuenteRowAMUS.setFontHeightInPoints((short) 8);
									rowStyloAMUS.setFont(fuenteRowAMUS);

									Cell cell_AMUS = row4.createCell((short) 8);
									cell_AMUS.setCellStyle(rowStyloAMUS);
									cell_AMUS.setCellValue(resLiq.getDouble("MONTOUS") * signoB);

								}

								if (MONEDA.equals("MN")) {

									CellStyle rowStyloAMMN = book.createCellStyle();
									rowStyloAMMN.setAlignment(HorizontalAlignment.RIGHT);
									rowStyloAMMN.setVerticalAlignment(VerticalAlignment.CENTER);
									org.apache.poi.ss.usermodel.Font fuenteRowAMMN = book.createFont();
									fuenteRowAMMN.setFontName("Arial");
									fuenteRowAMMN.setBold(false);
									fuenteRowAMMN.setFontHeightInPoints((short) 8);
									rowStyloAMMN.setFont(fuenteRowAMMN);

									Cell cell_AMMN = row4.createCell((short) 8);
									cell_AMMN.setCellStyle(rowStyloAMMN);
									cell_AMMN.setCellValue(resLiq.getDouble("MONTOMN") * signoB);

								}
							}
							resLiq.close();
						}
						nrow++;
					}
				}
				
				
			}
	
			res.close();
			cnn.desconectar();
			
//			PreparedStatement psdr;
//			String droptable = " DROP TABLE "+tabla;
//			psdr = cnn.getConnection().prepareStatement(droptable);
//			psdr.executeUpdate();
//			cnn.desconectar();
//			psdr.close();
			
			File PathC = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();
			System.out.println(PathC);
			
			String Documents = PathC.toString()+"Users"+"\\"+System.getProperty("user.name")+"\\"+"Documents"+"\\"+"Andina";			
			
			System.out.println(Documents);
			
			File FolderDown = new File(Documents);
			FolderDown.mkdirs();
			
			FileOutputStream fileOut = new FileOutputStream( FolderDown + "\\" + NombreArchivo + ".xlsx");
			book.write(fileOut);
			fileOut.close();

			Desktop.getDesktop().open(new File( FolderDown + "\\" + NombreArchivo + ".xlsx"));
			
			
			
			
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Excel_EstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Excel_EstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (SQLException ex) {
			Logger.getLogger(Excel_EstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}	
	
}
