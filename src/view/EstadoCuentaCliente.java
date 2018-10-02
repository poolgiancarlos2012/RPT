package View;

import controller.Manager;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import View.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.IllegalComponentStateException;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.util.concurrent.ExecutionException;

import java.awt.Desktop;
import java.awt.List;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import report.excel.Excel_EstadoCuentaCliente;

public class EstadoCuentaCliente extends JInternalFrame implements InternalFrameListener {
        
        private Manager mgr;

        private JLabel lblcodigo_cliente;
        private JTextField txtcodigo_cliente;
        private JButton btnexportar;
        private JTextArea taskOutput;
         private JScrollPane scrPane;
        
        private JProgressBar bar;
        
        private JPanel paneladm;
        private JPanel panelop;
        private JPanel panelbar;
        private JPanel panelrpta;

        public JTextField getTxtcodigo_cliente() {
                return txtcodigo_cliente;
        }

        public void setTxtcodigo_cliente(JTextField txtcodigo_cliente) {
                this.txtcodigo_cliente = txtcodigo_cliente;
        }

    public EstadoCuentaCliente() throws Exception {
//        super("Site: ", true, true, false,false);

        setClosable(true); // Boton Cerrar
        setIconifiable(true); // Boton Minimizar
        setMaximizable(false); 
        setResizable(false); 
        setTitle("Reporte Estado de Cuenta"); 
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
//        Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-SemiBold.otf"));
	String strexosemibold = new File("src\\font\\exo2\\Exo2-SemiBold.otf").getAbsolutePath();
	Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File(strexosemibold));

	ge.registerFont(StrExo2SemiBold);
        Font Exo2SemiBold = new Font("Exo 2 Semi Bold", Font.PLAIN, 13);
        
//        Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-Medium.otf"));
        String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();
	Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
	ge.registerFont(StrExo2Medium);
        Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
        
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addInternalFrameListener(this);        
        
        paneladm = new JPanel();
        paneladm.setLayout(new BoxLayout(paneladm, BoxLayout.Y_AXIS));              
        
        panelop = new JPanel();
        panelop.setLayout(new FlowLayout(FlowLayout.CENTER, 5,5));    
//        panelop.setBackground(Color.YELLOW);
        
        lblcodigo_cliente = new JLabel();
        lblcodigo_cliente.setFont(Exo2SemiBold);
        lblcodigo_cliente.setText("CODIGO CLIENTE");        
        panelop.add(lblcodigo_cliente);
        
        txtcodigo_cliente = new JTextField();
        txtcodigo_cliente.setColumns(10 );
        txtcodigo_cliente.setFont(Exo2Medium);
        txtcodigo_cliente.setText("");
        panelop.add(txtcodigo_cliente);
        
        btnexportar = new JButton();
        Dimension SizeButton = new Dimension(100,25);
        btnexportar.setPreferredSize(SizeButton);
        btnexportar.setFont(Exo2SemiBold);
        btnexportar.setText("DESCARGAR");
        btnexportar.setFocusPainted(false);
        btnexportar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
//			String cod_cliente = txtcodigo_cliente.getText();
//			Excel_EstadoCuentaCliente ec = new Excel_EstadoCuentaCliente(cod_cliente);

			paneladm.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			btnexportar.setEnabled(false);
			WorkerDoSomething worker = new WorkerDoSomething(taskOutput, txtcodigo_cliente, btnexportar, paneladm);
			worker.addPropertyChangeListener(new ProgressListener(bar));
			worker.execute();

		} 
        });
		
        panelop.add(btnexportar);        
        paneladm.add(panelop);        
        
        panelbar = new JPanel();
        panelbar.setLayout(new FlowLayout(FlowLayout.CENTER, 5,5));   
