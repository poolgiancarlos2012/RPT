package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.*;

import javax.swing.*;

import controller.Manager;
import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;



public class Menu extends JFrame {

	private Manager mgr = new Manager();

	private JMenuBar barraMenu;
	
	private JLabel StatusWindows;

	private JMenu MenuReportecob, MenuEstadoCuentacob, MenuGerencialcob, MenuHistoricocob, MenuBaseCliente, MenuConfig;
	private JMenuItem ItemEstadoCuentaCliente, ItemEstadoCuentaFecha, ItemEstadoCuentaMassiveSend, ItemEstadoCuentaSunny, ItemGerencialOficina, ItemGerencialTiendas, ItemGerencialCastigados, ItemHistoricoPagos, ItemHistoricoDoc, ItemHistoricoDetDoc, ItemDireccionCliente, ItemTipoCambio, ItemBCliente, ItemLineaCreditos, ItemAcces;

	private JDesktopPane desktop;
	private JFrame Menframe;

	public JFrame getMenframe() {
		return Menframe;
	}

	public JLabel getStatusWindows() {
		return StatusWindows;
	}

	public void setStatusWindows(JLabel StatusWindows) {
		this.StatusWindows = StatusWindows;
	}

	public void setMenframe(JFrame Menframe) {
		this.Menframe = Menframe;
	}

	public JDesktopPane getDesktop() {
		return desktop;
	}

	public void setDesktop(JDesktopPane desktop) {
		this.desktop = desktop;
	}

	public Menu() {
		init();
	}

	public void init() {

		Menframe = new JFrame();

		String stricoga = new File("src\\img\\Srep16x16.png").getAbsolutePath();
//		xframe.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ppujay\\Documents\\NetBeansProjects\\RPT\\src\\img\\icono_grupo_andina.png"));
		
		Menframe.setIconImage(Toolkit.getDefaultToolkit().getImage(stricoga));
		Menframe.setDefaultLookAndFeelDecorated(true);
		Menframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		desktop = new JDesktopPane();
		barraMenu = new JMenuBar();

		MenuReportecob = new JMenu("Reporte");
		MenuReportecob.setEnabled(false);
		barraMenu.add(this.MenuReportecob);

		MenuEstadoCuentacob = new JMenu("Estado de Cuenta");
		MenuEstadoCuentacob.setEnabled(false);
		MenuReportecob.add(this.MenuEstadoCuentacob);

		ItemEstadoCuentaCliente = new JMenuItem("E.C. Por Cliente");
		ItemEstadoCuentaCliente.setEnabled(false);
		MenuEstadoCuentacob.add(this.ItemEstadoCuentaCliente);

		ItemEstadoCuentaFecha = new JMenuItem("E.C. Por Fecha");
		ItemEstadoCuentaFecha.setEnabled(false);
		MenuEstadoCuentacob.add(this.ItemEstadoCuentaFecha);

		ItemEstadoCuentaMassiveSend = new JMenuItem("E.C. Envio Masivo");
		ItemEstadoCuentaMassiveSend.setEnabled(false);
		MenuEstadoCuentacob.add(this.ItemEstadoCuentaMassiveSend);

		ItemEstadoCuentaSunny = new JMenuItem("E.C. Sunny");
		ItemEstadoCuentaSunny.setEnabled(false);
		MenuEstadoCuentacob.add(this.ItemEstadoCuentaSunny);
			
		MenuGerencialcob = new JMenu("Gerencial");
		MenuGerencialcob.setEnabled(false);
		MenuReportecob.add(this.MenuGerencialcob);

		ItemGerencialOficina = new JMenuItem("G. Oficina");
		ItemGerencialOficina.setEnabled(false);
		MenuGerencialcob.add(this.ItemGerencialOficina);

		ItemGerencialTiendas = new JMenuItem("G. Tiendas");
		ItemGerencialTiendas.setEnabled(false);
		MenuGerencialcob.add(this.ItemGerencialTiendas);

		ItemGerencialCastigados = new JMenuItem("G. Castigados");
		ItemGerencialCastigados.setEnabled(false);
		MenuGerencialcob.add(this.ItemGerencialCastigados);

		MenuHistoricocob = new JMenu("Historicos");
		MenuHistoricocob.setEnabled(false);
		MenuReportecob.add(this.MenuHistoricocob);

		ItemHistoricoPagos = new JMenuItem("Hist. Pagos");
		ItemHistoricoPagos.setEnabled(false);
		MenuHistoricocob.add(this.ItemHistoricoPagos);

		ItemHistoricoDoc = new JMenuItem("Hist. Doc.");
		ItemHistoricoDoc.setEnabled(false);
		MenuHistoricocob.add(this.ItemHistoricoDoc);

		ItemHistoricoDetDoc = new JMenuItem("Hist. Det. Doc.");
		ItemHistoricoDetDoc.setEnabled(false);
		MenuHistoricocob.add(this.ItemHistoricoDetDoc);

		ItemDireccionCliente = new JMenuItem("Dirección Cliente");
		ItemDireccionCliente.setEnabled(false);
		MenuReportecob.add(this.ItemDireccionCliente);

		ItemTipoCambio = new JMenuItem("Tipo de Cambio");
		ItemTipoCambio.setEnabled(false);
		MenuReportecob.add(this.ItemTipoCambio);

		MenuBaseCliente = new JMenu("Base Clientes");
		MenuBaseCliente.setEnabled(false);
		barraMenu.add(this.MenuBaseCliente);

		ItemBCliente = new JMenuItem("Linea de Creditos");
		ItemBCliente.setEnabled(false);
		MenuBaseCliente.add(this.ItemBCliente);

		ItemLineaCreditos = new JMenuItem("B. Clientes");
		ItemLineaCreditos.setEnabled(false);
		MenuBaseCliente.add(this.ItemLineaCreditos);

		MenuConfig = new JMenu("Configuración");
		MenuConfig.setEnabled(false);
		
		ItemAcces = new JMenuItem("Modificar Accesos");
		ItemAcces.setEnabled(false);
		MenuConfig.add(ItemAcces);
		

		barraMenu.add(this.MenuConfig);
		

		desktop.add(barraMenu);
		Menframe.setJMenuBar(barraMenu);
		Menframe.getContentPane().add(desktop);
		Menframe.setSize(400, 400);
		Menframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Menframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Menframe.setLocationRelativeTo(null);	

		programaEventos();

	}

