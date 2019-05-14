package View;

import controller.Manager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import model.vo.ArticuloVO;
import model.vo.TipMovVO;
import model.vo.AlmacenVO;
import model.vo.ProveedorVO;

public class RegistrarEntrada extends JInternalFrame implements InternalFrameListener{
	
	private Manager mgr = new Manager();
        
	private JTable JTMovEnt;
	private JTable JTListProvee;
	
	private JPanel paneladm, panelop,panelop1,panelop2, panelop3, panelop4,panelop5,panelop6,panelop7,panelop8,panelop9,panelop10,panelop11,panelop12,panelop13,panelHori,panelVeri_one,panelop14,panelVeri_two,panelVeri_three,panelVeri_four,panelVeri_five,panelop15,panelGrabar;
	
	private JLabel lbl_entrada, lbl_td_descrip, lbl_fech_doc, lbl_almacen,lbl_almacen_descrip,lbl_nro_doc,lbl_cod_mov,lbl_mov_descrip,lbl_prov,lbl_prov_descrip,lbl_ref1,lbl_ref2,lbl_orden_compra,lbl_ref1_descrip,lbl_ref2_descrip,lbl_glosa1,lbl_glosa2,lbl_glosa3,lbl_producto,lbl_prod_descrip,lbl_lote,lbl_cant,lbl_base,lbl_calc,lbl_dispo,lbl_final;
	private JTextField txt_ent, txt_fech_doc, txt_almacen, txt_nro_doc, txt_cod_mov,txt_codprov,txt_prov,txt_td_ref1,txt_td_ref2,txt_cod_empresa,txt_nro_ref1,txt_nro_ref2,txt_nro_orden_compra,txt_glosa1,txt_glosa2,txt_glosa3, txt_codprod,txt_prod_descrip,txt_um,txt_cant,txt_base,txt_calc,txt_dispo,txt_final;
	private JButton btn_Procesar,btnGrabar;
	
	private JTable JTReceta,JTProd;
	
	private JComboBox  cboAlmacen;
	
	public RegistrarEntrada() throws Exception {
		mgr = new Manager();
		
		setClosable(true); // Boton Cerrar
		setIconifiable(true); // Boton Minimizar
		setMaximizable(false);
		setResizable(false);
		setTitle("Registro de Entrada");
		
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
		
		String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();		
		Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
		ge.registerFont(StrExo2Light);
		Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);
				
		paneladm = new JPanel();
		paneladm.setLayout(new BoxLayout(paneladm, BoxLayout.Y_AXIS));	
		paneladm.setBackground( Color.decode("#DDEBF7") );
	
		panelop1 = new JPanel();
		panelop1.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop1.setBackground( Color.decode("#DDEBF7") );	
		
		lbl_almacen = new JLabel();
		lbl_almacen.setFont(Exo2Medium);
		lbl_almacen.setPreferredSize(new Dimension(100,19));
		lbl_almacen.setText("ALMACEN");
		panelop1.add(lbl_almacen);
		
		cboAlmacen = new JComboBox();
		cboAlmacen.setPreferredSize(new Dimension(230, 19));
		ArrayList<AlmacenVO> arrAlmacen = mgr.ConsultarAlmacen();	
		for(AlmacenVO Alma: arrAlmacen){					
			cboAlmacen.addItem(new Item(Alma.getCOD(),Alma.getALMACEN()));			
		}
		cboAlmacen.setSelectedIndex(0);
		cboAlmacen.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDescription());
			//strage0002 = item.getId();
		});		
		cboAlmacen.setEnabled(true);
		panelop1.add(cboAlmacen);
		
		panelop1.add(Box.createHorizontalStrut(98)); // ESPACIO QUE SEPARA DOS COMPONENTES  EN UNA LINEA HORIZONTAL
		
		lbl_nro_doc = new JLabel();
		lbl_nro_doc.setFont(Exo2Medium);
		lbl_nro_doc.setPreferredSize(new Dimension(100,19));
		lbl_nro_doc.setText("NRO. DOCUM.");
		panelop1.add(lbl_nro_doc);
				
		txt_nro_doc = new JTextField();
		txt_nro_doc.setPreferredSize(new Dimension(100,19));
		txt_nro_doc.setFont(Exo2Light);
		txt_nro_doc.setText("");
		txt_nro_doc.setName("txt_nro_doc)");
		panelop1.add(txt_nro_doc);
		
		paneladm.add(panelop1);
		
		panelop2 = new JPanel();
		panelop2.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop2.setBackground( Color.decode("#DDEBF7") );

		lbl_cod_mov = new JLabel();
		lbl_cod_mov.setFont(Exo2Medium);
		lbl_cod_mov.setPreferredSize(new Dimension(100,19));
		lbl_cod_mov.setText("COD.MOV.");
		panelop2.add(lbl_cod_mov);

		txt_cod_mov = new JTextField();
		txt_cod_mov.setPreferredSize(new Dimension(35,19));
		txt_cod_mov.setFont(Exo2Light);
		txt_cod_mov.setText("");
		txt_cod_mov.setName("txt_ent");
		panelop2.add(txt_cod_mov);
		
