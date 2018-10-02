package View;

import controller.Manager;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import report.excel.Excel_EstadoCuentaSunny;

public class EstadoCuentaSunny extends JInternalFrame implements InternalFrameListener {

	private Manager mgr = new Manager();
	
	private JButton btnDescargar;
	
	private JPanel paneladm;
	private JPanel panelDownload;
	private JPanel panelProgressBar;
	private JPanel panelRpta;
	

	
	private JTextArea AreaResult;
	private JScrollPane srcPaneEstCuSunny;
	private JProgressBar barPaneEstCuSunny;

	public EstadoCuentaSunny() throws Exception{
		Container CJIiEstCuSunny = this.getContentPane();
		
		setClosable(true); // Boton Cerrar
		setIconifiable(true); // Boton Minimizar
		setMaximizable(false);
		setResizable(false);
		setTitle("Reporte Estado de Cuenta Sunny");		
		setName("InternalFrameEstCuSunny");
		this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Logo_Srep.png")));
		
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
		panelDownload.setBorder(new EmptyBorder(10, 0, 8, 0));
		btnDescargar = new JButton("DESCARGAR");
		btnDescargar.setFont(Exo2Medium);
		btnDescargar.setPreferredSize(new Dimension(100,25));
		btnDescargar.setFocusPainted(false);
		
		btnDescargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				WorkerRptEstadoCuentaSunny worker = new WorkerRptEstadoCuentaSunny(AreaResult );
				worker.addPropertyChangeListener(new ProgressEstadoCuentaFecha(barPaneEstCuSunny));
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

		barPaneEstCuSunny = new JProgressBar(0, 100);
		barPaneEstCuSunny.setStringPainted(true);
		barPaneEstCuSunny.setBorderPainted(true);
		barPaneEstCuSunny.setFont(Exo2SemiBold);
		barPaneEstCuSunny.setPreferredSize(new Dimension(300, 20));
		panelProgressBar.add(barPaneEstCuSunny);
		paneladm.add(panelProgressBar);

		panelRpta = new JPanel();
		panelRpta.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));
		panelRpta.setBorder(new EmptyBorder(0, 0, 8, 0));

		AreaResult = new JTextArea();
		AreaResult.setFont(Exo2SemiBold);
		AreaResult.setWrapStyleWord(true);
		AreaResult.setEditable(false);

		srcPaneEstCuSunny = new JScrollPane(AreaResult);
		srcPaneEstCuSunny.setPreferredSize(new Dimension(300, 150));
		panelRpta.add(srcPaneEstCuSunny);
		paneladm.add(panelRpta);
		
		addInternalFrameListener(this);
		add(paneladm);
		setVisible(true);
		pack();
		setResizable(false);
	}
	
	private static EstadoCuentaSunny InstanciaEstadoCuentaSunny;

	public static EstadoCuentaSunny getInstanciaEstadoCuentaSunny() throws Exception {
		if (InstanciaEstadoCuentaSunny == null) {
			InstanciaEstadoCuentaSunny = new EstadoCuentaSunny();
		}
		return InstanciaEstadoCuentaSunny;
	}
	
	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
			
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		mgr.WindowsStatClose = true;
		InstanciaEstadoCuentaSunny = null;
		dispose();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
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

class ProgressEstadoCuentaSunny implements PropertyChangeListener {
	private JProgressBar barPaneEstCuSunny;

	ProgressEstadoCuentaSunny() {

	}

	ProgressEstadoCuentaSunny(JProgressBar b) {
		this.barPaneEstCuSunny = b;
		barPaneEstCuSunny.setValue(0);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			barPaneEstCuSunny.setValue((int) evt.getNewValue());
		}
	}
}

class WorkerRptEstadoCuentaSunny extends SwingWorker<Void, Void> {
	
	private JTextArea AreaResult;
	
	WorkerRptEstadoCuentaSunny() {

	}
	
	WorkerRptEstadoCuentaSunny(JTextArea t) {
		AreaResult = t;
	}
	
	protected Void doInBackground() throws Exception {
		
		setProgress(1);
		AreaResult.append(1 + " % " + "\t" + " INICIANDO " + "\n");

		Thread.sleep(100);

		setProgress(50);
		AreaResult.append(50 + " % " + "\t" + " PROCESADO " + "\n");

		Excel_EstadoCuentaSunny EstCuSunny = new Excel_EstadoCuentaSunny();
		
		
		Thread.sleep(100);
		
		setProgress(100);
		AreaResult.append(100 + " % " + "\t" + " PROCESADO " + "\n");
		
		return null;
	}
	
	@Override
	protected void done()  {
		AreaResult.append("DESCARGA FINALIZADA" + "\n");
	}
	
	
}