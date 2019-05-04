package View;

import controller.Manager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import report.excel.Excel_DireccionCliente;

public class DireccionCliente extends JInternalFrame implements InternalFrameListener{

	private Manager mgr = new Manager();
	
	private JPanel paneladm,panelDownload,panelProgressBar,panelRpta;
	private JButton btnDescargar;
	private JTextArea AreaResult;
	private JScrollPane scrPaneEstCuxFech;
	private JProgressBar barDireccionCliente;
	
	public DireccionCliente() throws Exception{
		
		mgr = new Manager();
		
		setClosable(true); // Boton Cerrar
		setIconifiable(true); // Boton Minimizar
		setMaximizable(false);
		setResizable(false);
		setTitle("Reporte Dirección Cliente");
		
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
				paneladm.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				btnDescargar.setEnabled(false);
				WorkerRptDireccionCliente worker = new WorkerRptDireccionCliente(AreaResult, btnDescargar, paneladm);
				worker.addPropertyChangeListener(new ProgressDireccionCliente(barDireccionCliente));
				worker.execute();
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

		barDireccionCliente = new JProgressBar(0, 100);
		barDireccionCliente.setStringPainted(true);
		barDireccionCliente.setBorderPainted(true);
		barDireccionCliente.setFont(Exo2SemiBold);
		barDireccionCliente.setPreferredSize(new Dimension(300, 20));
		panelProgressBar.add(barDireccionCliente);
		paneladm.add(panelProgressBar);
		
		panelRpta = new JPanel();
		panelRpta.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));
		panelRpta.setBorder(new EmptyBorder(0, 0, 12, 0));
		
		AreaResult = new JTextArea();
		AreaResult.setFont(Exo2SemiBold);
		AreaResult.setWrapStyleWord(true);
		AreaResult.setEditable(false);
		
		scrPaneEstCuxFech = new JScrollPane(AreaResult);
		scrPaneEstCuxFech.setPreferredSize(new Dimension(300, 150));
		panelRpta.add(scrPaneEstCuxFech);
		paneladm.add(panelRpta);
		
		add(paneladm);
		
		setVisible(true);
		pack();
		setResizable(false);
		
	}
	
	
	private static DireccionCliente InstanciaDireccionCliente;
	
	public static DireccionCliente getInstanciaDireccionCliente() throws Exception{
		if(InstanciaDireccionCliente == null){
			InstanciaDireccionCliente = new DireccionCliente();
		}
		return InstanciaDireccionCliente;
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
		InstanciaDireccionCliente = null;
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

class ProgressDireccionCliente implements PropertyChangeListener {

	private JProgressBar barDireccionCliente;

	ProgressDireccionCliente() {

	}

	ProgressDireccionCliente(JProgressBar b) {
		this.barDireccionCliente = b;
		barDireccionCliente.setValue(0);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			barDireccionCliente.setValue((int) evt.getNewValue());
		}
	}
}


class WorkerRptDireccionCliente extends SwingWorker<Void, Void> {

	private JTextArea AreaResult;
	private JButton btnDescargar;
	private JPanel paneladm;

	WorkerRptDireccionCliente() {

	}

	WorkerRptDireccionCliente(JTextArea t, JButton expo, JPanel padm) {
		AreaResult = t;
		btnDescargar = expo;
		paneladm = padm;

	}

	protected Void doInBackground() throws Exception {

		setProgress(1);
		AreaResult.append(1 + " % " + "\t" + " INICIANDO " + "\n");

		Thread.sleep(100);

		setProgress(50);
		AreaResult.append(50 + " % " + "\t" + " PROCESADO " + "\n");

		Thread.sleep(100);

		
		Excel_DireccionCliente exc = new Excel_DireccionCliente();
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