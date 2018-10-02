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

public class Excel_EstadoCuentaSunny{
	
	
	public Excel_EstadoCuentaSunny() throws Exception{
		
		
		try {	
			
			SXSSFWorkbook book = new SXSSFWorkbook();
			Sheet reporte = book.createSheet("Est.Cu. Sunny");
			
			CellStyle headerStylo = book.createCellStyle();
			headerStylo.setAlignment(HorizontalAlignment.CENTER);
			headerStylo.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font fuenteHeader = book.createFont();
			fuenteHeader.setFontName("Arial");
			fuenteHeader.setBold(true);
			fuenteHeader.setFontHeightInPoints((short) 9);			
			headerStylo.setFont(fuenteHeader);
			
			CellStyle RwStyLetraIzq = book.createCellStyle();
			RwStyLetraIzq.setAlignment(HorizontalAlignment.LEFT);
			RwStyLetraIzq.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font FontLetraIzq = book.createFont();
			FontLetraIzq.setFontName("Arial");
			FontLetraIzq.setBold(false);
			FontLetraIzq.setFontHeightInPoints((short) 8);
			RwStyLetraIzq.setFont(FontLetraIzq);
			
			CellStyle RwStyLetraDerecha = book.createCellStyle();
			RwStyLetraDerecha.setAlignment(HorizontalAlignment.RIGHT);
			RwStyLetraDerecha.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font FontLetraDerecha = book.createFont();
			FontLetraDerecha.setFontName("Arial");
			FontLetraDerecha.setBold(false);
			FontLetraDerecha.setFontHeightInPoints((short) 8);
			RwStyLetraDerecha.setFont(FontLetraDerecha);
			
			CellStyle RwStyLetraCenter = book.createCellStyle();
			RwStyLetraCenter.setAlignment(HorizontalAlignment.CENTER);
			RwStyLetraCenter.setVerticalAlignment(VerticalAlignment.CENTER);
			org.apache.poi.ss.usermodel.Font FontLetraCenter = book.createFont();
			FontLetraCenter.setFontName("Arial");
			FontLetraCenter.setBold(false);
			FontLetraCenter.setFontHeightInPoints((short) 8);
			RwStyLetraCenter.setFont(FontLetraCenter);
			

			SXSSFRow  rowhead  = (SXSSFRow) reporte.createRow((short) 0);
			String NombreArchivo = "EstadoCuentaSunny" + new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());

			Cell cell_empresa = rowhead.createCell((short) 0);
			cell_empresa.setCellStyle(headerStylo);
			cell_empresa.setCellValue("EMPRESA");
			
			Cell cell_codvend = rowhead.createCell((short) 1);
			cell_codvend.setCellStyle(headerStylo);
			cell_codvend.setCellValue("COD_VEN");
			
			Cell cell_vend = rowhead.createCell((short) 2);
			cell_vend.setCellStyle(headerStylo);
			cell_vend.setCellValue("VENDE");
			
			Cell cell_tienda = rowhead.createCell((short) 3);
			cell_tienda.setCellStyle(headerStylo);
			cell_tienda.setCellValue("TIENDA");
			
			Cell cell_td = rowhead.createCell((short) 4);
			cell_td.setCellStyle(headerStylo);
			cell_td.setCellValue("TD");
						
			Cell cell_doc = rowhead.createCell((short) 5);
			cell_doc.setCellStyle(headerStylo);
			cell_doc.setCellValue("DOCUMENTO");
			
			Cell cell_emision = rowhead.createCell((short) 6);
			cell_emision.setCellStyle(headerStylo);
			cell_emision.setCellValue("FEC_EMISION");
			
			Cell cell_vcto = rowhead.createCell((short) 7);
			cell_vcto.setCellStyle(headerStylo);
			cell_vcto.setCellValue("FEC_VENCI");
			
			Cell cell_mon = rowhead.createCell((short) 8);
			cell_mon.setCellStyle(headerStylo);
			cell_mon.setCellValue("MONEDA");
			
			Cell cell_import = rowhead.createCell((short) 9);
			cell_import.setCellStyle(headerStylo);
			cell_import.setCellValue("IMPORTE");
			
			Cell cell_saldo = rowhead.createCell((short) 10);
			cell_saldo.setCellStyle(headerStylo);
			cell_saldo.setCellValue("SALDO");
			
			Cell cell_estado = rowhead.createCell((short) 11);
			cell_estado.setCellStyle(headerStylo);
			cell_estado.setCellValue("ESTADO");
						
			Cell cell_detestado = rowhead.createCell((short) 12);
			cell_detestado.setCellStyle(headerStylo);
			cell_detestado.setCellValue("DET_ESTADO");
						
			Cell cell_banco = rowhead.createCell((short) 13);
			cell_banco.setCellStyle(headerStylo);
			cell_banco.setCellValue("BANCO");
						
			Cell cell_numcobra = rowhead.createCell((short) 14);
			cell_numcobra.setCellStyle(headerStylo);
			cell_numcobra.setCellValue("NUM_COBRA");
			
			int colvar = 256; // Valor por defecto para poder setear el ancho de las columnas
//			
			reporte.setColumnWidth(0, 10 * colvar);
			reporte.setColumnWidth(1, 13 * colvar);
			reporte.setColumnWidth(2, 25 * colvar);
			reporte.setColumnWidth(3, 13 * colvar);
			reporte.setColumnWidth(4, 5 * colvar);
			reporte.setColumnWidth(5, 18 * colvar);
			reporte.setColumnWidth(6, 18 * colvar);
			reporte.setColumnWidth(7, 18 * colvar);
			
			Cnx cnn = new Cnx();
			PreparedStatement ps;
			ResultSet res;
			StringBuilder sql = new StringBuilder();
			
			sql.append("		SELECT\n" +
"							LTRIM(RTRIM(TM_CCIA)) AS 'CODIGO_EMPRESA',\n" +
"							LTRIM(RTRIM(COD_VEN)) AS 'COD_VEN',\n" +
"							LTRIM(RTRIM(VENDE)) AS 'VENDE',\n" +
"							LTRIM(RTRIM(TM_TIPDOC)) AS 'TD',\n" +
"							LTRIM(RTRIM(TM_NRODOC)) AS 'DOCUMENTO',\n" +
"							CONVERT(VARCHAR(10), TM_FECDOC, 103) AS 'FEC_EMISION',\n" +
"							CONVERT(VARCHAR(10), TM_FECVEN, 103) AS 'FEC_VENCI',\n" +
"							LTRIM(RTRIM(TM_TIPMON)) AS 'MONEDA',\n" +
"							CASE LTRIM(RTRIM(TM_TIPMON)) \n" +
"							WHEN 'US' THEN TM_IMPTUS\n" +
"							WHEN 'MN' THEN TM_IMPTMN\n" +
"							ELSE 0 END AS 'IMPORTE',\n" +
"							CASE LTRIM(RTRIM(TM_TIPMON)) \n" +
"							WHEN 'US' THEN TM_SALDUS\n" +
"							WHEN 'MN' THEN TM_SALDMN\n" +
"							ELSE 0 END AS 'SALDO',\n" +
"							LTRIM(RTRIM(TM_LETSIT)) AS 'ESTADO',\n" +
"							CASE LTRIM(RTRIM(TM_TIPDOC))\n" +
"							WHEN 'LT' THEN \n" +
"												CASE LTRIM(RTRIM(TM_LETSIT))\n" +
"												WHEN 'CO' THEN 'COBRANZA EN BANCO'\n" +
"												WHEN 'CT' THEN ''\n" +
"												WHEN 'COPR' THEN 'COBRANZA PROTESTADO'\n" +
"												WHEN 'CTRE' THEN 'REBAJADO EN CARTERA'\n" +
"												WHEN 'BC' THEN 'ENVIADO AL BANCO'\n" +
"												WHEN 'CORE' THEN 'COBRANZA REBAJADA'\n" +
"												WHEN 'COCA' THEN 'CANCELADO'\n" +
"												ELSE '' END\n" +
"							WHEN 'NC' THEN 'SALDO A FAVOR'\n" +
"							WHEN 'PA' THEN\n" +
"												CASE SUBSTRING(REPLACE(LTRIM(RTRIM(TM_NRODOC)), ' ', ''), 1, 2)\n" +
"													WHEN 'OP' THEN 'SALDO A FAVOR'\n" +
"													WHEN 'DR' THEN 'AUTODETRACCIÓN'\n" +
"													ELSE ''\n" +
"												END\n" +
"							ELSE '' END AS 'DET_ESTADO',\n" +
"							LTRIM(RTRIM(TM_DESBAN)) AS 'BANCO',\n" +
"							CASE LTRIM(RTRIM(TM_LETSIT)) WHEN 'COPR' THEN ''\n" +
"							ELSE TM_NUMCOB\n" +
"							END AS 'NUM_COBRA'\n" +
"\n" +
"							FROM\n" +
"(\n" +
"\n" +
"							SELECT \n" +
"							'COMERCIAL ANDINA INDUSTRIAL SAC' AS TM_CCIADES,\n" +
"							'0002' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"											WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"											ELSE CD_CCODVEN \n" +
"										END, \n" +
"							VENDE = 		CASE \n" +
"											WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"											ELSE	(\n" +
"													SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0002VEND A LEFT OUTER JOIN RSFACCAR..FT0002LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"													LEFT OUTER JOIN RSFACCAR..FT0002SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"													LEFT OUTER JOIN RSFACCAR..FT0002ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0002TABL C  \n" +
"													WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"												)\n" +
"										END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASe When Datediff(DAY,CD_DFECDOC,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECDOC,Getdate())<0 then 0 When Datediff(DAY,CD_DFECDOC,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECDOC,Getdate()) End,\n" +
"							CD_DFECVEN AS TM_FECVEN,CD_DFECCAN AS TM_FECCAN, TM_DIAPLZ=CASe When Datediff(DAY,CD_DFECVEN,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECVEN,Getdate())<0 then 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT, \n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN,\n" +
"							CD_CTDOREF AS TM_TIPDOCREF,\n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO,\n" +
"							1 AS 'NUM'\n" +
"							From RSFACCAR..CC0002CART\n" +
"							Left Join RSFACCAR..CC0002LETR On CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							Left Join RSFACCAR..CC0002BANC On LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT Join RSFACCAR..FT0002VEND On CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT Join RSFACCAR..FT0002FACC AS B On CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT Join RSFACCAR..FT0002ACUC AS C On CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE\n" +
"							CD_CCODCLI='20524869714' AND \n" +
"							(CD_CESTADO<>'A')   AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X') \n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT\n" +
"							'GRUPO ANDEX' AS TM_CCIADES,\n" +
"							'0003' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE CD_CCODVEN \n" +
"																				END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0003VEND A LEFT OUTER JOIN RSFACCAR..FT0003LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0003SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0003ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0003TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASE WHEN Datediff(DAY,CD_DFECDOC,Getdate()) IS NULL THEN 0 WHEN Datediff(DAY,CD_DFECDOC,Getdate())<0 THEN 0 WHEN Datediff(DAY,CD_DFECDOC,Getdate())>999 THEN 999 ELSE Datediff(DAY,CD_DFECDOC,Getdate()) END,\n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN,\n" +
"							TM_DIAPLZ=CASE WHEN Datediff(DAY,CD_DFECVEN,Getdate()) IS NULL THEN 0 WHEN Datediff(DAY,CD_DFECVEN,Getdate())<0 THEN 0 WHEN Datediff(DAY,CD_DFECVEN,Getdate())>999 THEN 999 ELSE Datediff(DAY,CD_DFECVEN,Getdate()) END,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT, \n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN,\n" +
"							CD_CTDOREF AS TM_TIPDOCREF,\n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO,\n" +
"							2 AS 'NUM'\n" +
"							FROM RSFACCAR..CC0003CART\n" +
"							LEFT JOIN RSFACCAR..CC0003LETR ON CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							LEFT JOIN RSFACCAR..CC0003BANC ON LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT JOIN RSFACCAR..FT0003VEND ON CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT JOIN RSFACCAR..FT0003FACC AS B ON CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT JOIN RSFACCAR..FT0003ACUC AS C ON CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE\n" +
"							CD_CCODCLI='20524869714' AND\n" +
"							(CD_CESTADO<>'A') AND\n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X')\n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT\n" +
"							'FERTILIZANTES Y SEMILLAS ANDINA' AS TM_CCIADES,\n" +
"							'0004' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"															WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"															ELSE CD_CCODVEN \n" +
"													END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0004VEND A LEFT OUTER JOIN RSFACCAR..FT0004LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0004SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0004ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0004TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END, TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASE WHEN Datediff(DAY,CD_DFECDOC,Getdate()) IS NULL THEN 0 WHEN Datediff(DAY,CD_DFECDOC,Getdate())<0 THEN 0 WHEN Datediff(DAY,CD_DFECDOC,Getdate())>999 THEN 999 ELSE Datediff(DAY,CD_DFECDOC,Getdate()) END, \n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN,\n" +
"							TM_DIAPLZ=CASE WHEN Datediff(DAY,CD_DFECVEN,Getdate()) IS NULL THEN 0 WHEN Datediff(DAY,CD_DFECVEN,Getdate())<0 THEN 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT,\n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN,\n" +
"							CD_CTDOREF AS TM_TIPDOCREF,\n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO ,\n" +
"							3 AS 'NUM'\n" +
"							FROM \n" +
"							RSFACCAR..CC0004CART\n" +
"							LEFT JOIN RSFACCAR..CC0004LETR ON CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							LEFT JOIN RSFACCAR..CC0004BANC ON LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT JOIN RSFACCAR..FT0004VEND ON CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT JOIN RSFACCAR..FT0004FACC AS B ON CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT JOIN RSFACCAR..FT0004ACUC AS C ON CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE \n" +
"							CD_CCODCLI='20524869714' AND \n" +
"							(CD_CESTADO<>'A') AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X')\n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT \n" +
"							'CAISAC II' AS TM_CCIADES, \n" +
"							'0005' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"																	WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																	ELSE CD_CCODVEN \n" +
"															END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0005VEND A LEFT OUTER JOIN RSFACCAR..FT0005LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0005SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0005ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0005TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASe When Datediff(DAY,CD_DFECDOC,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECDOC,Getdate())<0 then 0 When Datediff(DAY,CD_DFECDOC,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECDOC,Getdate()) End,\n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN,\n" +
"							TM_DIAPLZ=CASe When Datediff(DAY,CD_DFECVEN,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECVEN,Getdate())<0 then 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT,\n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN,\n" +
"							CD_CTDOREF AS TM_TIPDOCREF,\n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO,\n" +
"							5 AS 'NUM'\n" +
"							FROM\n" +
"							RSFACCAR..CC0005CART\n" +
"							LEFT JOIN RSFACCAR..CC0005LETR On CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							LEFT JOIN RSFACCAR..CC0005BANC On LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT JOIN RSFACCAR..FT0005VEND On CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT JOIN RSFACCAR..FT0005FACC AS B On CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT JOIN RSFACCAR..FT0005ACUC AS C On CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE\n" +
"							CD_CCODCLI='20524869714' AND \n" +
"							(CD_CESTADO<>'A')   AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X')\n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT \n" +
"							'ANDEX II' AS TM_CCIADES,\n" +
"							'0006' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE CD_CCODVEN \n" +
"																				END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0006VEND A LEFT OUTER JOIN RSFACCAR..FT0006LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0006SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0006ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0006TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASe When Datediff(DAY,CD_DFECDOC,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECDOC,Getdate())<0 then 0 When Datediff(DAY,CD_DFECDOC,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECDOC,Getdate()) End, \n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN,\n" +
"							TM_DIAPLZ=CASe When Datediff(DAY,CD_DFECVEN,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECVEN,Getdate())<0 then 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT, \n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN,\n" +
"							CD_CTDOREF AS TM_TIPDOCREF,\n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO ,\n" +
"							6 AS 'NUM'\n" +
"							From RSFACCAR..CC0006CART\n" +
"							LEFT JOIN RSFACCAR..CC0006LETR On CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							LEFT JOIN RSFACCAR..CC0006BANC On LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT JOIN RSFACCAR..FT0006VEND On CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT JOIN RSFACCAR..FT0006FACC AS B On CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT JOIN RSFACCAR..FT0006ACUC AS C On CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE \n" +
"							CD_CCODCLI='20524869714' AND \n" +
"							(CD_CESTADO<>'A') AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X')\n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT \n" +
"							'SEMILLAS II' AS TM_CCIADES, \n" +
"							'0007' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE CD_CCODVEN \n" +
"																				END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0007VEND A LEFT OUTER JOIN RSFACCAR..FT0007LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0007SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0007ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0007TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASe When Datediff(DAY,CD_DFECDOC,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECDOC,Getdate())<0 then 0 When Datediff(DAY,CD_DFECDOC,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECDOC,Getdate()) End,\n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN,\n" +
"							TM_DIAPLZ=CASe When Datediff(DAY,CD_DFECVEN,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECVEN,Getdate())<0 then 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT, \n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN, \n" +
"							CD_CTDOREF AS TM_TIPDOCREF, \n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO ,\n" +
"							7 AS 'NUM'\n" +
"							From RSFACCAR..CC0007CART\n" +
"							LEFT JOIN RSFACCAR..CC0007LETR On CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET    \n" +
"							LEFT JOIN RSFACCAR..CC0007BANC On LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT JOIN RSFACCAR..FT0007VEND On CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT JOIN RSFACCAR..FT0007FACC AS B On CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT JOIN RSFACCAR..FT0007ACUC AS C On CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE \n" +
"							CD_CCODCLI='20524869714' AND \n" +
"							(CD_CESTADO<>'A') AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X') \n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT \n" +
"							'GRUPO ANDINA SAC' AS TM_CCIADES, \n" +
"							'0009' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE CD_CCODVEN \n" +
"																				END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0009VEND A LEFT OUTER JOIN RSFACCAR..FT0009LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0009SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0009ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0009TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASe When Datediff(DAY,CD_DFECDOC,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECDOC,Getdate())<0 then 0 When Datediff(DAY,CD_DFECDOC,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECDOC,Getdate()) End, \n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN, \n" +
"							TM_DIAPLZ=CASe When Datediff(DAY,CD_DFECVEN,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECVEN,Getdate())<0 then 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End,\n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT, \n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN, \n" +
"							CD_CTDOREF AS TM_TIPDOCREF, \n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO ,\n" +
"							8 AS 'NUM'\n" +
"							From RSFACCAR..CC0009CART\n" +
"							Left Join RSFACCAR..CC0009LETR On CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							Left Join RSFACCAR..CC0009BANC On LT_CCODBAN=BC_CCODBAN    LEFT Join RSFACCAR..FT0009VEND On CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT Join RSFACCAR..FT0009FACC AS B On CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT Join RSFACCAR..FT0009ACUC AS C On CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							Where CD_CCODCLI='20524869714' And \n" +
"							(CD_CESTADO<>'A')   AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X')\n" +
"\n" +
"\n" +
"							UNION \n" +
"\n" +
"							SELECT\n" +
"							'SUNNY VALLEY S.A.C.' AS TM_CCIADES,\n" +
"							'0016' AS TM_CCIA,\n" +
"							'01' AS TM_TIPO,\n" +
"							CD_CCODART AS TM_CODART,\n" +
"							CD_CPERIOD AS TM_CGLOSA,\n" +
"							CD_CTIPDOC+CD_CNRODOC AS TM_BUSCA,\n" +
"\n" +
"							COD_VEN = 	CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE CD_CCODVEN \n" +
"																				END, \n" +
"							VENDE = 				CASE \n" +
"																						WHEN VE_CTIPVEN='F' THEN CD_CCODVEN+'/'+VE_CCODLIN \n" +
"																						ELSE	(\n" +
"																														SELECT A.VE_CNOMBRE FROM (RSFACCAR..FT0016VEND A LEFT OUTER JOIN RSFACCAR..FT0016LINE B  ON A.VE_CCODLIN = B.LI_CCODLIN)\n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0016SUPV E ON A.VE_CCOSUPV = E.SU_CCODIGO  \n" +
"																														LEFT OUTER JOIN RSFACCAR..FT0016ZONV D ON A.VE_CZONVTA = D.ZV_CCODZON ,RSFACCAR..AL0016TABL C  \n" +
"																														WHERE A.VE_CTIPVEN = C.TG_CCLAVE  AND C.TG_CCOD='53' AND A.VE_CCODIGO=CD_CCODVEN\n" +
"																												)\n" +
"																					END,\n" +
"\n" +
"							CD_CTIPDOC AS TM_TIPDOC,\n" +
"							CD_CLOCEMI AS TM_LOCEMI,\n" +
"							CD_CNRODOC AS TM_NRODOC,\n" +
"							CD_CORIGEN AS TM_ORIGEN,\n" +
"							CD_CCODCLI AS TM_CODCLI,\n" +
"							CD_CDEBHAB AS TM_DEBHAB,\n" +
"							CD_NNROABO AS TM_NROABO,\n" +
"							CD_DFECDOC AS TM_FECDOC,\n" +
"							CD_CTDOREF AS TM_CTDREF,\n" +
"							CD_CNROREF AS TM_NRODOCR,\n" +
"							TM_CSERIE =CASE WHEN NOT B.F5_CNUMSER IS NULL THEN B.F5_CNUMSER WHEN NOT C.F5_CNUMSER IS NULL THEN C.F5_CNUMSER END,\n" +
"							TM_CDOCVT =CASE WHEN NOT B.F5_CNUMDOC IS NULL THEN B.F5_CNUMDOC WHEN NOT C.F5_CNUMDOC IS NULL THEN C.F5_CNUMDOC END,\n" +
"							TM_DIATRA=CASe When Datediff(DAY,CD_DFECDOC,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECDOC,Getdate())<0 then 0 When Datediff(DAY,CD_DFECDOC,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECDOC,Getdate()) End, \n" +
"							CD_DFECVEN AS TM_FECVEN,\n" +
"							CD_DFECCAN AS TM_FECCAN,\n" +
"							TM_DIAPLZ=CASe When Datediff(DAY,CD_DFECVEN,Getdate()) is null Then 0 When Datediff(DAY,CD_DFECVEN,Getdate())<0 then 0 When Datediff(DAY,CD_DFECVEN,Getdate())>999 then 999 Else Datediff(DAY,CD_DFECVEN,Getdate()) End, \n" +
"							CD_CTIPMON AS TM_TIPMON ,\n" +
"							CD_NIMPTMN AS TM_IMPTMN,\n" +
"							CD_NIMPTUS AS TM_IMPTUS,\n" +
"							CD_NSALDMN AS TM_SALDMN,\n" +
"							CD_NSALDUS AS TM_SALDUS,\n" +
"							CD_NTIPCAM AS TM_TIPCAM,\n" +
"							CD_CTIPPRO AS TM_TIPPRO,\n" +
"							TM_IMPORTE=CASE CD_CTIPMON When 'MN' Then CD_NIMPTMN Else CD_NIMPTUS End,\n" +
"							TM_SALDO=CASE CD_CTIPMON When 'MN' Then CD_NSALDMN Else CD_NSALDUS End,\n" +
"							TM_CCODVEN=CASe When VE_CTIPVEN='F' Then CD_CCODVEN+'/'+VE_CCODLIN Else CD_CCODVEN End,\n" +
"							ISNULL(LT_CSITUAC,'') AS TM_LETSIT, \n" +
"							ISNULL(LT_CNUMCOB,'') AS TM_NUMCOB,\n" +
"							ISNULL(BC_CDESBAN,'') AS TM_DESBAN, \n" +
"							CD_CTDOREF AS TM_TIPDOCREF, \n" +
"							CD_CNROREF AS TM_NUMDOCREF,\n" +
"							CD_CESTADO AS TM_ESTADO ,\n" +
"							4 AS 'NUM'\n" +
"							From RSFACCAR..CC0016CART\n" +
"							Left Join RSFACCAR..CC0016LETR On CD_CTIPDOC='LT' AND CD_CNRODOC=LT_CNROLET\n" +
"							Left Join RSFACCAR..CC0016BANC On LT_CCODBAN=BC_CCODBAN\n" +
"							LEFT Join RSFACCAR..FT0016VEND On CD_CCODVEN=VE_CCODIGO\n" +
"							LEFT Join RSFACCAR..FT0016FACC AS B On CD_CTIPDOC=B.F5_CTD AND CD_CNRODOC=RTRIM(B.F5_CNUMSER)+RTRIM(B.F5_CNUMDOC) AND CD_CCODCLI=B.F5_CCODCLI\n" +
"							LEFT Join RSFACCAR..FT0016ACUC AS C On CD_CTIPDOC=C.F5_CTD AND CD_CNRODOC=RTRIM(C.F5_CNUMSER)+RTRIM(C.F5_CNUMDOC) AND CD_CCODCLI=C.F5_CCODCLI\n" +
"							WHERE \n" +
"							CD_CCODCLI='20524869714' And \n" +
"							(CD_CESTADO<>'A')   AND \n" +
"							((LT_CSITUAC<>'GE' AND LT_CSITUAC<>'EM') OR ISNULL(LT_CSITUAC,'X')='X')\n" +
") AS T\n" +
"WHERE\n" +
"T.TM_ESTADO='V' AND\n" +
"0 <>		CASE LTRIM(RTRIM(TM_TIPMON)) \n" +
"								WHEN 'US' THEN TM_SALDUS\n" +
"								WHEN 'MN' THEN TM_SALDMN\n" +
"								ELSE 0 \n" +
"						END -- NO MUESTRA LOS SALDO 0\n" +
"ORDER BY T.NUM,T.TM_FECVEN ASC");

			System.out.println(sql.toString());
			
			ps = cnn.getConnection().prepareStatement(sql.toString());
			res = ps.executeQuery();
			
			int numFilaDatos = 0; 
			while ( res.next() ) {
				numFilaDatos = numFilaDatos + 1;
				
				SXSSFRow row = (SXSSFRow) reporte.createRow((short) numFilaDatos);
				
				String EMPRESA ="";
				
				if("0002".equals(res.getString("CODIGO_EMPRESA"))){
					EMPRESA = "CAISAC";
				}
				
				if("0003".equals(res.getString("CODIGO_EMPRESA"))){
					EMPRESA = "ANDEX";
				}
				
				if("0004".equals(res.getString("CODIGO_EMPRESA"))){
					EMPRESA = "SEMILLAS";
				}
				
				if("0016".equals(res.getString("CODIGO_EMPRESA"))){
					EMPRESA = "SUNNY";
				}
				
				Cell cell_EMPRE = row.createCell((short) 0);
				cell_EMPRE.setCellStyle(RwStyLetraCenter);
				cell_EMPRE.setCellValue(EMPRESA);
				
				Cell cell_CODVEN = row.createCell((short) 1);
				cell_CODVEN.setCellStyle(RwStyLetraCenter);
				cell_CODVEN.setCellValue(res.getString("COD_VEN"));
				
				Cell cell_VEN = row.createCell((short) 2);
				cell_VEN.setCellStyle(RwStyLetraIzq);
				cell_VEN.setCellValue(res.getString("VENDE"));
				
				String xtienda ="";
				
				if("301".equals(res.getString("COD_VEN"))){
					xtienda = "OFICINA";
				}else{
					xtienda = "TIENDA";
				}
				
				
				Cell cell_TIENDA = row.createCell((short) 3);
				cell_TIENDA.setCellStyle(RwStyLetraIzq);
				cell_TIENDA.setCellValue(xtienda);
				
				Cell cell_TD = row.createCell((short) 4);
				cell_TD.setCellStyle(RwStyLetraCenter);
				cell_TD.setCellValue(res.getString("TD"));
				
				Cell cell_DOCUMENTO = row.createCell((short) 5);
				cell_DOCUMENTO.setCellStyle(RwStyLetraIzq);
				cell_DOCUMENTO.setCellValue(res.getString("DOCUMENTO"));
				
				Cell cell_FEC_EMISION = row.createCell((short) 6);
				cell_FEC_EMISION.setCellStyle(RwStyLetraCenter);
				cell_FEC_EMISION.setCellValue(res.getString("FEC_EMISION"));
				
				Cell cell_FEC_VENCI = row.createCell((short) 7);
				cell_FEC_VENCI.setCellStyle(RwStyLetraCenter);
				cell_FEC_VENCI.setCellValue(res.getString("FEC_VENCI"));
				
				Cell cell_MONEDA = row.createCell((short) 8);
				cell_MONEDA.setCellStyle(RwStyLetraCenter);
				cell_MONEDA.setCellValue(res.getString("MONEDA"));
				
				Cell cell_IMPORTE = row.createCell((short) 9);
				cell_IMPORTE.setCellStyle(RwStyLetraDerecha);
				cell_IMPORTE.setCellValue(res.getDouble("IMPORTE"));
				
				Cell cell_SALDO = row.createCell((short) 10);
				cell_SALDO.setCellStyle(RwStyLetraDerecha);
				cell_SALDO.setCellValue(res.getDouble("SALDO"));
				
				Cell cell_ESTADO = row.createCell((short) 11);
				cell_ESTADO.setCellStyle(RwStyLetraCenter);
				cell_ESTADO.setCellValue(res.getString("ESTADO"));
				
				Cell cell_DET_ESTADO = row.createCell((short) 12);
				cell_DET_ESTADO.setCellStyle(RwStyLetraIzq);
				cell_DET_ESTADO.setCellValue(res.getString("DET_ESTADO"));
				
				Cell cell_BANCO = row.createCell((short) 13);
				cell_BANCO.setCellStyle(RwStyLetraIzq);
				cell_BANCO.setCellValue(res.getString("BANCO"));
				
				Cell cell_NUM_COBRA = row.createCell((short) 14);
				cell_NUM_COBRA.setCellStyle(RwStyLetraCenter);
				cell_NUM_COBRA.setCellValue(res.getString("NUM_COBRA"));
				
			}
			
			res.close();
			cnn.desconectar();
			
			File PathC = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();

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
			Logger.getLogger(Excel_EstadoCuentaSunny.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Excel_EstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
		}
			

		
	}

	
	
}