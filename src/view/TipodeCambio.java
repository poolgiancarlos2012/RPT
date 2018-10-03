package View;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.Manager;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import report.excel.Excel_TipodeCambio;

public class TipodeCambio extends JInternalFrame implements InternalFrameListener{

	private Manager mgr = new Manager();
	
	private JPanel paneladm,panelop,panelDownload,panelProgressBar,panelRpta;
	private JLabel lblFechaIni,lblFechaFin;
	private JDateChooser JDFechaIni,JDFechaFin;
	private JButton btnDescargar;
	private JTextArea AreaResult;
	private JScrollPane scrPaneEstCuxFech;
	private JProgressBar barTipodeCambio;
		
	public TipodeCambio() throws Exception{
				
		mgr = new Manager();
		
		setClosable(true); // Boton Cerrar
		setIconifiable(true); // Boton Minimizar
		setMaximizable(false);
		setResizable(false);
		setTitle("Reporte Tipo de Cambio");
		
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		addInternalFrameListener(this);
		
		this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Srep16x16.png")));
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();		
		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
		ge.registerFont(StrExo2Medium);
		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
		
		String strexosemibold = new File("src\\font\\exo2\\Exo2-SemiBold.otf").getAbsolutePath();
		Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File(strexosemibold));
		ge.registerFont(StrExo2SemiBold);
		Font Exo2SemiBold = new Font("Exo 2 Semi Bold", Font.PLAIN, 13);
		
		paneladm = new JPanel();
		paneladm.setLayout(new BoxLayout(paneladm, BoxLayout.Y_AXIS));		
		
		panelop = new JPanel();

		panelop.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		
		lblFechaIni = new JLabel();
		lblFechaIni.setFont(Exo2Medium);
		lblFechaIni.setText("Fecha Inicio");
		panelop.add(lblFechaIni);
		
		JDFechaIni = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');		
		JTextFieldDateEditor TXTini = (JTextFieldDateEditor)this.JDFechaIni.getComponent(1);
		TXTini.setHorizontalAlignment(JTextField.CENTER);
		TXTini.setFont(Exo2Medium);
		JDFechaIni.setPreferredSize(new Dimension(114,19));
		panelop.add(JDFechaIni);
		
		lblFechaFin = new JLabel();
		lblFechaFin.setFont(Exo2Medium);
		lblFechaFin.setText("Fecha Fin");
		panelop.add(lblFechaFin);
		
		JDFechaFin = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');		
		JTextFieldDateEditor TXTFin = (JTextFieldDateEditor)this.JDFechaFin.getComponent(1);
		TXTFin.setHorizontalAlignment(JTextField.CENTER);
		TXTFin.setFont(Exo2Medium);
		JDFechaFin.setPreferredSize(new Dimension(114,19));
		panelop.add(JDFechaFin);
		
		paneladm.add(panelop);
		
		panelDownload = new JPanel();
		panelDownload.setLayout(new FlowLayout(FlowLayout.CENTER,12,0));
		panelDownload.setBorder(new EmptyBorder(8, 0, 8, 0));
		
		String key_go = new File("src\\img\\fatcow_icon\\16x16_0620\\page_excel.png").getAbsolutePath();		
		ImageIcon iconkey_go = new ImageIcon(key_go);
		
		btnDescargar = new JButton("DESCARGAR",iconkey_go);
		btnDescargar.setFont(Exo2Medium);
		btnDescargar.setPreferredSize(new Dimension(100,25));
		btnDescargar.setFocusPainted(false);
		
		btnDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
	
				Date xini = JDFechaIni.getDate();
				Date xfin = JDFechaFin.getDate();
				