//		txt_cod_mov.addMouseListener(new MouseAdapter() {
//		@Override
//			public void mouseClicked(MouseEvent e) {		
//					Border border = BorderFactory.createLineBorder(Color.decode("#3A7AE2"));		
//					txt_cod_mov.setBorder(border);		
//			}
//		});
//
//		txt_cod_mov.setFocusTraversalKeysEnabled(false);
//
//		javax.swing.Action myAction = new javax.swing.AbstractAction() {
//				public void actionPerformed(ActionEvent e) {
//						try {
//								JDialog asyncDialog = JDialogTipMov();				
//						} catch (Exception ex) {
//								Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
//						}
//
//				}
//		};
//
//		txt_cod_mov.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, Event.SHIFT_MASK), "myCode");
//		txt_cod_mov.getActionMap().put("myCode", myAction);
		

		lbl_mov_descrip = new JLabel();
		lbl_mov_descrip.setFont(Exo2Medium);
		lbl_mov_descrip.setText("---");
		lbl_mov_descrip.setPreferredSize(new Dimension(293,19));
		lbl_mov_descrip.setForeground(Color.decode("#888888"));
		panelop2.add(lbl_mov_descrip);	
		
		lbl_fech_doc = new JLabel();
		lbl_fech_doc.setFont(Exo2Medium);
		lbl_fech_doc.setPreferredSize(new Dimension(100,19));
		lbl_fech_doc.setText("FECHA DOCUM.");		
		panelop2.add(lbl_fech_doc);
		
		txt_fech_doc = new JTextField();
		txt_fech_doc.setPreferredSize(new Dimension(100,19));
		txt_fech_doc.setFont(Exo2Light);
		txt_fech_doc.setText("");
		txt_fech_doc.setName("txt_fech_doc");
		panelop2.add(txt_fech_doc);
		
		paneladm.add(panelop2);
		
		JSeparator sep = new JSeparator();
		sep.setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 50));
		paneladm.add(sep);
		
		panelop4 = new JPanel();
		panelop4.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop4.setBackground( Color.decode("#DDEBF7") );
		
		lbl_prov = new JLabel();
		lbl_prov.setFont(Exo2Medium);
		lbl_prov.setPreferredSize(new Dimension(100,19));
		lbl_prov.setText("PROVEEDOR");
		panelop4.add(lbl_prov);
				
		txt_codprov = new JTextField();
		txt_codprov.setPreferredSize(new Dimension(100,19));
		txt_codprov.setFont(Exo2Light);
		txt_codprov.setText("");
		txt_codprov.setName("txt_codprov)");
		panelop4.add(txt_codprov);		
		
		lbl_prov_descrip = new JLabel();
		lbl_prov_descrip.setFont(Exo2Medium);
		lbl_prov_descrip.setPreferredSize(new Dimension(100,19));
		lbl_prov_descrip.setText("---");
		lbl_prov_descrip.setForeground(Color.decode("#888888"));
		panelop4.add(lbl_prov_descrip);
		
