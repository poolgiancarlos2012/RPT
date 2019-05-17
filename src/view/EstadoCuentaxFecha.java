package View;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import model.vo.AgenciaVO;

import controller.Manager;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;
import model.logic.Logical;
import model.vo.ClienteVO;
//import model.vo.ClienteVO;
import report.excel.Excel_EstadoCuentaFecha;
import report.excel.Excel_HistoricoDetalleDoc;
import java.awt.Color;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.cnx.Cnx;


public class EstadoCuentaxFecha extends JInternalFrame implements InternalFrameListener{
	
	private Manager mgr = new Manager();
	
	private JTable JTCliente;

	public JTable getJTCliente() {
		return JTCliente;
	}

	public void setJTCliente(JTable JTCliente) {
		this.JTCliente = JTCliente;
	}
	
	
	
	private boolean DEBUG = true;
	
	private JLabel lblcodigo_cliente,lblemisionini,lblemisionfini,lblfechemision;
	public JTextField txtcodigo_cliente,txtrazon_social;
	private JButton btnDescargar, btntest;
	private JTextArea AreaResult;
	private JScrollPane scrPaneEstCuxFech;

	public JTextField getTxtcodigo_cliente() {
		return txtcodigo_cliente;
	}

	public void setTxtcodigo_cliente(JTextField txtcodigo_cliente) {
		this.txtcodigo_cliente = txtcodigo_cliente;
	}

	public JTextField getTxtrazon_social() {
		return txtrazon_social;
	}

	public void setTxtrazon_social(JTextField txtrazon_social) {
		this.txtrazon_social = txtrazon_social;
	}
	
	
	
	static DefaultComboBoxModel dcbagenCAISAC;
	
	private JProgressBar barEstCuxFech;
	
	private JPanel paneladm;
	private JPanel panelop;
	private JPanel panelrazon_social;
	private JPanel panelAllClientes;
	private JPanel panelenvol;
	private JPanel panelemp;
	private JPanel panelabonos;
	private JPanel panelemision;
	private JPanel panelfchemi;
	private JPanel panelpencancel;
	private JPanel panelcancel;
	private JPanel panelDownload;
	private JPanel panelProgressBar;
	private JPanel panelRpta;
	
	private JCheckBox chkAllClientes,chkCAISAC, chkANDEX, chkSEMILLAS, chkSUNNY,chkAbonos;
	private JComboBox  cboAgenCAISAC,cboAgenANDEX, cboAgenSEMILLAS, cboAgenSUNNY;
		
	private JRadioButton rbpendcancel;
	private JRadioButton rbpendientes;
	private JDateChooser Emisionini;
	private JDateChooser Emisionfin;
	
	private String strage0002, strage0003, strage0004, strage0016;
	private String CAISAC, ANDEX, SEMILLAS, SUNNY;
	
	private String strPendCancel;
	private String strAbo;
		
	private GroupLayout glempresas;
	
	public EstadoCuentaxFecha() throws Exception{
		
		Container CJIiEstCuxFech = this.getContentPane();
		
		setClosable(true); // Boton Cerrar
		setIconifiable(true); // Boton Minimizar
		setMaximizable(false);
		setResizable(false);
		setTitle("Reporte Estado de Cuenta x Fecha");
		
		setName("InternalFrameEstCuxFech");
		
//		mgr = new Manager();

//		mgr.setWindowsStat("Cerrado");

//		String stricoga = new File("src\\img\\icono_grupo_andina.png").getAbsolutePath();
//		setIconImage(Toolkit.getDefaultToolkit().getImage(stricoga));
//		setFrameIcon(Toolkit.getDefaultToolkit().getImage(stricoga));

		this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Srep16x16.png")));

//		setFrameIcon(stricoga);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-SemiBold.otf"));
		String strexosemibold = new File("src\\font\\exo2\\Exo2-SemiBold.otf").getAbsolutePath();
		Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File(strexosemibold));

		ge.registerFont(StrExo2SemiBold);
		Font Exo2SemiBold = new Font("Exo 2 Semi Bold", Font.PLAIN, 13);

//		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-Medium.otf"));

		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();		
		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
		ge.registerFont(StrExo2Medium);
		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
		
//		Font StrExo2Regular = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-Regular.otf"));

		String strexoregular = new File("src\\font\\exo2\\Exo2-Regular.otf").getAbsolutePath();		
		Font StrExo2Regular = Font.createFont(Font.TRUETYPE_FONT, new File(strexoregular));
		ge.registerFont(StrExo2Regular);
		Font Exo2Regular = new Font("Exo 2 Regular", Font.PLAIN, 12);
		
//		Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-Light.otf"));
		String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();		
		Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
		ge.registerFont(StrExo2Light);
		Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		addInternalFrameListener(this);

		paneladm = new JPanel();
//		paneladm.setPreferredSize(new Dimension(328, 480));
		paneladm.setLayout(new BoxLayout(paneladm, BoxLayout.Y_AXIS));