//        panelbar.setBackground(Color.ORANGE);

        UIManager.put("ProgressBar.background", Color.decode("#1E1E1C")); // FONDO SIN CARGAR
        UIManager.put("ProgressBar.selectionBackground",Color.WHITE); // TEXTO SIN CARGAR       
        UIManager.put("ProgressBar.selectionForeground",Color.decode("#000000")); // TEXTO CARGANDO
        UIManager.put("ProgressBar.foreground", Color.decode("#11FF00")); //FONDO CARGANDO
        
        bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        bar.setBorderPainted(true);
        bar.setFont(Exo2SemiBold);
        bar.setPreferredSize(new Dimension(300,20));
//        bar.setValue(50);
        panelbar.add(bar);
        paneladm.add(panelbar);
        
        panelrpta = new JPanel();
        panelrpta.setLayout(new FlowLayout(FlowLayout.CENTER, 5,5));   
//        panelrpta.setBackground(Color.BLUE);
        
//        Font StrExo2SemiBold2 = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-SemiBold.otf"));
        String strexosemibold2 = new File("src\\font\\exo2\\Exo2-SemiBold.otf").getAbsolutePath();
	Font StrExo2SemiBold2 = Font.createFont(Font.TRUETYPE_FONT, new File(strexosemibold2));
	ge.registerFont(StrExo2SemiBold2);
        Font Exo2SemiBold2 = new Font("Exo 2 Semi Bold", Font.PLAIN, 12);
        
        
        
        taskOutput = new JTextArea();
//        taskOutput.setMargin(new Insets(5,5,5,5));       
        taskOutput.setFont(Exo2SemiBold2);
        taskOutput.setWrapStyleWord(true);
        taskOutput.setEditable(false);
        
        scrPane = new JScrollPane(taskOutput);
        scrPane.setPreferredSize(new Dimension(300, 150));
//        panelrpta.add(taskOutput);
        panelrpta.add(scrPane);
        paneladm.add(panelrpta);
        
        add(paneladm);       
        pack();
        setResizable(false);
        
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			setVisible(true);
		}
	});


        
    }
    
        private static EstadoCuentaCliente myInstance;
        public static EstadoCuentaCliente getInstance() throws Exception {
            if(myInstance == null){
                myInstance = new EstadoCuentaCliente();
            }
            return myInstance;
        }


        public void setManager(Manager mgr){
                this.mgr = mgr;
        }

        @Override
        public void internalFrameOpened(InternalFrameEvent e) {

        }
        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
                myInstance= null;
                dispose();
        }
        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
                System.out.println("3");
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

class ProgressListener implements PropertyChangeListener {

	private JProgressBar bar;

	ProgressListener() {

	}

	ProgressListener(JProgressBar b) {
		this.bar = b;
		bar.setValue(0);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			bar.setValue((int) evt.getNewValue());
		}
	}
}

class WorkerDoSomething extends SwingWorker< Void, Void> {

	private JTextArea taskOutput;
	private JTextField txtcodigo_cliente;
	private JButton btnexportar;
	private JPanel paneladm;

	WorkerDoSomething() {

	}

	WorkerDoSomething(JTextArea t, JTextField tx, JButton expo, JPanel padm) {
		taskOutput = t;
		txtcodigo_cliente = tx;
		btnexportar = expo;
		paneladm = padm;
	}

	protected Void doInBackground() throws Exception {

		setProgress(1);
		taskOutput.append(1 + " % " + "\t" + " INICIANDO " + "\n");

		Thread.sleep(100);

		setProgress(50);
		taskOutput.append(50 + " % " + "\t" + " PROCESADO " + "\n");

		Thread.sleep(100);

		String cod_cliente = txtcodigo_cliente.getText();
		Excel_EstadoCuentaCliente exc = new Excel_EstadoCuentaCliente(cod_cliente);
		setProgress(100);
		taskOutput.append(100 + " % " + "\t" + " PROCESADO " + "\n");

		return null;
	}

	@Override
	protected void done() {
		taskOutput.append("DESCARGAR FINALIZADA" + "\n");

		paneladm.setCursor(null);
		btnexportar.setEnabled(true);

	}

}