//		txt_codprov.addMouseListener(new MouseAdapter() {
//		@Override
//			public void mouseClicked(MouseEvent e) {		
//					Border border = BorderFactory.createLineBorder(Color.decode("#3A7AE2"));		
//					txt_codprov.setBorder(border);		
//			}
//		});
//		
//		txt_codprov.setFocusTraversalKeysEnabled(false);
//		
//		javax.swing.Action MyPanelProvee = new javax.swing.AbstractAction() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//						JDialog asyncDialog = JDialogProvee();				
//				} catch (Exception ex) {
//						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
//				}
//			}
//		};
//		
//		txt_codprov.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, Event.SHIFT_MASK), "myCode");
//		txt_codprov.getActionMap().put("myCode", MyPanelProvee);
		
		paneladm.add(panelop4);
		
		panelop5 = new JPanel();
		panelop5.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop5.setBackground( Color.decode("#DDEBF7") );
	
		lbl_ref1 = new JLabel();
		lbl_ref1.setFont(Exo2Medium);
		lbl_ref1.setText("TIPO REF 1");
		lbl_ref1.setPreferredSize(new Dimension(100,19));
		lbl_ref1.setForeground(Color.decode("#000000"));
		panelop5.add(lbl_ref1);	

		txt_td_ref1 = new JTextField();
		txt_td_ref1.setPreferredSize(new Dimension(35,19));
		txt_td_ref1.setFont(Exo2Light);
		txt_td_ref1.setText("");
		txt_td_ref1.setName("txt_td_ref1)");
		panelop5.add(txt_td_ref1);
		
		
		txt_nro_ref1 = new JTextField();
		txt_nro_ref1.setPreferredSize(new Dimension(100,19));
		txt_nro_ref1.setFont(Exo2Light);
		txt_nro_ref1.setText("");
		txt_nro_ref1.setName("txt_nro_ref1)");
		panelop5.add(txt_nro_ref1);

		lbl_ref1_descrip = new JLabel();
		lbl_ref1_descrip.setFont(Exo2Medium);
		lbl_ref1_descrip.setText("---");
		lbl_ref1_descrip.setPreferredSize(new Dimension(100,19));
		lbl_ref1_descrip.setForeground(Color.decode("#888888"));
		panelop5.add(lbl_ref1_descrip);	

		paneladm.add(panelop5);
		
		
		panelop6 = new JPanel();
		panelop6.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop6.setBackground( Color.decode("#DDEBF7") );

		lbl_ref2 = new JLabel();
		lbl_ref2.setFont(Exo2Medium);
		lbl_ref2.setText("TIPO REF 2");
		lbl_ref2.setPreferredSize(new Dimension(100,19));
		lbl_ref2.setForeground(Color.decode("#000000"));
		panelop6.add(lbl_ref2);	

		txt_td_ref2 = new JTextField();
		txt_td_ref2.setPreferredSize(new Dimension(35,19));
		txt_td_ref2.setFont(Exo2Light);
		txt_td_ref2.setText("");
		txt_td_ref2.setName("txt_td_ref2)");
		panelop6.add(txt_td_ref2);


		txt_nro_ref2 = new JTextField();
		txt_nro_ref2.setPreferredSize(new Dimension(100,19));
		txt_nro_ref2.setFont(Exo2Light);
		txt_nro_ref2.setText("");
		txt_nro_ref2.setName("txt_nro_ref2)");
		panelop6.add(txt_nro_ref2);

		lbl_ref2_descrip = new JLabel();
		lbl_ref2_descrip.setFont(Exo2Medium);
		lbl_ref2_descrip.setText("---");
		lbl_ref2_descrip.setPreferredSize(new Dimension(100,19));
		lbl_ref2_descrip.setForeground(Color.decode("#888888"));
		panelop6.add(lbl_ref2_descrip);	

		paneladm.add(panelop6);
		
		panelop7 = new JPanel();
		panelop7.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop7.setBackground( Color.decode("#DDEBF7") );

		lbl_orden_compra = new JLabel();
		lbl_orden_compra.setFont(Exo2Medium);
		lbl_orden_compra.setText("ORDEN COMPRA");
		lbl_orden_compra.setPreferredSize(new Dimension(100,19));
		lbl_orden_compra.setForeground(Color.decode("#000000"));
		panelop7.add(lbl_orden_compra);	

		txt_cod_empresa = new JTextField();
		txt_cod_empresa.setPreferredSize(new Dimension(35,19));
		txt_cod_empresa.setFont(Exo2Light);
		txt_cod_empresa.setText("");
		txt_cod_empresa.setName("txt_cod_empresa)");
		panelop7.add(txt_cod_empresa);

		txt_nro_orden_compra = new JTextField();
		txt_nro_orden_compra.setPreferredSize(new Dimension(100,19));
		txt_nro_orden_compra.setFont(Exo2Light);
		txt_nro_orden_compra.setText("");
		txt_nro_orden_compra.setName("txt_nro_orden_compra)");
		panelop7.add(txt_nro_orden_compra);

		paneladm.add(panelop7);
		
		panelop8 = new JPanel();
		panelop8.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop8.setBackground( Color.decode("#DDEBF7") );

		lbl_glosa1 = new JLabel();
		lbl_glosa1.setFont(Exo2Medium);
		lbl_glosa1.setText("GLOSA 1");
		lbl_glosa1.setPreferredSize(new Dimension(100,19));
		lbl_glosa1.setForeground(Color.decode("#000000"));
		panelop8.add(lbl_glosa1);	

		txt_glosa1 = new JTextField();
		txt_glosa1.setPreferredSize(new Dimension(564,19));
		txt_glosa1.setFont(Exo2Light);
		txt_glosa1.setText("");
		txt_glosa1.setName("txt_glosa1)");
		panelop8.add(txt_glosa1);

		paneladm.add(panelop8);
		
		panelop9 = new JPanel();
		panelop9.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop9.setBackground( Color.decode("#DDEBF7") );

		lbl_glosa2 = new JLabel();
		lbl_glosa2.setFont(Exo2Medium);
		lbl_glosa2.setText("GLOSA 2");
		lbl_glosa2.setPreferredSize(new Dimension(100,19));
		lbl_glosa2.setForeground(Color.decode("#000000"));
		panelop9.add(lbl_glosa2);	

		txt_glosa2 = new JTextField();
		txt_glosa2.setPreferredSize(new Dimension(564,19));
		txt_glosa2.setFont(Exo2Light);
		txt_glosa2.setText("");
		txt_glosa2.setName("txt_glosa2)");
		panelop9.add(txt_glosa2);

		paneladm.add(panelop9);
		
		panelop10 = new JPanel();
		panelop10.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop10.setBackground( Color.decode("#DDEBF7") );

		lbl_glosa3 = new JLabel();
		lbl_glosa3.setFont(Exo2Medium);
		lbl_glosa3.setText("GLOSA 3");
		lbl_glosa3.setPreferredSize(new Dimension(100,19));
		lbl_glosa3.setForeground(Color.decode("#000000"));
		panelop10.add(lbl_glosa3);	

		txt_glosa3 = new JTextField();
		txt_glosa3.setPreferredSize(new Dimension(564,19));
		txt_glosa3.setFont(Exo2Light);
		txt_glosa3.setText("");
		txt_glosa3.setName("txt_glosa3)");
		panelop10.add(txt_glosa3);

		paneladm.add(panelop10);
		
		JSeparator sepa = new JSeparator();
		sepa.setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 50));
		paneladm.add(sepa);

		JTReceta = new JTable();

		DefaultTableModel LReceta = new DefaultTableModel();
		String titulos[] = {	
						"#",
						"COD",
						"INSUMO",
						"UND",
						"SERIE",
						"FECHA LOTE",
						"CANT",
						"CANT. BASE",
						"CANT. CALC."
					};
		LReceta.setColumnIdentifiers(titulos);

		JTReceta.setModel(LReceta);

		JTReceta.setPreferredScrollableViewportSize(new Dimension(672, 200));
		JTReceta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTReceta.getColumnModel().getColumn(0).setPreferredWidth(30);
		JTReceta.getColumnModel().getColumn(1).setPreferredWidth(75);
		JTReceta.getColumnModel().getColumn(2).setPreferredWidth(200);
		JTReceta.getColumnModel().getColumn(3).setPreferredWidth(50);
		JTReceta.getColumnModel().getColumn(4).setPreferredWidth(100);
		JTReceta.getColumnModel().getColumn(5).setPreferredWidth(100);
		JTReceta.getColumnModel().getColumn(6).setPreferredWidth(100);
		JTReceta.getColumnModel().getColumn(7).setPreferredWidth(150);

		JScrollPane scrollPane = new JScrollPane(JTReceta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JPanel jptreceta = new JPanel();
		jptreceta.setPreferredSize(new Dimension(600,250));
		jptreceta.setBackground( Color.decode("#DDEBF7") );
//		jptreceta.setBackground(Color.blue);
		jptreceta.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 5));
		jptreceta.add(scrollPane);

		paneladm.add(jptreceta);
		
		panelGrabar = new JPanel();
		panelGrabar.setLayout(new FlowLayout(FlowLayout.CENTER,12,0));
		panelGrabar.setBorder(new EmptyBorder(8, 0, 8, 0));
		panelGrabar.setBackground( Color.decode("#DDEBF7") );
		
		String save = new File("src\\img\\fatcow_icon\\16x16_0340\\disk.png").getAbsolutePath();		
		ImageIcon iconsave = new ImageIcon(save);
		
		btnGrabar = new JButton("GRABAR",iconsave);
		btnGrabar.setFont(Exo2Medium);
		btnGrabar.setPreferredSize(new Dimension(100,25));
		btnGrabar.setFocusPainted(false);
		panelGrabar.add(btnGrabar);
		paneladm.add(panelGrabar);
		
		getContentPane().setBackground( Color.decode("#DDEBF7") );
		add(paneladm);
		setVisible(true);
		pack();
		setResizable(false);
		
	}
	
	private static RegistrarEntrada InstanciaRegistrarEntrada;
	
	public static RegistrarEntrada getInstanciaRegistrarEntrada() throws Exception{
		if(InstanciaRegistrarEntrada == null){
			InstanciaRegistrarEntrada = new RegistrarEntrada();
		}
		return InstanciaRegistrarEntrada;
	}
	
	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}
	
	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		System.out.println("Abriendo Registro de Entrada");
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		System.out.println("Cerrando");
		mgr.WindowsStatClose = true;
		InstanciaRegistrarEntrada = null;
		dispose();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		System.out.println("Se Cerro Ventana del Registro de Entrada");
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
	
