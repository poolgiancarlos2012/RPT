package View;

import controller.Manager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import model.vo.ArticuloVO;

public class RegistrarEntrada extends JInternalFrame implements InternalFrameListener{
	
	private Manager mgr = new Manager();
	
	private JPanel paneladm, panelop,panelop1,panelop2, panelop3, panelop4,panelop5,panelop6,panelop7,panelop8,panelop9,panelop10,panelop11,panelop12,panelop13,panelHori,panelVeri_one,panelop14,panelVeri_two,panelVeri_three,panelVeri_four,panelVeri_five,panelop15,panelGrabar;
	
	private JLabel lbl_entrada, lbl_td_descrip, lbl_fech_doc, lbl_almacen,lbl_nro_doc,lbl_cod_mov,lbl_mov_descrip,lbl_prov,lbl_ref1,lbl_ref2,lbl_orden_compra,lbl_ref1_descrip,lbl_ref2_descrip,lbl_glosa1,lbl_glosa2,lbl_glosa3,lbl_producto,lbl_lote,lbl_cant,lbl_base,lbl_calc,lbl_dispo,lbl_final;
	private JTextField txt_ent, txt_fech_doc, txt_almacen, txt_nro_doc, txt_cod_mov,txt_codprov,txt_prov,txt_td_ref1,txt_td_ref2,txt_cod_empresa,txt_nro_ref1,txt_nro_ref2,txt_nro_orden_compra,txt_glosa1,txt_glosa2,txt_glosa3, txt_codprod,txt_prod_descrip,txt_um,txt_cant,txt_base,txt_calc,txt_dispo,txt_final;
	private JButton btn_Procesar,btnGrabar;
	
	private JTable JTReceta,JTProd;
	
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

		panelop = new JPanel();
//		panelop.setBorder(new EmptyBorder(8, 8, 8, 8));
		panelop.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop.setBackground( Color.decode("#DDEBF7") );

		lbl_entrada = new JLabel();
		lbl_entrada.setFont(Exo2Medium);
		lbl_entrada.setPreferredSize(new Dimension(100,19));
		lbl_entrada.setText("ENTRADA");
		panelop.add(lbl_entrada);
		
		txt_ent = new JTextField();
		txt_ent.setPreferredSize(new Dimension(35,19));
		txt_ent.setFont(Exo2Light);
		txt_ent.setText("");
		txt_ent.setName("txt_ent");
		panelop.add(txt_ent);
		
