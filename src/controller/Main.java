package controller;

import model.logic.Logical;

import View.Login;
import View.Menu;
import View.EstadoCuentaCliente;
import View.EstadoCuentaxFecha;
import View.EstadoCuentaSunny;

import View.HistoricoDetalleDoc;
import View.Acceso;
import View.DireccionCliente;
import View.RegistrarEntrada;

import View.TipodeCambio;


import javax.swing.UIManager;

public class Main extends javax.swing.JFrame {

	Logical Logic;
	Login logeo;
	Menu menues;
	
	EstadoCuentaCliente EstCuCli;
	EstadoCuentaxFecha EstCuxFech;
	EstadoCuentaSunny EstCuSunny;
	
	HistoricoDetalleDoc HistDetDoc;
	Acceso Acces;
	
	TipodeCambio Tipcamb;
	DireccionCliente DirCli;
	RegistrarEntrada RegEnt;
	
	Manager mgr;

	public static void main(String[] args) throws Exception {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		Main inicio = new Main();
		inicio.iniciar();
	}

	private void iniciar() throws Exception {

		logeo = new Login();
		menues = new Menu();
		EstCuCli = new EstadoCuentaCliente();
		EstCuxFech = new EstadoCuentaxFecha();
		EstCuSunny = new EstadoCuentaSunny();
		Tipcamb = new TipodeCambio();
		DirCli = new DireccionCliente();
		RegEnt = new RegistrarEntrada();
		
		HistDetDoc = new HistoricoDetalleDoc();
		Acces = new Acceso();
		Logic = new Logical();
		mgr = new Manager();

		logeo.setManager(mgr);
		menues.setManager(mgr);
		EstCuCli.setManager(mgr);
		EstCuxFech.setManager(mgr);
		EstCuSunny.setManager(mgr);
		HistDetDoc.setManager(mgr);
		Acces.setManager(mgr);
		Logic.setManager(mgr);
		Tipcamb.setManager(mgr);
		DirCli.setManager(mgr);
		RegEnt.setManager(mgr);
				
		mgr.setLogic(Logic);
		mgr.setLogeo(logeo);
		mgr.setMenues(menues);
		mgr.setEstCuCli(EstCuCli);
		mgr.setEstCuxFech(EstCuxFech);
		mgr.setEstCuSunny(EstCuSunny);
		mgr.setHistDetDoc(HistDetDoc);
		mgr.setAcces(Acces);
		mgr.setTipcamb(Tipcamb);
		mgr.setDirCli(DirCli);
		mgr.setRegEnt(RegEnt);
		
		logeo.getXframe().setVisible(true);
		//logeo.setVisible(true);       
	}

//    private void initComponent() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