//	private JDialog JDialogTipMov() throws Exception {
//        
//                        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//
//                        String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();
//                        Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
//                        ge.registerFont(StrExo2Medium);
//                        Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
//                        
//                        final JDialog dialog = new JDialog();
//                        dialog.setLayout(new FlowLayout());
//                        dialog.setTitle("Movimientos de Entrada");
//                        dialog.setModal(false);
//                        dialog.setLocation(200, 200);
//
//                        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//                        dialog.setAlwaysOnTop(true);
//                        
//                        JTMovEnt = new JTable();
//
//                        DefaultTableModel LTipMov = new DefaultTableModel();
//                        String titulos[] = {"CODIGO", "DESCRIPCION"};
//                        LTipMov.setColumnIdentifiers(titulos);	
//
//                        String codigo = ""; 
//                        String descripcion = ""; 
//                        
//                        for (TipMovVO c : mgr.MostrarTipMov(codigo, descripcion)) {
//                            String Datos[] = {String.valueOf(c.getCODIGO()), String.valueOf(c.getDESCRIPCION())};
//                            LTipMov.addRow(Datos);
//                        }
//                        
//                        JTMovEnt.setModel(LTipMov);
//                        
//                        JTMovEnt.addMouseListener(new java.awt.event.MouseAdapter() {
//                                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                                        int selectedRowIndex = JTMovEnt.getSelectedRow();
//                                        System.out.println(LTipMov.getValueAt(selectedRowIndex, 0).toString() + "\t" + LTipMov.getValueAt(selectedRowIndex, 1).toString());
//
//                                        txt_cod_mov.setText(LTipMov.getValueAt(selectedRowIndex, 0).toString());
//                                        lbl_mov_descrip.setText(LTipMov.getValueAt(selectedRowIndex, 1).toString());				
//                                        dialog.dispose();				
//                                }
//                        });
//                        
//                        JTMovEnt.setPreferredScrollableViewportSize(new Dimension(450, 200));
//                        JTMovEnt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//                        JTMovEnt.getColumnModel().getColumn(0).setPreferredWidth(150);
//                        JTMovEnt.getColumnModel().getColumn(1).setPreferredWidth(300);
//                        
//                        JScrollPane scrollPane = new JScrollPane(JTMovEnt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//                        
//                        JPanel jpdialog = new JPanel();
//                        jpdialog.setLayout(new BoxLayout(jpdialog, BoxLayout.Y_AXIS));
//                        jpdialog.setBackground(Color.red);
//
//                        JPanel jpttipmov = new JPanel();
//                        jpttipmov.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
//                        jpttipmov.add(scrollPane);		
//                        jpdialog.add(jpttipmov);		
//
//                        JPanel jpsearchcli =  new JPanel();
//                        jpsearchcli.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
//                        
//                        JLabel lblxcodigo = new JLabel();
//                        lblxcodigo.setFont(Exo2Medium);
//                        lblxcodigo.setText("TIP.MOV.");
//                        jpsearchcli.add(lblxcodigo);
//                        jpdialog.add(jpsearchcli);
//
//                        JTextField txtxcodigo = new JTextField();
//                        txtxcodigo.setPreferredSize(new Dimension(150,19));
//                        jpsearchcli.add(txtxcodigo);
//
//                        JTextField txtxdescripcion = new JTextField();
//                        txtxdescripcion.setPreferredSize(new Dimension(250,19));
//                        jpsearchcli.add(txtxdescripcion);
//
//                        dialog.add(jpdialog);
//                        dialog.pack();
//                        Runnable dialogDisplayThread = new Runnable() {
//                                public void run() {
//                                        dialog.setVisible(true);
//                                }
//                        };
//
//                        dialog.addWindowListener(new WindowAdapter() {
//                                public void windowClosed(WindowEvent e) {
//                                        dialog.dispose();
//                                }
//
//                                public void windowClosing(WindowEvent e) {
//                                        dialog.dispose();
//                                }
//                        });
//                        
//                        txtxcodigo.addKeyListener(new KeyAdapter() {
//                        @Override
//                        public void keyPressed(KeyEvent e) {
//                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                                                String xcod = txtxcodigo.getText();
//                                                String xdescrip = txtxdescripcion.getText();
//
//                                                LTipMov.setRowCount(0);
//                                                for (TipMovVO c : mgr.MostrarTipMov(xcod,xdescrip)) {
//                                                        String Datos[] = {String.valueOf(c.getCODIGO()), String.valueOf(c.getDESCRIPCION())};
//                                                        LTipMov.addRow(Datos);
//                                                }
//
//                                                JTMovEnt.setModel(LTipMov);
//                                        }
//                                }			
//                        });
//                        
//                        txtxdescripcion.addKeyListener(new KeyAdapter() {
//                            @Override
//                            public void keyPressed(KeyEvent e) {
//                                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                                            String xcod = txtxcodigo.getText();
//                                            String xdescrip = txtxdescripcion.getText();
//
//                                            LTipMov.setRowCount(0);
//                                            for (TipMovVO c : mgr.MostrarTipMov(xcod, xdescrip)) {
//                                                    String Datos[] = {String.valueOf(c.getCODIGO()), String.valueOf(c.getDESCRIPCION())};
//                                                    LTipMov.addRow(Datos);
//                                            }
//
//                                            JTMovEnt.setModel(LTipMov);
//                                    }
//                            }			
//                        });
//                        
//                        new Thread(dialogDisplayThread).start();
//
//                        while (!dialog.isVisible()) {
//                        }
//                        dialog.paint(dialog.getGraphics());
//
//                        return dialog;
//        
//                }

	private PopupMenu addSeparator(Dimension dimension) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