		panelop = new JPanel();
//		panelop.setBorder(new EmptyBorder(8, 8, 8, 8));
		panelop.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));

		lblcodigo_cliente = new JLabel();
		lblcodigo_cliente.setFont(Exo2Medium);
		lblcodigo_cliente.setText("CODIGO CLIENTE");
		panelop.add(lblcodigo_cliente);

		txtcodigo_cliente = new JTextField();
		txtcodigo_cliente.setPreferredSize(new Dimension(198,19));
		txtcodigo_cliente.setFont(Exo2Light);
		txtcodigo_cliente.setText("");
		txtcodigo_cliente.setName("txtcodigo_cliente");

		txtcodigo_cliente.setFocusTraversalKeysEnabled(false);
		
		panelop.add(txtcodigo_cliente);
		
		/*****/

		javax.swing.Action myAction = new javax.swing.AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				try {
					JDialog asyncDialog = JDialogClientes();		
					
					asyncDialog.setModal(true);
					asyncDialog.setVisible(true);	
					
				} catch (Exception ex) {
					Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		};

		txtcodigo_cliente.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, Event.SHIFT_MASK), "myCode");
		txtcodigo_cliente.getActionMap().put("myCode", myAction);


		/*****/
		paneladm.add(panelop);
		
		panelrazon_social = new JPanel();
//		panelrazon_social.setBorder(new EmptyBorder(8, 8, 8, 8));
		panelrazon_social.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		txtrazon_social = new JTextField();
		txtrazon_social.setPreferredSize(new Dimension(300,19));
		txtrazon_social.setFont(Exo2Light);
		txtrazon_social.setText("");
		panelrazon_social.add(txtrazon_social);
		paneladm.add(panelrazon_social);	
		
		
		panelAllClientes = new JPanel();
//		panelAllClientes.setBackground(Color.GREEN);
		panelAllClientes.setLayout(new FlowLayout(FlowLayout.LEFT,8,0));
		panelAllClientes.setBorder(new EmptyBorder(8, 0, 8, 0));
		chkAllClientes = new JCheckBox("TODOS LOS CLIENTES");
		chkAllClientes.setFont(Exo2Medium);
		chkAllClientes.setFocusPainted( false );
		panelAllClientes.add(chkAllClientes);
		paneladm.add(panelAllClientes);
		
		chkAllClientes.setSelected(true);
		txtcodigo_cliente.setEnabled(false);
		txtrazon_social.setEnabled(false);
		txtcodigo_cliente.setBackground(Color.decode("#DBDBDB"));
		txtrazon_social.setBackground(Color.decode("#DBDBDB"));					
		txtcodigo_cliente.setText("");
		txtrazon_social.setText("");
		
		chkAllClientes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {			
				if( chkAllClientes.isSelected() ) {
					txtcodigo_cliente.setEnabled(false);
					txtrazon_social.setEnabled(false);
					txtcodigo_cliente.setBackground(Color.decode("#DBDBDB"));
					txtrazon_social.setBackground(Color.decode("#DBDBDB"));					
					txtcodigo_cliente.setText("");
					txtrazon_social.setText("");					
				} else {
					txtcodigo_cliente.setEnabled(true);
//					txtrazon_social.setEnabled(true);					
					txtcodigo_cliente.setBackground(Color.decode("#FFFFFF"));
//					txtrazon_social.setBackground(Color.decode("#FFFFFF"));					
				}
			}
		});
		
		
		panelenvol = new JPanel();
		panelenvol.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelenvol.setBorder(new EmptyBorder(0, 0, 0, 0));
//		panelenvol.setPreferredSize(new Dimension(200, 300));
//		panelenvol.setBackground(Color.ORANGE);
		
		
		panelemp = new JPanel();
		panelemp.setBorder(new EmptyBorder(4, 8, 4, 8));
		panelemp.setPreferredSize(new Dimension(328, 115));