		txt_ent.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {		
				Border border = BorderFactory.createLineBorder(Color.decode("#3A7AE2"));		
				txt_ent.setBorder(border);		
			}
		});
		
		lbl_td_descrip = new JLabel();
		lbl_td_descrip.setFont(Exo2Medium);
		lbl_td_descrip.setText("---");
		lbl_td_descrip.setPreferredSize(new Dimension(100,19));
		lbl_td_descrip.setForeground(Color.decode("#888888"));
		panelop.add(lbl_td_descrip);
		
		lbl_fech_doc = new JLabel();
		lbl_fech_doc.setFont(Exo2Medium);
		lbl_fech_doc.setText("FECHA DOCUM.");		
		panelop.add(lbl_fech_doc);		
		paneladm.add(panelop);
		
		txt_fech_doc = new JTextField();
		txt_fech_doc.setPreferredSize(new Dimension(100,19));
		txt_fech_doc.setFont(Exo2Light);
		txt_fech_doc.setText("");
		txt_fech_doc.setName("txt_fech_doc");
		panelop.add(txt_fech_doc);
		
		panelop1 = new JPanel();
		panelop1.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop1.setBackground( Color.decode("#DDEBF7") );	
				
		lbl_almacen = new JLabel();
		lbl_almacen.setFont(Exo2Medium);
		lbl_almacen.setPreferredSize(new Dimension(100,19));
		lbl_almacen.setText("ALMACEN");
		panelop1.add(lbl_almacen);
		
		txt_almacen = new JTextField();
		txt_almacen.setPreferredSize(new Dimension(100,19));
		txt_almacen.setFont(Exo2Light);
		txt_almacen.setText("");
		txt_almacen.setName("txt_almacen");
		panelop1.add(txt_almacen);
		paneladm.add(panelop1);
		
		panelop2 = new JPanel();
		panelop2.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop2.setBackground( Color.decode("#DDEBF7") );
		
		lbl_nro_doc = new JLabel();
		lbl_nro_doc.setFont(Exo2Medium);
		lbl_nro_doc.setPreferredSize(new Dimension(100,19));
		lbl_nro_doc.setText("NRO. DOCUM.");
		panelop2.add(lbl_nro_doc);
				
		txt_nro_doc = new JTextField();
		txt_nro_doc.setPreferredSize(new Dimension(100,19));
		txt_nro_doc.setFont(Exo2Light);
		txt_nro_doc.setText("");
		txt_nro_doc.setName("txt_nro_doc)");
		panelop2.add(txt_nro_doc);
		paneladm.add(panelop2);
		
		panelop3 = new JPanel();
		panelop3.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop3.setBackground( Color.decode("#DDEBF7") );

		lbl_cod_mov = new JLabel();
		lbl_cod_mov.setFont(Exo2Medium);
		lbl_cod_mov.setPreferredSize(new Dimension(100,19));
		lbl_cod_mov.setText("COD.MOV.");
		panelop3.add(lbl_cod_mov);

		txt_cod_mov = new JTextField();
		txt_cod_mov.setPreferredSize(new Dimension(35,19));
		txt_cod_mov.setFont(Exo2Light);
		txt_cod_mov.setText("");
		txt_cod_mov.setName("txt_ent");
		panelop3.add(txt_cod_mov);

		lbl_mov_descrip = new JLabel();
		lbl_mov_descrip.setFont(Exo2Medium);
		lbl_mov_descrip.setText("---");
		lbl_mov_descrip.setPreferredSize(new Dimension(100,19));
		lbl_mov_descrip.setForeground(Color.decode("#888888"));
		panelop3.add(lbl_mov_descrip);		
		paneladm.add(panelop3);
		
