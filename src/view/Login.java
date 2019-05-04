package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Manager;
import java.awt.Color;
import javax.swing.JOptionPane;

import model.vo.UsuarioVO;
import model.logic.Logical;

import java.awt.Image;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame implements ActionListener {

	private JLabel lblusu, lblpass;
	private JTextField txtusu;
	private JPasswordField txtpass;
	private JButton btningresar;
	private JFrame xframe;

	private Manager mgr;

	public JFrame getXframe() {
		return xframe;
	}

	public void setXframe(JFrame xframe) {
		this.xframe = xframe;
	}

	public Login() throws Exception {


		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

//		Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\font\\exo2\\Exo2-SemiBold.otf"));
		String strexosemibold = new File("src\\font\\exo2\\Exo2-SemiBold.otf").getAbsolutePath();
		Font StrExo2SemiBold = Font.createFont(Font.TRUETYPE_FONT, new File(strexosemibold));
		ge.registerFont(StrExo2SemiBold);
		Font Exo2SemiBold = new Font("Exo 2 Semi Bold", Font.PLAIN, 13);

		String strexomedium = new File("src\\font\\exo2\\Exo2-Medium.otf").getAbsolutePath();
		
		Font StrExo2Medium = Font.createFont(Font.TRUETYPE_FONT, new File(strexomedium));
		ge.registerFont(StrExo2Medium);
		Font Exo2Medium = new Font("Exo 2 Medium", Font.PLAIN, 12);

		xframe = new JFrame();
		
		String stricoga = new File("src\\img\\Srep16x16.png").getAbsolutePath();
		xframe.setIconImage(Toolkit.getDefaultToolkit().getImage(stricoga));

		xframe.setDefaultLookAndFeelDecorated(true);
		xframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = xframe.getContentPane();
		contentPane.setLayout(null);
		
		String strimgga = new File("src\\img\\logo_grupo_andina.png").getAbsolutePath();
//		JLabel img = new JLabel(new ImageIcon("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\img\\logo_grupo_andina.png"));
		JLabel img = new JLabel(new ImageIcon(strimgga));
		img.setBounds(30, 40, 184, 46); // x, y, width, height
		contentPane.add(img);

		lblusu = new JLabel();
		lblusu.setSize(100, 30);
		lblusu.setLocation(20, 120);
		lblusu.setText("USUARIO");
		lblusu.setFont(Exo2SemiBold);
		contentPane.add(lblusu);

		txtusu = new JTextField();
		txtusu.setSize(110, 20);
		txtusu.setLocation(115, 124);
		txtusu.setText("");
		txtusu.setFont(Exo2Medium);
		
		txtusu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtpass.requestFocus();
				}
			}
			
		});
		
		contentPane.add(txtusu);

		lblpass = new JLabel();
		lblpass.setSize(100, 30);
		lblpass.setLocation(20, 150);
		lblpass.setText("PASSWORD");
		lblpass.setFont(Exo2SemiBold);
		contentPane.add(lblpass);

		txtpass = new JPasswordField();
		txtpass.setSize(110, 20);
		txtpass.setLocation(115, 154);
		txtpass.setText("");
		
		txtpass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String usuario = txtusu.getText();
					String clave = new String(txtpass.getPassword());
					UsuarioVO BuscarUser = mgr.ValidarAcceso(usuario, clave);
					if (BuscarUser != null) {
						String Cargo = BuscarUser.getCargo();
						mgr.ShowMenu(Cargo);
						xframe.dispose();
					} else if (Logical.consultaUsuario == true) {
						JOptionPane.showMessageDialog(null, "Usuario no Existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			
		});
		
		contentPane.add(txtpass);
		
		String door_in = new File("src\\img\\fatcow_icon\\16x16_0360\\door_in.png").getAbsolutePath();		
		ImageIcon icondoor_in = new ImageIcon(door_in);
		
		btningresar = new JButton("INGRESAR",icondoor_in);
		btningresar.setSize(100, 25);
		btningresar.setLocation(70, 197);	
		btningresar.setFont(Exo2Medium);
		btningresar.setFocusPainted(false);
		btningresar.addActionListener(this);
		contentPane.add(btningresar);	
		
		xframe.setSize(250, 270);
		xframe.setLocation(60, 60);
		xframe.setResizable(false);
		xframe.setLocationRelativeTo(null);
		xframe.getContentPane().setBackground(Color.decode("#DDEBF7"));

	}

	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btningresar) {
			String usuario = txtusu.getText();
			String clave = new String(txtpass.getPassword());
			UsuarioVO BuscarUser = mgr.ValidarAcceso(usuario, clave);
			
//			System.out.println("DATOS LOGIN: USU "+BuscarUser.getUsuario()+" - ALIAS: "+BuscarUser.getAlias());
			
			if ( BuscarUser != null ) {
				String Cargo = BuscarUser.getCargo();
				mgr.ShowMenu(Cargo);				

				xframe.dispose();
			} else if (Logical.consultaUsuario == true) {
				JOptionPane.showMessageDialog(null, "Usuario no Existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

//    public static void main(String args[]){
//        Login lg = new Login();        
//        JFrame frame = new JFrame();
//        Container contentPane = frame.getContentPane();
//        contentPane.setLayout(null);        
//        ImageIcon icon = new ImageIcon("C:\\Users\\poolpg\\Documents\\NetBeansProjects\\Practicando\\src\\Practicando\\descarga.jpg");
//        JLabel label = new JLabel(icon);
//        frame.add(label);
//        JLabel img = new JLabel(new ImageIcon("C:\\Users\\poolpg\\Documents\\NetBeansProjects\\RPT\\src\\img\\login.png"));
//        img.setBounds(200, 300, 100, 100); // x, y, width, height
//        contentPane.add(img);      
//        frame.setSize(500,500);
//        frame.setLocation(60,60);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
//    }
}
