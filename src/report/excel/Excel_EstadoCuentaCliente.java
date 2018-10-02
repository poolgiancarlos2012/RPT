package report.excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.cnx.Cnx;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.IOUtil;

public class Excel_EstadoCuentaCliente {

	private String where;
	private String wcod_cliente;

	public Excel_EstadoCuentaCliente(String xcod_cliente) {
		reporte(xcod_cliente);
	}

	public static void reporte(String xcod_cliente) {	
		try {
		
//			HSSFWorkbook book = new HSSFWorkbook(); 
//			XSSFWorkbook book = new XSSFWorkbook();
			SXSSFWorkbook book = new SXSSFWorkbook();
			Sheet xlsBCli = book.createSheet("Base Clientes");
			String NombreArchivo = "ReporteProductos " + new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());
			
			String[] cabecera = new String[]{"COD", "EMPRESA", "ZONA", "COD_VEN", "VENDEDOR", "COD_CLIENTE", "CLIENTE", "TD", "DOCUMENTO", "FECHA_EMISION", "FECHA_VENCIMIENTO", "DIAS_PLAZO", "MON", "TIPCAMV", "TIPCAMC", "IMPORTEDOC", "CODAGE", "ITEM", "PRODUCTO", "PRESENTACION", "UNID_KG_LT", "CODIGO", "DESCRI", "CANTI", "PRECIO", "UNID", "IGV", "VALORVTA", "PRECIOVTA"};
			Row filaEncabezados = xlsBCli.createRow(4);

			for (int i = 0; i < cabecera.length; i++) {
				Cell celdaEnzabezado = filaEncabezados.createCell(i);
				celdaEnzabezado.setCellValue(cabecera[i]);
			}

			Cnx cnn = new Cnx();
			PreparedStatement ps;
			ResultSet res;
			StringBuilder sql = new StringBuilder();
			sql.append("EXECUTE SP_HISTORICO_DEUDA_DETALLE \n"
					+ "'02/07/2018','03/07/2018','','',\n"
					+ "'0002',\n"
					+ "'0003',\n"
					+ "'0004',\n"
					+ "'0016'  ");
			System.out.println(sql.toString());
			ps = cnn.getConnection().prepareStatement(sql.toString());
			res = ps.executeQuery();
			int numFilaDatos = 5; // desde que fila se mostraran los datos
			int numCol = res.getMetaData().getColumnCount(); // numero de columnas            
			while (res.next()) {
				Row filaDatos = xlsBCli.createRow(numFilaDatos);
				for (int a = 0; a < numCol; a++) {
					Cell CeldaDatos = filaDatos.createCell(a);
					if (a == 0 || a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6 || a == 7 || a == 8 || a == 9 || a == 10 || a == 11 || a == 12 || a == 13 || a == 14 || a == 15 || a == 16 || a == 17 || a == 18 || a == 19 || a == 20 || a == 21 || a == 22 || a == 23 || a == 24 || a == 25 || a == 26 || a == 27 || a == 28) {
						CeldaDatos.setCellValue(res.getString(a + 1));
					} else {
					}
				}
				numFilaDatos++;
			}
			res.close();
			cnn.desconectar();

			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\docs\\excel\\" + NombreArchivo + ".xlsx");
			book.write(fileOut);
			fileOut.close();

			Desktop.getDesktop().open(new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\docs\\excel\\" + NombreArchivo + ".xlsx"));
			
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Excel_EstadoCuentaCliente.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Excel_EstadoCuentaCliente.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Excel_EstadoCuentaCliente.class.getName()).log(Level.SEVERE, null, ex);
		}			
	}
}