//		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);  
//		panelop3.add(sep);
		
		panelop4 = new JPanel();
		panelop4.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop4.setBackground( Color.decode("#DDEBF7") );
		
		JSeparator sep = new JSeparator();
		sep.setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 50));
		paneladm.add(sep);
		
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
		
		txt_prov = new JTextField();
		txt_prov.setPreferredSize(new Dimension(241,19));
		txt_prov.setFont(Exo2Light);
		txt_prov.setText("");
		txt_prov.setName("txt_prov)");
		panelop4.add(txt_prov);
				
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
		txt_glosa1.setPreferredSize(new Dimension(353,19));
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
		txt_glosa2.setPreferredSize(new Dimension(353,19));
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
		txt_glosa3.setPreferredSize(new Dimension(353,19));
		txt_glosa3.setFont(Exo2Light);
		txt_glosa3.setText("");
		txt_glosa3.setName("txt_glosa3)");
		panelop10.add(txt_glosa3);

		paneladm.add(panelop10);
		
		JSeparator sepa = new JSeparator();
		sepa.setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 50));
		paneladm.add(sepa);
		
		panelop11 = new JPanel();
		panelop11.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelop11.setBackground( Color.decode("#DDEBF7") );
		
		lbl_producto = new JLabel();
		lbl_producto.setFont(Exo2Medium);
		lbl_producto.setText("PRODUCTO");
		lbl_producto.setPreferredSize(new Dimension(100,19));
		lbl_producto.setForeground(Color.decode("#000000"));
		panelop11.add(lbl_producto);	
		
		txt_codprod = new JTextField();
		txt_codprod.setPreferredSize(new Dimension(100,19));
		txt_codprod.setFont(Exo2Light);
		txt_codprod.setText("");
		txt_codprod.setName("txt_codprod");
		
		txt_codprod.setFocusTraversalKeysEnabled(false);
                                
		javax.swing.Action myAction = new javax.swing.AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				try {
					JDialog asyncDialog = JDialogArticulos();
//					asyncDialog.setLocation(200, 200);					
				} catch (Exception ex) {
					Logger.getLogger(RegistrarEntrada.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		};

		txt_codprod.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, Event.SHIFT_MASK), "myCode");
		txt_codprod.getActionMap().put("myCode", myAction);
		
		panelop11.add(txt_codprod);		
		
		txt_prod_descrip = new JTextField();
		txt_prod_descrip.setPreferredSize(new Dimension(194,19));
		txt_prod_descrip.setFont(Exo2Light);
		txt_prod_descrip.setText("");
		txt_prod_descrip.setName("txt_prod_descrip");
		panelop11.add(txt_prod_descrip);
		
		
		
		txt_um = new JTextField();
		txt_um.setPreferredSize(new Dimension(35,19));
		txt_um.setFont(Exo2Light);
		txt_um.setText("");
		txt_um.setName("txt_um)");
		panelop11.add(txt_um);		
		paneladm.add(panelop11);
		
		panelHori = new JPanel();
		panelHori.setLayout(new BoxLayout(panelHori, BoxLayout.X_AXIS));		
				
		panelVeri_one = new JPanel();
		panelVeri_one.setLayout(new BoxLayout(panelVeri_one, BoxLayout.Y_AXIS));
		panelVeri_one.setBackground( Color.decode("#DDEBF7") );
		panelVeri_one.setBorder(new EmptyBorder(12, 12, 8, 4));
//		panelVeri_one.setPreferredSize(new Dimension(50, 100));
		
		lbl_cant = new JLabel();
		lbl_cant.setFont(Exo2Medium);
		lbl_cant.setText("CANT.");
//		lbl_cant.setPreferredSize(new Dimension(50,19));
		lbl_cant.setForeground(Color.decode("#000000"));
		panelVeri_one.add(lbl_cant);	
		
		txt_cant = new JTextField();
//		txt_cant.setPreferredSize(new Dimension(50,19));
		txt_cant.setFont(Exo2Light);
		txt_cant.setText("");
		txt_cant.setName("txt_cant");
		panelVeri_one.add(txt_cant);			
		panelHori.add(panelVeri_one);
		
		panelVeri_two = new JPanel();
		panelVeri_two.setLayout(new BoxLayout(panelVeri_two, BoxLayout.Y_AXIS));
		panelVeri_two.setBackground( Color.decode("#DDEBF7") );
		panelVeri_two.setBorder(new EmptyBorder(12, 0, 8, 4));
		
		lbl_base = new JLabel();
		lbl_base.setFont(Exo2Medium);
		lbl_base.setText("CANT. BASE");
//		lbl_base.setPreferredSize(new Dimension(100,19));
		lbl_base.setForeground(Color.decode("#000000"));
		panelVeri_two.add(lbl_base);	
		
		txt_base = new JTextField();
//		txt_base.setPreferredSize(new Dimension(100,19));
		txt_base.setFont(Exo2Light);
		txt_base.setText("");
		txt_base.setName("txt_base");
		panelVeri_two.add(txt_base);			
		panelHori.add(panelVeri_two);
		
		
				
		panelVeri_three = new JPanel();
		panelVeri_three.setLayout(new BoxLayout(panelVeri_three, BoxLayout.Y_AXIS));
		panelVeri_three.setBackground( Color.decode("#DDEBF7") );
		panelVeri_three.setBorder(new EmptyBorder(12, 0, 8, 4));
		
		lbl_calc = new JLabel();
		lbl_calc.setFont(Exo2Medium);
		lbl_calc.setText("CANT. CALC");