//		panelemp.setBackground(Color.BLUE);
		glempresas = new GroupLayout(panelemp);		
		
		
		chkCAISAC = new JCheckBox("CAISAC");
		chkANDEX = new JCheckBox("ANDEX");
		chkSEMILLAS = new JCheckBox("SEMILLAS");
		chkSUNNY = new JCheckBox("SUNNY");
		
		chkCAISAC.setFont(Exo2Medium);
		chkANDEX.setFont(Exo2Medium);
		chkSEMILLAS.setFont(Exo2Medium);
		chkSUNNY.setFont(Exo2Medium);
		
		CAISAC = "";
		ANDEX = "";
		SEMILLAS = "";
		SUNNY = "";
		
		strage0002 = "";
		strage0003 = "";
		strage0004 = "";
		strage0016 = "";
		
		chkCAISAC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkCAISAC.isSelected()) {
					cboAgenCAISAC.setEnabled(true);
					CAISAC = "0002";
					
					Item itemAgeCAISAC = (Item) cboAgenCAISAC.getSelectedItem();
					String idAgeCAISAC = itemAgeCAISAC.getId();
					strage0002 = idAgeCAISAC;
					
				} else {
					cboAgenCAISAC.setEnabled(false);
					CAISAC = "";
					strage0002 = "";
				}
			}
		});
		
		chkANDEX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkANDEX.isSelected()) {
					cboAgenANDEX.setEnabled(true);
					ANDEX = "0003";
					
					Item itemAgeANDEX = (Item) cboAgenANDEX.getSelectedItem();
					String idAgeANDEX = itemAgeANDEX.getId();
					strage0003 = idAgeANDEX;
					
				} else {
					cboAgenANDEX.setEnabled(false);
					ANDEX = "";
					strage0003 = "";
				}
			}
		});
		
		chkSEMILLAS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkSEMILLAS.isSelected()) {
					cboAgenSEMILLAS.setEnabled(true);
					SEMILLAS = "0004";
					
					Item itemAgeSEMILLAS = (Item) cboAgenSEMILLAS.getSelectedItem();
					String idAgeSEMILLAS = itemAgeSEMILLAS.getId();
					strage0004 = idAgeSEMILLAS;
					
				} else {
					cboAgenSEMILLAS.setEnabled(false);
					SEMILLAS = "";
					strage0004 = "";
				}
			}
		});
		
		chkSUNNY.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkSUNNY.isSelected()) {
					cboAgenSUNNY.setEnabled(true);					
					SUNNY = "0016";
					
					Item itemAgeSUNNY = (Item) cboAgenSUNNY.getSelectedItem();
					String idAgeSUNNY = itemAgeSUNNY.getId();
					strage0016 = idAgeSUNNY;
					
//					if( strage0016.equals("0") ){
//						System.out.println(cboAgenSUNNY.getItemCount());
//						
//						int count0016 = cboAgenSUNNY.getItemCount()-1;
//						
//						for(int h = 1; h <= count0016-1; h++){
//							System.out.println(cboAgenSUNNY.getItemAt(h));
//							
//						}
//						
//					}else{
//						
//					}
					
				} else {
					cboAgenSUNNY.setEnabled(false);
					SUNNY = "";
					strage0016 = "";
				}
			}
		});
		
		cboAgenCAISAC = new JComboBox();				
		ArrayList<AgenciaVO> arrAgenciaCAISAC = mgr.ConsultarAgenciaCAISAC();	
		for(AgenciaVO ag0002: arrAgenciaCAISAC){					
			cboAgenCAISAC.addItem(new Item(ag0002.getCLAVE(),ag0002.getAGENCIA()));			
		}
		cboAgenCAISAC.setSelectedIndex(0);
		cboAgenCAISAC.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDescription());
			strage0002 = item.getId();
		});		
		cboAgenCAISAC.setEnabled(false);
		

		cboAgenANDEX = new JComboBox();
		ArrayList<AgenciaVO> arrAgenciaANDEX = mgr.ConsultarAgenciaANDEX();		
		for(AgenciaVO ag0003: arrAgenciaANDEX){				
			cboAgenANDEX.addItem(new Item(ag0003.getCLAVE(),ag0003.getAGENCIA()));			
		}
		cboAgenANDEX.setSelectedIndex(0);		
		cboAgenANDEX.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDescription());
			strage0003 = item.getId();
		});		
		cboAgenANDEX.setEnabled(false);
				
		cboAgenSEMILLAS = new JComboBox();		
		ArrayList<AgenciaVO> arrAgenciaSEMILLAS = mgr.ConsultarAgenciaSEMILLAS();
		for(AgenciaVO ag0004: arrAgenciaSEMILLAS){				
			cboAgenSEMILLAS.addItem(new Item(ag0004.getCLAVE(),ag0004.getAGENCIA()));			
		}
		cboAgenSEMILLAS.setSelectedIndex(0);		
		cboAgenSEMILLAS.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDescription());
			strage0004 = item.getId();
		});		
		cboAgenSEMILLAS.setEnabled(false);
		
		cboAgenSUNNY = new JComboBox();		
		ArrayList<AgenciaVO> arrAgenciaSUNNY = mgr.ConsultarAgenciaSUNNY();
		cboAgenSUNNY.addItem(new Item("0", "TODOS"));
		for(AgenciaVO ag0016: arrAgenciaSUNNY){				
			cboAgenSUNNY.addItem(new Item(ag0016.getCLAVE(),ag0016.getAGENCIA()));			
		}
		cboAgenSUNNY.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDescription());
			strage0016 = item.getId();
		});		
		cboAgenSUNNY.setEnabled(false);

		
		chkCAISAC.setFocusPainted( false );
		chkANDEX.setFocusPainted( false );
		chkSEMILLAS.setFocusPainted( false );
		chkSUNNY.setFocusPainted( false );	