	public void OpcionAdmin() {

		MenuReportecob.setEnabled(true);

			MenuEstadoCuentacob.setEnabled(true);
				ItemEstadoCuentaCliente.setEnabled(true);
				ItemEstadoCuentaFecha.setEnabled(true);
				ItemEstadoCuentaMassiveSend.setEnabled(true);
				ItemEstadoCuentaSunny.setEnabled(true);

			MenuGerencialcob.setEnabled(true);
				ItemGerencialOficina.setEnabled(true);
				ItemGerencialTiendas.setEnabled(true);
				ItemGerencialCastigados.setEnabled(true);

			MenuHistoricocob.setEnabled(true);
				ItemHistoricoPagos.setEnabled(true);
				ItemHistoricoDoc.setEnabled(true);
				ItemHistoricoDetDoc.setEnabled(true);

			ItemDireccionCliente.setEnabled(true);
			ItemTipoCambio.setEnabled(true);

		MenuBaseCliente.setEnabled(true);
			ItemBCliente.setEnabled(true);
			ItemLineaCreditos.setEnabled(true);

		MenuConfig.setEnabled(true);
			ItemAcces.setEnabled(true);

	}

	public void OpcionJefeOpe() {

		MenuReportecob.setEnabled(true);

			MenuEstadoCuentacob.setEnabled(true);
				ItemEstadoCuentaCliente.setEnabled(true);
				ItemEstadoCuentaFecha.setEnabled(true);
				ItemEstadoCuentaMassiveSend.setEnabled(true);
				ItemEstadoCuentaSunny.setEnabled(true);

			MenuGerencialcob.setEnabled(true);
				ItemGerencialOficina.setEnabled(true);
				ItemGerencialTiendas.setEnabled(true);
				ItemGerencialCastigados.setEnabled(true);

			MenuHistoricocob.setEnabled(true);
				ItemHistoricoPagos.setEnabled(true);
				ItemHistoricoDoc.setEnabled(true);
				ItemHistoricoDetDoc.setEnabled(true);

			ItemDireccionCliente.setEnabled(true);
			ItemTipoCambio.setEnabled(true);

		MenuBaseCliente.setEnabled(true);
			ItemBCliente.setEnabled(true);
			ItemLineaCreditos.setEnabled(true);

		MenuConfig.setEnabled(true);
			ItemAcces.setEnabled(true);
		
	}