//		lbl_calc.setPreferredSize(new Dimension(100,19));
		lbl_calc.setForeground(Color.decode("#000000"));
		panelVeri_three.add(lbl_calc);	
		
		txt_calc = new JTextField();
//		txt_calc.setPreferredSize(new Dimension(100,19));
		txt_calc.setFont(Exo2Light);
		txt_calc.setText("");
		txt_calc.setName("txt_calc");
		panelVeri_three.add(txt_calc);			
		panelHori.add(panelVeri_three);
		
		panelVeri_four = new JPanel();
		panelVeri_four.setLayout(new BoxLayout(panelVeri_four, BoxLayout.Y_AXIS));
		panelVeri_four.setBackground( Color.decode("#DDEBF7") );
		panelVeri_four.setBorder(new EmptyBorder(12, 0, 8, 4));
		
		lbl_dispo = new JLabel();
		lbl_dispo.setFont(Exo2Medium);
		lbl_dispo.setText("STOCK DISPO.");
//		lbl_dispo.setPreferredSize(new Dimension(100,19));
		lbl_dispo.setForeground(Color.decode("#000000"));
		panelVeri_four.add(lbl_dispo);	
		
		txt_dispo = new JTextField();
//		txt_dispo.setPreferredSize(new Dimension(100,19));
		txt_dispo.setFont(Exo2Light);
		txt_dispo.setText("");
		txt_dispo.setName("txt_dispo");
		panelVeri_four.add(txt_dispo);			
		panelHori.add(panelVeri_four);
		
		panelVeri_five = new JPanel();
		panelVeri_five.setLayout(new BoxLayout(panelVeri_five, BoxLayout.Y_AXIS));
		panelVeri_five.setBackground( Color.decode("#DDEBF7") );
		panelVeri_five.setBorder(new EmptyBorder(12, 0, 8, 12));
		
		lbl_final = new JLabel();
		lbl_final.setFont(Exo2Medium);
		lbl_final.setText("STOCK FINAL");
//		lbl_final.setPreferredSize(new Dimension(100,19));
		lbl_final.setForeground(Color.decode("#000000"));
		panelVeri_five.add(lbl_final);	
		
		txt_final = new JTextField();