//		chkAllClientes.setFocusPainted( false );
//		chkAbonos.setFocusPainted( false );

		panelemp.setLayout(glempresas);
		
		glempresas.setAutoCreateGaps(true);
		glempresas.setAutoCreateContainerGaps(false);
		
		glempresas.setHorizontalGroup(glempresas.createSequentialGroup()
				.addGroup(glempresas.createParallelGroup(LEADING)
						.addComponent(chkCAISAC, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(chkANDEX, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(chkSEMILLAS, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(chkSUNNY, 0, GroupLayout.DEFAULT_SIZE, 100)
				)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 50, 50)
				.addGroup(glempresas.createParallelGroup(LEADING)
						.addComponent(cboAgenCAISAC, 0, GroupLayout.DEFAULT_SIZE, 154)
						.addComponent(cboAgenANDEX, 0, GroupLayout.DEFAULT_SIZE, 154)
						.addComponent(cboAgenSEMILLAS, 0, GroupLayout.DEFAULT_SIZE, 154)
						.addComponent(cboAgenSUNNY, 0, GroupLayout.DEFAULT_SIZE, 154)
				)
		);

		glempresas.setVerticalGroup(glempresas.createSequentialGroup()
				.addGroup(glempresas.createParallelGroup(BASELINE)
						.addComponent(chkCAISAC, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(cboAgenCAISAC, 0, GroupLayout.DEFAULT_SIZE, 154)
				)
				.addGroup(glempresas.createParallelGroup(BASELINE)
						.addComponent(chkANDEX, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(cboAgenANDEX, 0, GroupLayout.DEFAULT_SIZE, 154)
				)
				.addGroup(glempresas.createParallelGroup(BASELINE)
						.addComponent(chkSEMILLAS, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(cboAgenSEMILLAS, 0, GroupLayout.DEFAULT_SIZE, 154)
				)
				.addGroup(glempresas.createParallelGroup(BASELINE)
						.addComponent(chkSUNNY, 0, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(cboAgenSUNNY, 0, GroupLayout.DEFAULT_SIZE, 154)
				)
		);
		
		panelenvol.add(panelemp);
		paneladm.add(panelenvol);
		
		panelabonos = new JPanel();
//		panelabonos.setBackground(Color.GREEN);
		panelabonos.setLayout(new FlowLayout(FlowLayout.LEFT,8,0));
		panelabonos.setBorder(new EmptyBorder(8, 0, 8, 0));
		chkAbonos = new JCheckBox("MOSTRAR ABONOS");
		chkAbonos.setFont(Exo2Medium);
		chkAbonos.setFocusPainted( false );
		panelabonos.add(chkAbonos);
		paneladm.add(panelabonos);
		
		strAbo = "";
		chkAbonos.setSelected(true);
		chkAbonos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkAbonos.isSelected()) {
					strAbo = "ABO";
					
				} else {
					strAbo = "";
				}
			}
		});
		
		if (chkAbonos.isSelected()) {
			strAbo = "ABO";
		} else {
			strAbo = "";
		}
		
		panelfchemi = new JPanel();
		
		panelfchemi.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));
		panelfchemi.setBorder(new EmptyBorder(10, 0, 4, 0));
		lblfechemision = new JLabel();
		lblfechemision.setFont(Exo2Medium);
		lblfechemision.setText("FECHA EMISION");
		panelfchemi.add(lblfechemision);
		paneladm.add(panelfchemi);
		
		panelemision = new JPanel();
		panelemision.setBorder(new EmptyBorder(4, 0, 4, 0));
		panelemision.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));		
		
		lblemisionini = new JLabel();
		lblemisionini.setFont(Exo2Medium);
		lblemisionini.setText("INI");
		panelemision.add(lblemisionini);
		
		Emisionini = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');		
		JTextFieldDateEditor TXTEmisionini = (JTextFieldDateEditor)this.Emisionini.getComponent(1);
		TXTEmisionini.setHorizontalAlignment(JTextField.CENTER);
		TXTEmisionini.setFont(Exo2Medium);
		Emisionini.setPreferredSize(new Dimension(114,19));
		panelemision.add(Emisionini);	
		
		lblemisionfini = new JLabel();
		lblemisionfini.setFont(Exo2Medium);
		lblemisionfini.setText("FIN");
		panelemision.add(lblemisionfini);
		
		Emisionfin = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');		
		JTextFieldDateEditor TXTEmisionfin = (JTextFieldDateEditor)this.Emisionfin.getComponent(1);
		TXTEmisionfin.setHorizontalAlignment(JTextField.CENTER);
		TXTEmisionfin.setFont(Exo2Medium);		
		Emisionfin.setPreferredSize(new Dimension(115,19));
		panelemision.add(Emisionfin);
		paneladm.add(panelemision);
				
		panelpencancel = new JPanel();		
		panelpencancel.setLayout(new FlowLayout(FlowLayout.LEFT,8,0));
		panelpencancel.setBorder(new EmptyBorder(10, 0, 0, 0));
		rbpendcancel = new JRadioButton("PENDIENTES Y CANCELADOS");
		rbpendcancel.setSelected(true);
		rbpendcancel.setActionCommand("PC");
		rbpendcancel.setFocusPainted( false );		
		rbpendcancel.setFont(Exo2Medium);
		panelpencancel.add(rbpendcancel);
		paneladm.add(panelpencancel);
				
		panelcancel = new JPanel();		
		panelcancel.setLayout(new FlowLayout(FlowLayout.LEFT,8,0));
		panelcancel.setBorder(new EmptyBorder(0, 0, 8, 0));
		rbpendientes = new JRadioButton("PENDIENTES");
		rbpendientes.setActionCommand("P");
		rbpendientes.setFocusPainted(false);
		rbpendientes.setFont(Exo2Medium);
		panelcancel.add(rbpendientes);
		paneladm.add(panelcancel);
		
		ButtonGroup PendientCancel = new ButtonGroup();
		PendientCancel.add(rbpendcancel);
		PendientCancel.add(rbpendientes);
		