				if( xini != null &&  xfin != null ){
					paneladm.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					btnDescargar.setEnabled(false);
					WorkerRptTipodeCambio worker = new WorkerRptTipodeCambio(AreaResult, btnDescargar, paneladm, JDFechaIni, JDFechaFin);
					worker.addPropertyChangeListener(new ProgressTipodeCambio(barTipodeCambio));
					worker.execute();
				}else{				
					try {
						String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
						Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
						ge.registerFont(StrExo2Light);
						Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

						String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

						ImageIcon IconSuccess = new ImageIcon(iconaccept);

						JLabel lblsuccess = new JLabel(".: Ingresar Fecha de Inicio y Fin :.");
						lblsuccess.setFont(Exo2Light);
						JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
					} catch (FontFormatException ex) {
						Logger.getLogger(TipodeCambio.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(TipodeCambio.class.getName()).log(Level.SEVERE, null, ex);
					}				
				}			
			}
		});
		
		panelDownload.add(btnDescargar);
		paneladm.add(panelDownload);
		
		panelProgressBar = new JPanel();
		panelProgressBar.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));
		panelProgressBar.setBorder(new EmptyBorder(0, 0, 8, 0));

		UIManager.put("ProgressBar.background", Color.decode("#1E1E1C")); // FONDO SIN CARGAR
		UIManager.put("ProgressBar.selectionBackground", Color.WHITE); // TEXTO SIN CARGAR       
		UIManager.put("ProgressBar.selectionForeground", Color.decode("#000000")); // TEXTO CARGANDO
		UIManager.put("ProgressBar.foreground", Color.decode("#11FF00")); //FONDO CARGANDO

		barTipodeCambio = new JProgressBar(0, 100);
		barTipodeCambio.setStringPainted(true);
		barTipodeCambio.setBorderPainted(true);
		barTipodeCambio.setFont(Exo2SemiBold);
		barTipodeCambio.setPreferredSize(new Dimension(380, 20));
		panelProgressBar.add(barTipodeCambio);
		paneladm.add(panelProgressBar);
		
		panelRpta = new JPanel();
		panelRpta.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));
		panelRpta.setBorder(new EmptyBorder(0, 0, 12, 0));
		
		AreaResult = new JTextArea();
		AreaResult.setFont(Exo2SemiBold);
		AreaResult.setWrapStyleWord(true);
		AreaResult.setEditable(false);
		
		scrPaneEstCuxFech = new JScrollPane(AreaResult);
		scrPaneEstCuxFech.setPreferredSize(new Dimension(380, 150));
		panelRpta.add(scrPaneEstCuxFech);
		paneladm.add(panelRpta);
		
		add(paneladm);
		setVisible(true);
		pack();
		setResizable(false);

	}
	
	private static TipodeCambio InstanciaTipodeCambio;
	
	public static TipodeCambio getInstanciaTipodeCambio() throws Exception{
		if(InstanciaTipodeCambio == null){
			InstanciaTipodeCambio = new TipodeCambio();
		}
		return InstanciaTipodeCambio;
	}
	
	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}
	
	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		System.out.println("Abriendo");
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		System.out.println("Cerrando");
		mgr.WindowsStatClose = true;
		InstanciaTipodeCambio = null;
		dispose();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		System.out.println("Se Cerro Ventana del Reporte Historico Detalle");
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {

	}
	
}

class ProgressTipodeCambio implements PropertyChangeListener {

	private JProgressBar barTipodeCambio;

	ProgressTipodeCambio() {

	}

	ProgressTipodeCambio(JProgressBar b) {
		this.barTipodeCambio = b;
		barTipodeCambio.setValue(0);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			barTipodeCambio.setValue((int) evt.getNewValue());
		}
	}
}

class WorkerRptTipodeCambio extends SwingWorker<Void, Void> {

	private JTextArea AreaResult;
	private JTextField txtcodigo_cliente;
	private JButton btnDescargar;
	private JPanel paneladm;
	private JDateChooser JDFechaIni; 
	private JDateChooser JDFechaFin;
	WorkerRptTipodeCambio() {

	}

	WorkerRptTipodeCambio(JTextArea t, JButton expo, JPanel padm, JDateChooser fini, JDateChooser ffin) {
		AreaResult = t;
//		txtcodigo_cliente = tx;
		btnDescargar = expo;
		paneladm = padm;
		JDFechaIni = fini;
		JDFechaFin = ffin;
	}

	protected Void doInBackground() throws Exception {

		setProgress(1);
		AreaResult.append(1 + " % " + "\t" + " INICIANDO " + "\n");

		Thread.sleep(100);

		setProgress(50);
		AreaResult.append(50 + " % " + "\t" + " PROCESADO " + "\n");

		Thread.sleep(100);
		
		Date dateini = JDFechaIni.getDate();		
		String Xstrini = DateFormat.getDateInstance().format(dateini);
		
		SimpleDateFormat Ini = new SimpleDateFormat("dd/MM/yyyy");
		Date dIni = Ini.parse(Xstrini);
		Ini.applyPattern("yyyyMMdd");

		String newIni;
		newIni = Ini.format(dIni);
		
		Date datefin = JDFechaFin.getDate();		
		String Xstrfin = DateFormat.getDateInstance().format(datefin);

		SimpleDateFormat Fin = new SimpleDateFormat("dd/MM/yyyy");
		Date dFin = Fin.parse(Xstrfin);
		Fin.applyPattern("yyyyMMdd");

		String newFin;
		newFin = Fin.format(dFin);

		Excel_TipodeCambio exc = new Excel_TipodeCambio(newIni,newFin);
		setProgress(100);
		AreaResult.append(100 + " % " + "\t" + " PROCESADO " + "\n");

		return null;
	}

	@Override
	protected void done() {
		AreaResult.append("DESCARGAR FINALIZADA" + "\n");
		paneladm.setCursor(null);
		btnDescargar.setEnabled(true);
	}

}