//		txt_final.setPreferredSize(new Dimension(100,19));
		txt_final.setFont(Exo2Light);
		txt_final.setText("");
		txt_final.setName("txt_final");
		panelVeri_five.add(txt_final);			
		panelHori.add(panelVeri_five);
		
		paneladm.add(panelHori);
		
		panelop15 = new JPanel();
		panelop15.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 5));
		panelop15.setBackground( Color.decode("#DDEBF7") );
		
		String key_go = new File("src\\img\\fatcow_icon\\16x16_0240\\cog.png").getAbsolutePath();		
		ImageIcon iconkey_go = new ImageIcon(key_go);
		
		btn_Procesar = new JButton("PROCESAR",iconkey_go);
		btn_Procesar.setFont(Exo2Medium);
		btn_Procesar.setPreferredSize(new Dimension(100,25));
		btn_Procesar.setFocusPainted(false);
		
		btn_Procesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panelop15.add(btn_Procesar);
		paneladm.add(panelop15);
		
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

		JTReceta.setPreferredScrollableViewportSize(new Dimension(550, 200));
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
	
	private JDialog JDialogArticulos() throws Exception {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();
		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
		ge.registerFont(StrExo2Medium);
		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
		
		final JDialog dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		dialog.setTitle("Consultar Productos");
		dialog.setModal(false);
		dialog.setLocation(200, 200);
		
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setAlwaysOnTop(true);
		
		JTProd = new JTable();
		
		DefaultTableModel LProd = new DefaultTableModel();
		String titulos[] = {"CODPROD", "PRODUCTO","UM"};
		LProd.setColumnIdentifiers(titulos);
		
		String codprod = ""; 
		String producto = "";
		String um = "";
		
		for (ArticuloVO c : mgr.MostrarArticulo(codprod, producto)) {
			String Datos[] = {String.valueOf(c.getCODPROD()), String.valueOf(c.getPROD()),String.valueOf(c.getUM())};
			LProd.addRow(Datos);
		}
		
		JTProd.setModel(LProd);
		
		JTProd.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRowIndex = JTProd.getSelectedRow();
				System.out.println(LProd.getValueAt(selectedRowIndex, 0).toString() + "\t" + LProd.getValueAt(selectedRowIndex, 1).toString());

				txt_codprod.setText(LProd.getValueAt(selectedRowIndex, 0).toString());
				txt_prod_descrip.setText(LProd.getValueAt(selectedRowIndex, 1).toString());		
				txt_um.setText(LProd.getValueAt(selectedRowIndex, 2).toString());		
				
				dialog.dispose();				
			}
		});
		
		JTProd.setPreferredScrollableViewportSize(new Dimension(450, 200));
		JTProd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTProd.getColumnModel().getColumn(0).setPreferredWidth(150);
		JTProd.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		JScrollPane scrollPane = new JScrollPane(JTProd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel jpdialog = new JPanel();
		jpdialog.setLayout(new BoxLayout(jpdialog, BoxLayout.Y_AXIS));
		jpdialog.setBackground(Color.red);
		
		JPanel jptArti = new JPanel();
//		jptcliente.setBackground(Color.blue);
		jptArti.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		jptArti.add(scrollPane);		
		jpdialog.add(jptArti);
		
		JPanel jpsearcharti =  new JPanel();
//		jpsearchcli.setBackground(Color.green);
		jpsearcharti.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		
		JLabel lblxcodprod = new JLabel();
		lblxcodprod.setFont(Exo2Medium);
		lblxcodprod.setText("PRODUCTO");
		jpsearcharti.add(lblxcodprod);
		jpdialog.add(jpsearcharti);
		
		JTextField txtxcodprod = new JTextField();
		txtxcodprod.setPreferredSize(new Dimension(150,19));
		jpsearcharti.add(txtxcodprod);
		
		JTextField txtxprod = new JTextField();
		txtxprod.setPreferredSize(new Dimension(250,19));
		jpsearcharti.add(txtxprod);
		
		dialog.add(jpdialog);
		dialog.pack();
		Runnable dialogDisplayThread = new Runnable() {
			public void run() {
				dialog.setVisible(true);
			}
		};
		
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				dialog.dispose();
			}

			public void windowClosing(WindowEvent e) {
				dialog.dispose();
			}
		});
		
		txtxcodprod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String xcodprod = txtxcodprod.getText();
					String xprod = txtxprod.getText();

					LProd.setRowCount(0);
					for (ArticuloVO c : mgr.MostrarArticulo(xcodprod,xprod)) {
//						String Datos[] = {String.valueOf(c.getCODPROD()), String.valueOf(c.getPROD())};
						String Datos[] = {String.valueOf(c.getCODPROD()), String.valueOf(c.getPROD()),String.valueOf(c.getUM())};
						LProd.addRow(Datos);
					}
					
					JTProd.setModel(LProd);
				}
			}			
		});
		
		txtxprod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String xcodprod = txtxcodprod.getText();
					String xprod = txtxprod.getText();

					LProd.setRowCount(0);
					for (ArticuloVO c : mgr.MostrarArticulo(xcodprod, xprod)) {
//						String Datos[] = {String.valueOf(c.getCODPROD()), String.valueOf(c.getPROD())};
						String Datos[] = {String.valueOf(c.getCODPROD()), String.valueOf(c.getPROD()),String.valueOf(c.getUM())};
						LProd.addRow(Datos);
					}
					
					JTProd.setModel(LProd);
				}
			}			
		});
		
		new Thread(dialogDisplayThread).start();

		while (!dialog.isVisible()) {
		}
		dialog.paint(dialog.getGraphics());
		
		return dialog;
		
	}
	
}