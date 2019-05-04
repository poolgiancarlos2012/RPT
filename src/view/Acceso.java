package View;

import clase.Password;
import controller.Manager;
import java.awt.Color;

import model.logic.Logical;
import model.vo.UsuarioVO;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;


public class Acceso extends JInternalFrame implements InternalFrameListener{
	private Manager mgr = new Manager();
	
	private JPanel paneladm,panelrutas,paneldownload,panelsave,panelmodpass,paneltexto1,paneltexto2,paneltexto3,paneltexto4,panelusers,panelpass,panelrpta,panelguardar;
	private JLabel lbldrutas,lbldescarga,lbltitlemodpass,lblexto1,lblexto2,lblexto3,lblexto4,lblusers,lblpass,lblrpta;
	private JTextField txtruta,txtusers,txtlong,txtpass;
	private JButton btnFileDirectori, btnsave,btnadignar,btnguardar;
	
	private Integer Anchopass = 13;
	
	public Acceso() throws Exception{
		Container JIConfig = this.getContentPane();
		
		setClosable(true);
		setIconifiable(true);
		setMaximizable(false);
		setResizable(false);
		setTitle("Control de Accesos");
		
		this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Srep16x16.png")));
		
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		addInternalFrameListener(this);		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();		
		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
		ge.registerFont(StrExo2Medium);
		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);
		
		String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();		
		Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
		ge.registerFont(StrExo2Light);
		Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);
		
		String strexosemibold = new File("src\\font\\exo2\\Exo2-SemiBold.otf").getAbsolutePath();
		Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File(strexosemibold));
		ge.registerFont(StrExo2SemiBold);
		Font Exo2SemiBold = new Font("Exo 2 Semi Bold", Font.PLAIN, 13);
		
		String stropenbold = new File("src\\font\\open-sans\\OpenSans-Regular.ttf").getAbsolutePath();
		Font StrOpenSan = Font.createFont(Font.TRUETYPE_FONT, new File(stropenbold));
		ge.registerFont(StrOpenSan);
		Font OpenSan = new Font("Open Sans", Font.PLAIN, 13);

		String strquicksand = new File("src\\font\\quicksand\\Quicksand-Light.otf").getAbsolutePath();
		Font Strquicksand = Font.createFont(Font.TRUETYPE_FONT, new File(strquicksand));
		ge.registerFont(Strquicksand);
		Font Quicksand = new Font("Exo 2 Semi Bold", Font.PLAIN, 13);
		
		paneladm = new JPanel();
		paneladm.setLayout(new BoxLayout(paneladm, BoxLayout.Y_AXIS));
		
		/***ACTUALIZAR CONTRASEÑA***/
				
		paneltexto1 = new JPanel();
		paneltexto1.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
//		paneltexto1.setBackground(Color.red);		
		lblexto1 = new JLabel();
		lblexto1.setFont(Exo2Light);
		lblexto1.setText("La Contraseña Segura");
		paneltexto1.add(lblexto1);
		paneladm.add(paneltexto1);

		paneltexto2 = new JPanel();
		paneltexto2.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
//		paneltexto2.setBackground(Color.red);		
		lblexto2 = new JLabel();
		lblexto2.setFont(Exo2Light);
		lblexto2.setText("> de 5 Nros");
		paneltexto2.add(lblexto2);
		paneladm.add(paneltexto2);
		
		paneltexto3 = new JPanel();
		paneltexto3.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
//		paneltexto3.setBackground(Color.red);		
		lblexto3 = new JLabel();
		lblexto3.setFont(Exo2Light);
		lblexto3.setText("> de 2 Mayusc");
		paneltexto3.add(lblexto3);
		paneladm.add(paneltexto3);
		
		paneltexto4 = new JPanel();
		paneltexto4.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
//		paneltexto4.setBackground(Color.red);		
		lblexto4 = new JLabel();
		lblexto4.setFont(Exo2Light);
		lblexto4.setText("> de 2 Mayusc");
		paneltexto4.add(lblexto4);
		paneladm.add(paneltexto4);
		
		panelusers = new JPanel();
		panelusers.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