//		add(rbpendcancel);
//		add(rbpendientes);
		
		panelDownload = new JPanel();
		panelDownload.setLayout(new FlowLayout(FlowLayout.CENTER,12,0));
		panelDownload.setBorder(new EmptyBorder(0, 0, 8, 0));
		
		String key_go = new File("src\\img\\fatcow_icon\\16x16_0620\\page_excel.png").getAbsolutePath();		
		ImageIcon iconkey_go = new ImageIcon(key_go);
		
		btnDescargar = new JButton("DESCARGAR",iconkey_go);
		btnDescargar.setFont(Exo2Medium);
		btnDescargar.setPreferredSize(new Dimension(100,25));
		btnDescargar.setFocusPainted(false);
		
		btnDescargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				boolean checkedCAISAC = chkCAISAC.isSelected();
//				boolean checkedANDEX = chkANDEX.isSelected();
//				boolean checkedSEMILLAS = chkSEMILLAS.isSelected();
//				boolean checkedSUNNY = chkSUNNY.isSelected();
				
//				String xEmisionini = ((JTextField)Emisionini.getDateEditor().getUiComponent()).getText();
//				String xEmisionfin = ((JTextField)Emisionfin.getDateEditor().getUiComponent()).getText();

//				System.out.println("Selected Radio Button: " + PendientCancel.getSelection().getActionCommand());

				strPendCancel = PendientCancel.getSelection().getActionCommand();

				Date xEmisionini = Emisionini.getDate();
				Date xEmisionfin = Emisionfin.getDate();

				
				System.out.println(xEmisionini);

				if(!chkAllClientes.isSelected() && txtcodigo_cliente.getText().equals("")){
					try {
							String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
							Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
							ge.registerFont(StrExo2Light);
							Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

							String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

							ImageIcon IconSuccess = new ImageIcon(iconaccept);

							JLabel lblsuccess = new JLabel(".: Ingresar al Cliente :.");
							lblsuccess.setFont(Exo2Light);
							JOptionPane.showMessageDialog (null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE,IconSuccess);

						} catch (FontFormatException ex) {
							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex) {
							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
						}
				} else {
					if (!chkCAISAC.isSelected() && !chkANDEX.isSelected() && !chkSEMILLAS.isSelected() && !chkSUNNY.isSelected() ) { // SI NINGUNA EMPRESA ESTA SELECCIONADA
					
						try {
							String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
							Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
							ge.registerFont(StrExo2Light);
							Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

							String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

							ImageIcon IconSuccess = new ImageIcon(iconaccept);

							JLabel lblsuccess = new JLabel(".: Seleccionar al menos una Empresa :.");
							lblsuccess.setFont(Exo2Light);
							JOptionPane.showMessageDialog (null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE,IconSuccess);

						} catch (FontFormatException ex) {
							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex) {
							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
						}

					} else {

						if( xEmisionini != null &&  xEmisionfin != null ){
							paneladm.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							btnDescargar.setEnabled(false);
							txtcodigo_cliente.setEnabled(false);
							txtrazon_social.setEnabled(false);
							chkAllClientes.setEnabled(false);
							chkCAISAC.setEnabled(false);
							chkANDEX.setEnabled(false);
							chkSEMILLAS.setEnabled(false);
							chkSUNNY.setEnabled(false);
							chkAbonos.setEnabled(false);
							cboAgenCAISAC.setEnabled(false);
							cboAgenANDEX.setEnabled(false);
							cboAgenSEMILLAS.setEnabled(false);
							cboAgenSUNNY.setEnabled(false);
							Emisionini.setEnabled(false);
							Emisionfin.setEnabled(false);
							rbpendcancel.setEnabled(false);
							rbpendientes.setEnabled(false);
							WorkerRptEstadoCuentaFecha worker = new WorkerRptEstadoCuentaFecha(AreaResult, txtcodigo_cliente, txtrazon_social, chkAllClientes, chkCAISAC, chkANDEX, chkSEMILLAS, chkSUNNY, chkAbonos, cboAgenCAISAC, cboAgenANDEX, cboAgenSEMILLAS, cboAgenSUNNY, Emisionini, Emisionfin, rbpendcancel, rbpendientes, btnDescargar, paneladm, CAISAC, ANDEX , SEMILLAS , SUNNY , strage0002, strage0003 , strage0004, strage0016, strPendCancel,strAbo );
							worker.addPropertyChangeListener(new ProgressEstadoCuentaFecha(barEstCuxFech));
							worker.execute();					
						} else {
							try {
								String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
								Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
								ge.registerFont(StrExo2Light);
								Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

								String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

								ImageIcon IconSuccess = new ImageIcon(iconaccept);

								JLabel lblsuccess = new JLabel(".: Ingresar Fecha de Inicio y Fin :.");
								lblsuccess.setFont(Exo2Light);
								JOptionPane.showMessageDialog (null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE,IconSuccess);
							} catch (FontFormatException ex) {
								Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
							} catch (IOException ex) {
								Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
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

		barEstCuxFech = new JProgressBar(0, 100);
		barEstCuxFech.setStringPainted(true);
		barEstCuxFech.setBorderPainted(true);
		barEstCuxFech.setFont(Exo2SemiBold);
		barEstCuxFech.setPreferredSize(new Dimension(300, 20));
//        bar.setValue(50);
		panelProgressBar.add(barEstCuxFech);
		paneladm.add(panelProgressBar);
		
		panelRpta = new JPanel();
		panelRpta.setLayout(new FlowLayout(FlowLayout.LEFT,12,0));
		panelRpta.setBorder(new EmptyBorder(0, 0, 8, 0));
		
		AreaResult = new JTextArea();
//        taskOutput.setMargin(new Insets(5,5,5,5));       
		AreaResult.setFont(Exo2SemiBold);
		AreaResult.setWrapStyleWord(true);
		AreaResult.setEditable(false);
		
		scrPaneEstCuxFech = new JScrollPane(AreaResult);
		scrPaneEstCuxFech.setPreferredSize(new Dimension(300, 150));
//        panelrpta.add(taskOutput);
		panelRpta.add(scrPaneEstCuxFech);
		paneladm.add(panelRpta);
				
		add(paneladm);		
		setVisible(true);
		pack();
		setResizable(false);
		
	}
	
	private static EstadoCuentaxFecha InstanciaEstadoCuentaxFecha;

	public static EstadoCuentaxFecha getInstanciaEstadoCuentaxFecha() throws Exception {
		if (InstanciaEstadoCuentaxFecha == null) {
			InstanciaEstadoCuentaxFecha = new EstadoCuentaxFecha();
		}
		return InstanciaEstadoCuentaxFecha;
	}
	
	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		System.out.println("Se Abrio Ventana del Reporte Estado de Cuenta x Fecha");
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		mgr.WindowsStatClose = true;
		InstanciaEstadoCuentaxFecha = null;
		dispose();		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
//		System.out.println("Se Cerro Ventana del Reporte Estado de Cuenta x Fecha");		
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
	
	private JDialog JDialogClientes() throws Exception {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();
		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
		ge.registerFont(StrExo2Medium);
		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);

		final JDialog dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		dialog.setTitle("Consulta de Clientes");
		dialog.setModal(false);
		dialog.setLocation(200, 200);
		dialog.setSize(500, 500);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setAlwaysOnTop(true);		

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				dialog.dispose();
			}

			public void windowClosing(WindowEvent e) {
				dialog.dispose();
			}
		});
		
		JTCliente = new JTable();

		DefaultTableModel LClientes = new DefaultTableModel();
		String titulos[] = {"COD_CLIENTE", "CLIENTE"};
		LClientes.setColumnIdentifiers(titulos);	
		
		String cod_cliente = ""; 
		String razon_social = ""; 
		
		for (ClienteVO c : mgr.MostrarCliente(cod_cliente, razon_social)) {
			String Datos[] = {String.valueOf(c.getCODIGO_CLIENTE()), String.valueOf(c.getCLIENTE())};
			LClientes.addRow(Datos);
		}

		JTCliente.setModel(LClientes);

		JTCliente.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRowIndex = JTCliente.getSelectedRow();
				System.out.println(LClientes.getValueAt(selectedRowIndex, 0).toString() + "\t" + LClientes.getValueAt(selectedRowIndex, 1).toString());

				txtcodigo_cliente.setText(LClientes.getValueAt(selectedRowIndex, 0).toString());
				txtrazon_social.setText(LClientes.getValueAt(selectedRowIndex, 1).toString());				
				dialog.dispose();				
			}
		});
		
		JTCliente.setPreferredScrollableViewportSize(new Dimension(450, 200));
		JTCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTCliente.getColumnModel().getColumn(0).setPreferredWidth(150);
		JTCliente.getColumnModel().getColumn(1).setPreferredWidth(300);

		JScrollPane scrollPane = new JScrollPane(JTCliente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel jpdialog = new JPanel();
		jpdialog.setLayout(new BoxLayout(jpdialog, BoxLayout.Y_AXIS));
		jpdialog.setBackground(Color.red);
		
		JPanel jptcliente = new JPanel();
//		jptcliente.setBackground(Color.blue);
		jptcliente.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		jptcliente.add(scrollPane);		
		jpdialog.add(jptcliente);		
		
		JPanel jpsearchcli =  new JPanel();
		jpsearchcli.setBackground(Color.green);
		jpsearchcli.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
		
		JLabel lblxcodigo_cliente = new JLabel();
		lblxcodigo_cliente.setFont(Exo2Medium);
		lblxcodigo_cliente.setText("CLIENTE");
		jpsearchcli.add(lblxcodigo_cliente);
		jpdialog.add(jpsearchcli);
		
		JTextField txtxcodigo_cliente = new JTextField();
		txtxcodigo_cliente.setPreferredSize(new Dimension(150,19));
		jpsearchcli.add(txtxcodigo_cliente);
		
		JTextField txtxrazon_social = new JTextField();
		txtxrazon_social.setPreferredSize(new Dimension(250,19));
		jpsearchcli.add(txtxrazon_social);
		
		dialog.add(jpdialog);
		dialog.pack();

		txtxcodigo_cliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String xcod_cliente = txtxcodigo_cliente.getText();
					String xrazon_social = txtxrazon_social.getText();

					LClientes.setRowCount(0);
					for (ClienteVO c : mgr.MostrarCliente(xcod_cliente,xrazon_social)) {
						String Datos[] = {String.valueOf(c.getCODIGO_CLIENTE()), String.valueOf(c.getCLIENTE())};
						LClientes.addRow(Datos);
					}
					
					JTCliente.setModel(LClientes);
				}
			}			
		});
		
		txtxrazon_social.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String xcod_cliente = txtxcodigo_cliente.getText();
					String xrazon_social = txtxrazon_social.getText();

					LClientes.setRowCount(0);
					for (ClienteVO c : mgr.MostrarCliente(xcod_cliente, xrazon_social)) {
						String Datos[] = {String.valueOf(c.getCODIGO_CLIENTE()), String.valueOf(c.getCLIENTE())};
						LClientes.addRow(Datos);
					}
					
					JTCliente.setModel(LClientes);
				}
			}			
		});

		return dialog;
	}
		
}