	public void OpcionOpe() {
		MenuReportecob.setEnabled(true);

			MenuEstadoCuentacob.setEnabled(true);
				ItemEstadoCuentaCliente.setEnabled(true);
				ItemEstadoCuentaFecha.setEnabled(true);
				ItemEstadoCuentaMassiveSend.setEnabled(true);
				ItemEstadoCuentaSunny.setEnabled(true);

			MenuGerencialcob.setEnabled(true);
				ItemGerencialOficina.setEnabled(true);
				ItemGerencialTiendas.setEnabled(true);
				ItemGerencialCastigados.setEnabled(true);

			MenuHistoricocob.setEnabled(true);
				ItemHistoricoPagos.setEnabled(true);
				ItemHistoricoDoc.setEnabled(true);
				ItemHistoricoDetDoc.setEnabled(true);

			ItemDireccionCliente.setEnabled(true);
			ItemTipoCambio.setEnabled(true);

		MenuBaseCliente.setEnabled(true);
			ItemBCliente.setEnabled(true);
			ItemLineaCreditos.setEnabled(true);

		MenuConfig.setEnabled(true);
			ItemAcces.setEnabled(true);
	}

	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}

	public void programaEventos() {

		ItemHistoricoDetDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {			
								
				if(mgr.WindowsStatClose){
					try {

						mgr.WindowsStatClose = false;

						if (!desktop.isAncestorOf(mgr.getHistDetDoc().getInstance())) {
							desktop.add(mgr.getHistDetDoc().getInstance());
							Dimension desktopSize = desktop.getSize();
							Dimension FrameSize = mgr.getHistDetDoc().getInstance().getSize();
							mgr.getHistDetDoc().getInstance().setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
						} else {
							mgr.getHistDetDoc().setVisible(true);
						}
					} catch (Exception ex) {
						Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}
		});

		ItemEstadoCuentaFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				
				if(mgr.WindowsStatClose){
					try {
						mgr.WindowsStatClose = false;

						if (!desktop.isAncestorOf(mgr.getEstCuxFech().getInstanciaEstadoCuentaxFecha())) {
							desktop.add(mgr.getEstCuxFech().getInstanciaEstadoCuentaxFecha());
							Dimension desktopSize = desktop.getSize();
							Dimension FrameSize = mgr.getEstCuxFech().getInstanciaEstadoCuentaxFecha().getSize();
							mgr.getEstCuxFech().getInstanciaEstadoCuentaxFecha().setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
						} else {
							mgr.getEstCuxFech().setVisible(true);
						}
					} catch (Exception ex) {
						Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				
			}
		});

		ItemAcces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(mgr.WindowsStatClose){
					try {
						mgr.WindowsStatClose = false;

						if (!desktop.isAncestorOf(mgr.getAcces().getInstanciaAcceso())) {
							desktop.add(mgr.getAcces().getInstanciaAcceso());
							Dimension desktopSize = desktop.getSize();
							Dimension FrameSize = mgr.getAcces().getInstanciaAcceso().getSize();
							mgr.getAcces().getInstanciaAcceso().setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
						} else {
							mgr.getAcces().setVisible(true);
						}
					} catch (Exception ex) {
						Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				
			}
		});
		
		ItemEstadoCuentaSunny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(mgr.WindowsStatClose){
					try {
						mgr.WindowsStatClose = false;

						if (!desktop.isAncestorOf(mgr.getEstCuSunny().getInstanciaEstadoCuentaSunny())) {
							desktop.add(mgr.getEstCuSunny().getInstanciaEstadoCuentaSunny());
							Dimension desktopSize = desktop.getSize();
							Dimension FrameSize = mgr.getEstCuSunny().getInstanciaEstadoCuentaSunny().getSize();
							mgr.getEstCuSunny().getInstanciaEstadoCuentaSunny().setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
						} else {
							mgr.getEstCuSunny().setVisible(true);
						}
					} catch (Exception ex) {
						Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				
			}
		});
		
		ItemTipoCambio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Hola Mundo:"+mgr.WindowsStatClose);
				
				if (mgr.WindowsStatClose) {
					try {
						mgr.WindowsStatClose = false;

						if (!desktop.isAncestorOf(mgr.getTipcamb().getInstanciaTipodeCambio())) {
							desktop.add(mgr.getTipcamb().getInstanciaTipodeCambio());
							Dimension desktopSize = desktop.getSize();
							Dimension FrameSize = mgr.getTipcamb().getInstanciaTipodeCambio().getSize();
							mgr.getTipcamb().getInstanciaTipodeCambio().setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
						} else {
							mgr.getTipcamb().setVisible(true);
						}
					} catch (Exception ex) {
						Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				
			}
		});
		
		ItemDireccionCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Hola Mundo:"+mgr.WindowsStatClose);

				if (mgr.WindowsStatClose) {
					try {
						mgr.WindowsStatClose = false;

						if (!desktop.isAncestorOf(mgr.getDirCli().getInstanciaDireccionCliente())) {
							desktop.add(mgr.getDirCli().getInstanciaDireccionCliente());
							Dimension desktopSize = desktop.getSize();
							Dimension FrameSize = mgr.getDirCli().getInstanciaDireccionCliente().getSize();
							mgr.getDirCli().getInstanciaDireccionCliente().setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
						} else {
							mgr.getDirCli().setVisible(true);
						}
					} catch (Exception ex) {
						Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}
		});

	}

//    public static void main(String args[]){
//        Menu mn = new Menu();
//    }
}