//		panelusers.setBackground(Color.red);		
		lblusers = new JLabel();
		lblusers.setPreferredSize(new Dimension(70,19));
		lblusers.setFont(Exo2Medium);
		lblusers.setText("USUARIO");
		panelusers.add(lblusers);
		
		txtusers = new JTextField();
		txtusers.setPreferredSize(new Dimension(150,19));
		txtusers.setFont(Exo2Light);
		
		if(mgr.Cargo.equals("ADMINISTRADOR")){
			txtusers.setEnabled(true);
		}else{
			txtusers.setEnabled(false);
		}

		txtusers.setText(mgr.Alias);
		panelusers.add(txtusers);
		
		paneladm.add(panelusers);
				
		panelpass = new JPanel();
		panelpass.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));	
		lblpass = new JLabel();
		lblpass.setPreferredSize(new Dimension(70,19));
		lblpass.setFont(Exo2Medium);
		lblpass.setText("PASSWORD");
		panelpass.add(lblpass);
		
				
		txtlong = new JTextField();
		txtlong.setPreferredSize(new Dimension(20,19));
		txtlong.setFont(OpenSan);
		txtlong.setText("");
		panelpass.add(txtlong);
		
		txtlong.addKeyListener(new KeyAdapter() {			
			public void keyReleased(KeyEvent e) {			

				if( Integer.parseInt(txtlong.getText()) >Anchopass){
					try {
						String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
						Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
						ge.registerFont(StrExo2Light);
						Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

						String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

						ImageIcon IconSuccess = new ImageIcon(iconaccept);

						JLabel lblsuccess = new JLabel(".: Maximo de caracteres hasta "+Anchopass+" digitos :.");
						lblsuccess.setFont(Exo2Light);
						JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
					} catch (FontFormatException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					}
					
					txtlong.setText("");
					txtpass.setText("");
					
				} 

				
			}			
		});
		
		txtpass = new JTextField();
		txtpass.setPreferredSize(new Dimension(118,19));
		txtpass.setFont(OpenSan);
		txtpass.setText("");
		panelpass.add(txtpass);
				
		txtpass.addKeyListener(new KeyAdapter() {			
			public void keyReleased(KeyEvent e) {
				
				
				if(  txtpass.getText().length() >Anchopass){
					try {
						String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
						Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
						ge.registerFont(StrExo2Light);
						Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

						String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

						ImageIcon IconSuccess = new ImageIcon(iconaccept);

						JLabel lblsuccess = new JLabel(".: Maximo de caracteres hasta "+Anchopass+" digitos :.");
						lblsuccess.setFont(Exo2Light);
						JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
					} catch (FontFormatException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					}
					

					txtpass.setText(txtpass.getText().substring(0,Anchopass));
					txtlong.setText(Anchopass.toString());
				} else{
					
					Password.setMinuscula(0);
					Password.setMayuscula(0);
					Password.setNumeros(0);
					
					
//					System.out.println("some action");	
					
					int xlargo = txtpass.getText().length();				
					txtlong.setText(Integer.toString(xlargo));
					
					
					/*************************************/					
					
					String acupass = "";

					int largo = Integer.parseInt(txtlong.getText());

					Password longitud = new Password(largo);

					int indice = Password.getLongitud();

//					int esFuerte = 0;

					String pass = txtpass.getText();
					for (int i = 0; i <= pass.length()-1; i++) {
//						char xclave = Password.generarPassword();

						char xclave = pass.charAt(i);
						int ascii = (int) xclave;
//						System.out.println(xclave);

						Password.setNumAleatorio(ascii);
						
						acupass = acupass + Character.toString(xclave);
						Password.GetSeguridadContraseña();
//
//						int ascii = (int) xclave;
//
						System.out.println(xclave + "->" + ascii + "\t:: " + Password.getMinuscula() + " Min " + "\t::" + Password.getMayuscula() + " May " + "\t::" + Password.getNumeros() + " Num ");

					}

					System.out.println();
					String Clave = Password.esFuerte();
					System.out.println(Clave);
//
					System.out.println("=========================================================");
	
//					txtpass.setText(acupass);
					lblrpta.setText(Clave);
					
					/*************************************/
					
				}
				
				
				
			}			
		});
				

		Numvalidator(txtlong);
		
		
		String key_go = new File("src\\img\\fatcow_icon\\16x16_0500\\key_go.png").getAbsolutePath();		
		ImageIcon iconkey_go = new ImageIcon(key_go);
		
		btnadignar = new JButton("ASIGNAR",iconkey_go);
		btnadignar.setFont(Exo2Medium);
		btnadignar.setPreferredSize(new Dimension(100,25));
		btnadignar.setFocusPainted(false);
		panelpass.add(btnadignar);
		
		paneladm.add(panelpass);
		
		panelrpta = new JPanel();
		panelrpta.setLayout(new FlowLayout(FlowLayout.LEFT,12,5));
		lblrpta = new JLabel();
		lblrpta.setPreferredSize(new Dimension(300,19));
		lblrpta.setFont(Exo2Light);
		lblrpta.setForeground(Color.decode("#0AAF28"));

		lblrpta.setText("Rpta: La contraseña es Segura !!!");
		panelrpta.add(lblrpta);
		paneladm.add(panelrpta);		
				
		panelguardar = new JPanel();
		panelguardar.setLayout(new FlowLayout(FlowLayout.CENTER,12,5));		
		
		String disk = new File("src\\img\\fatcow_icon\\16x16_0340\\disk.png").getAbsolutePath();		
		ImageIcon icon = new ImageIcon(disk);
		
		btnguardar = new JButton("GUARDAR",icon);
		btnguardar.setFont(Exo2Medium);
		btnguardar.setPreferredSize(new Dimension(100,25));
		btnguardar.setFocusPainted(false);
		btnguardar.setHorizontalAlignment(SwingConstants.LEFT);
		panelguardar.add(btnguardar);
		paneladm.add(panelguardar);

		
		
		
		btnadignar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  {

				Password.setMinuscula(0);
				Password.setMayuscula(0);
				Password.setNumeros(0);
				
				if(txtlong.getText().replaceAll(" ","").length()!=0){
					String acupass ="";

					int largo = Integer.parseInt(txtlong.getText());

					Password longitud = new Password(largo);

					int indice = Password.getLongitud();

					int esFuerte = 0;

					for (int i = 1; i <= indice; i++) {
						char xclave = Password.generarPassword();

//						System.out.print(xclave);
						acupass = acupass + Character.toString(xclave);
						Password.GetSeguridadContraseña();
						
						int ascii = (int) xclave; 

						System.out.println(xclave + "->" + ascii + "\t:: " + Password.getMinuscula() + " Min " + "\t::" + Password.getMayuscula() + " May " + "\t::" + Password.getNumeros() + " Num ");

						
					}

					System.out.println();
					String Clave =Password.esFuerte();
					System.out.println(Clave);

					System.out.println("=========================================================");

//					txtpass.setText(acupass.replaceAll("\\d+", ""));				
					txtpass.setText(acupass);				
					lblrpta.setText(Clave);
				} else {
					try {
						String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
						Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
						ge.registerFont(StrExo2Light);
						Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

						String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

						ImageIcon IconSuccess = new ImageIcon(iconaccept);

						JLabel lblsuccess = new JLabel(".: Ingresar numeros de digitos para la clave :.");
						lblsuccess.setFont(Exo2Light);
						JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
					} catch (FontFormatException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					}
				}				
			}
		});
		
		btnguardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String xalias = mgr.Alias;
				String xdni = mgr.User;
				String xpass = txtpass.getText();

				System.out.println("DATOS ACCESOS: ALIAS " + xalias + " - PASS: " + xpass + " DNI:  " + xdni);

				if (txtpass.getText().length() == 0) {
					try {
						String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
						Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
						ge.registerFont(StrExo2Light);
						Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

						String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

						ImageIcon IconSuccess = new ImageIcon(iconaccept);

						JLabel lblsuccess = new JLabel(".: Ingresar tu Clave o Contraseña :.");
						lblsuccess.setFont(Exo2Light);
						JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
					} catch (FontFormatException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					if (mgr.ActualizarContrasenia(xalias, xpass)) {
						try {
							String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
							Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
							ge.registerFont(StrExo2Light);
							Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

							String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0020\\accept.png").getAbsolutePath();

							ImageIcon IconSuccess = new ImageIcon(iconaccept);

							JLabel lblsuccess = new JLabel(".: La contraseña se Guardo :.");
							lblsuccess.setFont(Exo2Light);
							JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
						} catch (FontFormatException ex) {
							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IOException ex) {
							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}
		});
		


		add(paneladm);		
		setVisible(true);
		pack();
		setResizable(false);
	
	}
	
	private static Acceso InstanciaAcceso;

	public static Acceso getInstanciaAcceso() throws Exception {
		if (InstanciaAcceso == null) {
			InstanciaAcceso = new Acceso();
		}
		return InstanciaAcceso;
	}
	
	public void Numvalidator(JTextField txtField) {
		txtField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
//					JOptionPane.showMessageDialog(null, "Only numbers are allowed!", "WARNING!!", JOptionPane.WARNING_MESSAGE);					
					
					try {
						
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						
						String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
						Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
						ge.registerFont(StrExo2Light);
						Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);

						String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();

						ImageIcon IconSuccess = new ImageIcon(iconaccept);

						JLabel lblsuccess = new JLabel(".: Colocar solo Numeros :.");
						lblsuccess.setFont(Exo2Light);
						JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
					} catch (FontFormatException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
					}

				}else{
				
//					int xnro = Integer.parseInt(txtField.getText());					

//					if( xnro > 10 ) {
//						try {
//							GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//							
//							String strexolight = new File("src\\font\\exo2\\Exo2-Light.otf").getAbsolutePath();
//							Font StrExo2Light = Font.createFont(Font.TRUETYPE_FONT, new File(strexolight));
//							ge.registerFont(StrExo2Light);
//							Font Exo2Light = new Font("Exo 2 Light", Font.PLAIN, 12);
//
//							String iconaccept = new File("src\\img\\fatcow_icon\\32x32_0400\\error.png").getAbsolutePath();
//
//							ImageIcon IconSuccess = new ImageIcon(iconaccept);
//
//							JLabel lblsuccess = new JLabel(".: La longitud maxima es 10 :.");
//							lblsuccess.setFont(Exo2Light);
//							JOptionPane.showMessageDialog(null, lblsuccess, "Alerta", JOptionPane.INFORMATION_MESSAGE, IconSuccess);
//						} catch (FontFormatException ex) {
//							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
//						} catch (IOException ex) {
//							Logger.getLogger(EstadoCuentaxFecha.class.getName()).log(Level.SEVERE, null, ex);
//						}
//					}
				
				}
			}
		});
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
		InstanciaAcceso = null;
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

}