class ItemRenderer extends BasicComboBoxRenderer {

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (value != null) {
			Item item = (Item) value;
			setText(item.getDescription().toUpperCase());
		}
		if (index == -1) {
			Item item = (Item) value;
			setText("" + item.getId());
		}
		return this;
	}
}

class Item {

	private String id;
	private String description;

	public Item(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}
}

// Clases para la Barra de Progreso

class ProgressEstadoCuentaFecha implements PropertyChangeListener {

	private JProgressBar barEstCuxFech;

	ProgressEstadoCuentaFecha() {

	}

	ProgressEstadoCuentaFecha(JProgressBar b) {
		this.barEstCuxFech = b;
		barEstCuxFech.setValue(0);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			barEstCuxFech.setValue((int) evt.getNewValue());
		}
	}
}

class WorkerRptEstadoCuentaFecha extends SwingWorker<Void, Void> {

	private JTextArea AreaResult;
	private JTextField txtcodigo_cliente;
	private JTextField txtrazon_social;
	private JCheckBox chkAllClientes;
	private JCheckBox chkCAISAC;
	private JCheckBox chkANDEX;
	private JCheckBox chkSEMILLAS;
	private JCheckBox chkSUNNY;
	private JCheckBox chkAbonos;
	private JComboBox cboAgenCAISAC;
	private JComboBox cboAgenANDEX;
	private JComboBox cboAgenSEMILLAS;
	private JComboBox cboAgenSUNNY;
	private  JDateChooser Emisionini;
	private  JDateChooser Emisionfin;
	private JRadioButton rbpendcancel;
	private JRadioButton rbpendientes;
	private JButton btnDescargar;
	private JPanel paneladm;
	