//	private JDialog JDialogProvee() throws Exception {
//		
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//
//		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();
//		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
//		ge.registerFont(StrExo2Medium);
//		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
//
//		final JDialog dialog = new JDialog();
//		dialog.setLayout(new FlowLayout());
//		dialog.setTitle("Lista de Proveedor");
//		dialog.setModal(false);
//		dialog.setLocation(200, 200);
//
//		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//		dialog.setAlwaysOnTop(true);
//		
//		JTListProvee = new JTable();
//
//		DefaultTableModel LListProvee = new DefaultTableModel();
//		String titulos[] = {"CODIGO", "DESCRIPCION"};
//		LListProvee.setColumnIdentifiers(titulos);	
//
//		String codigo = ""; 
//		String proveedor = ""; 
//
//		for (ProveedorVO c : mgr.MostrarProveedor(codigo, proveedor)) {
//			String Datos[] = {String.valueOf(c.getCODIGO()), String.valueOf(c.getPROVEEDOR())};
//			LListProvee.addRow(Datos);
//		}
//
//		JTListProvee.setModel(LListProvee);
//
//		JTListProvee.addMouseListener(new java.awt.event.MouseAdapter() {
//			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				int selectedRowIndex = JTListProvee.getSelectedRow();
//				System.out.println(LListProvee.getValueAt(selectedRowIndex, 0).toString() + "\t" + LListProvee.getValueAt(selectedRowIndex, 1).toString());
//
//				txt_codprov.setText(LListProvee.getValueAt(selectedRowIndex, 0).toString());
//				lbl_prov_descrip.setText(LListProvee.getValueAt(selectedRowIndex, 1).toString());
//				dialog.dispose();
//			}
//		});
//		
//		JTListProvee.setPreferredScrollableViewportSize(new Dimension(450, 200));
//		JTListProvee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		JTListProvee.getColumnModel().getColumn(0).setPreferredWidth(150);
//		JTListProvee.getColumnModel().getColumn(1).setPreferredWidth(300);
//
//		JScrollPane scrollPane = new JScrollPane(JTListProvee, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//		JPanel jpdialog = new JPanel();
//		jpdialog.setLayout(new BoxLayout(jpdialog, BoxLayout.Y_AXIS));
//		jpdialog.setBackground(Color.red);
//
//		JPanel jptlistprovee = new JPanel();
//		jptlistprovee.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
//		jptlistprovee.add(scrollPane);
//		jpdialog.add(jptlistprovee);
//
//		JPanel jplistprovee = new JPanel();
//		jplistprovee.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
//
//		JLabel lblxcodigo = new JLabel();
//		lblxcodigo.setFont(Exo2Medium);
//		lblxcodigo.setText("TIP.MOV.");
//		jplistprovee.add(lblxcodigo);
//		jpdialog.add(jplistprovee);
//
//		JTextField txtxcodigo = new JTextField();
//		txtxcodigo.setPreferredSize(new Dimension(150, 19));
//		jplistprovee.add(txtxcodigo);
//
//		JTextField txtxdescripcion = new JTextField();
//		txtxdescripcion.setPreferredSize(new Dimension(250, 19));
//		jplistprovee.add(txtxdescripcion);
//
//		dialog.add(jpdialog);
//		dialog.pack();
//		
//		Runnable dialogDisplayThread = new Runnable() {
//				public void run() {
//						dialog.setVisible(true);
//				}
//		};
//
//		dialog.addWindowListener(new WindowAdapter() {
//				public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//				}
//				public void windowClosing(WindowEvent e) {
//						dialog.dispose();
//				}
//		});
//		
//		txtxcodigo.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//					String xcod = txtxcodigo.getText();
//					String xdescrip = txtxdescripcion.getText();
//
//					LListProvee.setRowCount(0);
//					for (ProveedorVO c : mgr.MostrarProveedor(xcod, xdescrip)) {
//						String Datos[] = {String.valueOf(c.getCODIGO()), String.valueOf(c.getPROVEEDOR())};
//						LListProvee.addRow(Datos);
//					}
//
//					JTMovEnt.setModel(LListProvee);
//				}
//			}
//		});
//
//		txtxdescripcion.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//					String xcod = txtxcodigo.getText();
//					String xdescrip = txtxdescripcion.getText();
//
//					LListProvee.setRowCount(0);
//					for (ProveedorVO c : mgr.MostrarProveedor(xcod, xdescrip)) {
//						String Datos[] = {String.valueOf(c.getCODIGO()), String.valueOf(c.getPROVEEDOR())};
//						LListProvee.addRow(Datos);
//					}
//
//					JTMovEnt.setModel(LListProvee);
//				}
//			}
//		});
//		
//		new Thread(dialogDisplayThread).start();
//
//		while (!dialog.isVisible()) {
//		}
//		dialog.paint(dialog.getGraphics());
//
//		return dialog;
//	}
	
}