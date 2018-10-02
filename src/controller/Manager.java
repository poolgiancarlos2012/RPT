package controller;

import java.util.ArrayList;

import View.Login;
import View.Menu;
import View.EstadoCuentaCliente;
import View.EstadoCuentaxFecha;
import View.EstadoCuentaSunny;

import View.HistoricoDetalleDoc;
import View.Acceso;

import model.logic.Logical;
import model.vo.UsuarioVO;
import model.vo.AgenciaVO;
import model.vo.ClienteVO;

public class Manager {

	private Login logeo;
	private Menu menues;
	
	private EstadoCuentaCliente EstCuCli;
	private EstadoCuentaxFecha EstCuxFech;
	private EstadoCuentaSunny EstCuSunny;	
	
	private HistoricoDetalleDoc HistDetDoc;
	private Acceso Acces;
	
	private Logical Logic;

	public static boolean WindowsStatClose = true; // IF ALL WINDOWS IS CLOSED
	public static String User = "";
	public static String Alias = "";
	public static String RutaDown = "";
	public static String Cargo = "";

	public Login getLogeo() {
		return logeo;
	}

	public void setLogeo(Login logeo) {
		this.logeo = logeo;
	}

	public Menu getMenues() {
		return menues;
	}

	public void setMenues(Menu menues) {
		this.menues = menues;
	}

	public Logical getLogic() {
		return Logic;
	}

	public void setLogic(Logical Logic) {
		this.Logic = Logic;
	}

	public EstadoCuentaCliente getEstCuCli() {
		return EstCuCli;
	}

	public void setEstCuCli(EstadoCuentaCliente EstCuCli) {
		this.EstCuCli = EstCuCli;
	}

	public HistoricoDetalleDoc getHistDetDoc() {
		return HistDetDoc;
	}

	public void setHistDetDoc(HistoricoDetalleDoc HistDetDoc) {
		this.HistDetDoc = HistDetDoc;
	}

	public EstadoCuentaxFecha getEstCuxFech() {
		return EstCuxFech;
	}

	public void setEstCuxFech(EstadoCuentaxFecha EstCuxFech) {
		this.EstCuxFech = EstCuxFech;
	}
	
	public EstadoCuentaSunny getEstCuSunny() {
		return EstCuSunny;
	}

	public void setEstCuSunny(EstadoCuentaSunny EstCuSunny) {
		this.EstCuSunny = EstCuSunny;
	}
	
	public Acceso getAcces() {
		return Acces;
	}

	public void setAcces(Acceso Acces) {
		this.Acces = Acces;
	}

	public void ShowMenu(String Cargo) {

		if (Cargo.equals("ADMINISTRADOR")) {
			menues.OpcionAdmin();
		} else if (Cargo.equals("JEFE_OPE")) {
			menues.OpcionJefeOpe();
		} else if (Cargo.equals("OPERACIONES")) {
			menues.OpcionOpe();
		}
		menues.getMenframe().setVisible(true);

	}

	public UsuarioVO ValidarAcceso(String user, String pass) {
		
//		boolean rpta

//		UsuarioVO busq =  Logic.ValidarAcceso(user, pass);
//		Alias = busq.getAlias();
//		User = busq.getUsuario();
		
		return Logic.ValidarAcceso(user, pass);
//		return busq;
	}

	public ArrayList<AgenciaVO> ConsultarAgenciaCAISAC() {
		Logic = new Logical();
		return Logic.ConsultarAgenciaCAISAC();
	}

	public ArrayList<AgenciaVO> ConsultarAgenciaANDEX() {
		Logic = new Logical();
		return Logic.ConsultarAgenciaANDEX();
	}

	public ArrayList<AgenciaVO> ConsultarAgenciaSEMILLAS() {
		Logic = new Logical();
		return Logic.ConsultarAgenciaSEMILLAS();
	}

	public ArrayList<AgenciaVO> ConsultarAgenciaSUNNY() {
		Logic = new Logical();
		return Logic.ConsultarAgenciaSUNNY();
	}

	public ArrayList<ClienteVO> MostrarCliente(String cod_cliente, String razon_social) {
		Logic = new Logical();
		return Logic.MostrarCliente(cod_cliente, razon_social);
	}
	
	public boolean ActualizarDirectoryDownload(String dni, String ruta){
		Logic = new Logical();
		return Logic.ActualizarDirectoryDownload(dni, ruta);
	}
	
	public boolean ActualizarContrasenia(String xalias,String NewPass){
		Logic = new Logical();
		return Logic.ActualizarContrasenia(xalias, NewPass);
	}

}