	private String CAISAC;
	private String ANDEX;
	private String SEMILLAS;
	private String SUNNY;	
	
	private String strage0002;
	private String strage0003;
	private String strage0004;
	private String strage0016;
	private String strPendCancel;
	private String strAbo;
	
	private String TMP;
	

	WorkerRptEstadoCuentaFecha() {

	}

	WorkerRptEstadoCuentaFecha(JTextArea t, JTextField tx, JTextField txraz, JCheckBox chkallcli, JCheckBox chk0002, JCheckBox chk0003, JCheckBox chk0004, JCheckBox chk0016, JCheckBox chkabo, JComboBox cboag0002, JComboBox cboag0003, JComboBox cboag0004, JComboBox cboag0016, JDateChooser jdcemiini, JDateChooser jdcemifin, JRadioButton rbcancel, JRadioButton rbpend,JButton expo, JPanel padm, String xCAISAC,String xANDEX,String xSEMILLAS, String xSUNNY,String strageCAISAC, String strageANDEX, String strageSEMILLAS, String strageSUNNY, String xPendCancel, String xstrAbo) {
		AreaResult = t;
		txtcodigo_cliente = tx;
		txtrazon_social = txraz;
		
		chkAllClientes = chkallcli;
		chkCAISAC = chk0002;
		chkANDEX = chk0003;
		chkSEMILLAS = chk0004;
		chkSUNNY = chk0016;
		chkAbonos = chkabo;
		cboAgenCAISAC = cboag0002;
		cboAgenANDEX = cboag0003;
		cboAgenSEMILLAS  = cboag0004;
		cboAgenSUNNY = cboag0016;
		Emisionini = jdcemiini;
		Emisionfin = jdcemifin;
		rbpendcancel = rbcancel;
		rbpendientes = rbpend;
		
		btnDescargar = expo;
		paneladm = padm;
		
		CAISAC = xCAISAC;
		ANDEX = xANDEX;
		SEMILLAS = xSEMILLAS;
		SUNNY = xSUNNY;
		
		strage0002 = strageCAISAC;
		strage0003 = strageANDEX;
		strage0004 = strageSEMILLAS;
		strage0016 = strageSUNNY;
		
		strPendCancel = xPendCancel;
		
		strAbo = xstrAbo;
		
		TMP = "TMP_ESTCUENTFECH_"+java.util.UUID.randomUUID().toString().replaceAll("-", "");
		
	}

	protected Void doInBackground() throws Exception {

		setProgress(1);
		AreaResult.append(1 + " % " + "\t" + " INICIANDO " + "\n");

		Thread.sleep(100);

		setProgress(50);
		AreaResult.append(50 + " % " + "\t" + " PROCESADO " + "\n");

		Thread.sleep(100);

		String cod_cliente			= txtcodigo_cliente.getText();
		
		boolean AllClientes		= chkAllClientes.isSelected();


		String empCAISAC = CAISAC;
		String empANDEX = ANDEX;
		String empSEMILLAS = SEMILLAS;
		String empSUNNY = SUNNY;
		
		String strxageCAISAC = strage0002;
		String strxageANDEX = strage0003;
		String strxageSEMILLAS = strage0004;
		String strxageSUNNY = strage0016;
		
		
		Date dateEmiini = Emisionini.getDate();		
		String XstriniEmi = DateFormat.getDateInstance().format(dateEmiini);
		
		SimpleDateFormat IniEmi = new SimpleDateFormat("dd/MM/yyyy");
		Date dIniEmi = IniEmi.parse(XstriniEmi);
		IniEmi.applyPattern("yyyyMMdd");

		String newIniEmi;
		newIniEmi = IniEmi.format(dIniEmi);


		
		Date dateEmifin = Emisionfin.getDate();		
		String XstrfinEmi = DateFormat.getDateInstance().format(dateEmifin);
		
		SimpleDateFormat FinEmi = new SimpleDateFormat("dd/MM/yyyy");
		Date dFinEmi = FinEmi.parse(XstrfinEmi);
		FinEmi.applyPattern("yyyyMMdd");

		String newFinEmi;
		newFinEmi = IniEmi.format(dFinEmi);
		
	
		 Excel_EstadoCuentaFecha exc = new Excel_EstadoCuentaFecha(cod_cliente , AllClientes, empCAISAC , empANDEX ,empSEMILLAS , empSUNNY ,strxageCAISAC , strxageANDEX , strxageSEMILLAS , strxageSUNNY , newIniEmi , newFinEmi, strPendCancel, strAbo, TMP );
		
		setProgress(100);
		AreaResult.append(100 + " % " + "\t" + " PROCESADO " + "\n");

		return null;
	}

	@Override
	protected void done()  {
		try {
			AreaResult.append("DESCARGA FINALIZADA" + "\n");
			
			paneladm.setCursor(null);
			btnDescargar.setEnabled(true);
			
			chkAllClientes.setEnabled(true);
			
			if(chkAllClientes.isSelected()){
				txtrazon_social.setEnabled(false);
				txtcodigo_cliente.setEnabled(false);
			} else {
				txtrazon_social.setEnabled(true);
				txtcodigo_cliente.setEnabled(true);
			}
			
			
			chkCAISAC.setEnabled(true);
			chkANDEX.setEnabled(true);
			chkSEMILLAS.setEnabled(true);
			chkSUNNY.setEnabled(true);
			chkAbonos.setEnabled(true);
			
			if ( chkCAISAC.isSelected() ) {
				cboAgenCAISAC.setEnabled(true);
			} else {
				cboAgenCAISAC.setEnabled(false);
			}

			if ( chkANDEX.isSelected() ) {
				cboAgenANDEX.setEnabled(true);
			} else {
				cboAgenANDEX.setEnabled(false);
			}

			if ( chkSEMILLAS.isSelected() ) {
				cboAgenSEMILLAS.setEnabled(true);
			} else {
				cboAgenSEMILLAS.setEnabled(false);
			}

			if ( chkSUNNY.isSelected() ) {
				cboAgenSUNNY.setEnabled(true);
			} else {
				cboAgenSUNNY.setEnabled(false);
			}


			Emisionini.setEnabled(true);
			Emisionfin.setEnabled(true);
			rbpendcancel.setEnabled(true);
			rbpendientes.setEnabled(true);


			Cnx cnn = new Cnx();
			PreparedStatement psdr = null;;
			String droptable = " DROP TABLE "+TMP;
			psdr = cnn.getConnection().prepareStatement(droptable);
			psdr.executeUpdate();
			cnn.desconectar();
			psdr.close();
			
		} catch (SQLException ex) {
			Logger.getLogger(WorkerRptEstadoCuentaFecha.class.getName()).log(Level.SEVERE, null, ex);
		}
			
			

	}

}




