package controller;

import java.util.ArrayList;
import java.awt.EventQueue;

import View.Login;
import View.Menu;
import View.EstadoCuentaCliente;
import View.EstadoCuentaxFecha;
import View.EstadoCuentaSunny;

import View.HistoricoDetalleDoc;
import View.Acceso;

import View.TipodeCambio;
import View.DireccionCliente;
import View.RegistrarEntrada;

import model.logic.Logical;
import model.vo.UsuarioVO;
import model.vo.AgenciaVO;
import model.vo.ArticuloVO;
import model.vo.ClienteVO;
import model.vo.TipMovVO;
import model.vo.AlmacenVO;
import model.vo.ProveedorVO;

public class Manager {

	private Login logeo;
	private Menu menues;
	
	private EstadoCuentaCliente EstCuCli;
	private EstadoCuentaxFecha EstCuxFech;
	private EstadoCuentaSunny EstCuSunny;	
	
	private HistoricoDetalleDoc HistDetDoc;
	private Acceso Acces;
	
	private TipodeCambio Tipcamb;
	private DireccionCliente DirCli;
	private RegistrarEntrada RegEnt;
	
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

	public TipodeCambio getTipcamb() {
		return Tipcamb;
	}

	public void setTipcamb(TipodeCambio Tipcamb) {
		this.Tipcamb = Tipcamb;
	}

	public DireccionCliente getDirCli() {
		return DirCli;
	}

	public void setDirCli(DireccionCliente DirCli) {
		this.DirCli = DirCli;
	}	
	
	public RegistrarEntrada getRegEnt() {
		return RegEnt;
	}

	public void setRegEnt(RegistrarEntrada RegEnt) {
		this.RegEnt = RegEnt;
	}
	
	public void ShowMenu(String Cargo) {

//		if (Cargo.equals("ADMINISTRADOR")) {
//			menues.OpcionAdmin();
//		} else if (Cargo.equals("JEFE_OPE")) {
//			menues.OpcionJefeOpe();
//		} else if (Cargo.equals("OPERACIONES")) {
//			menues.OpcionOpe();
//		}
//		menues.getMenframe().setVisible(true);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// crea el JFrame 
					if (Cargo.equals("ADMINISTRADOR")) {
						menues.OpcionAdmin();
					} else if (Cargo.equals("JEFE_OPE")) {
						menues.OpcionJefeOpe();
					} else if (Cargo.equals("OPERACIONES")) {
						menues.OpcionOpe();
					}
					menues.getMenframe().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public UsuarioVO ValidarAcceso(String user, String pass) {
		return Logic.ValidarAcceso(user, pass);
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
	
	public ArrayList<ArticuloVO> MostrarArticulo(String codprod, String prod) {
		Logic = new Logical();
		return Logic.MostrarArticulos(codprod, prod);
	}
        
                public ArrayList<TipMovVO> MostrarTipMov(String codigo, String descripcion) {
		Logic = new Logical();
		return Logic.MostrarTipMov(codigo, descripcion);
	}
				
	public ArrayList<AlmacenVO> ConsultarAlmacen() {
		Logic = new Logical();
		return Logic.ConsultarAlmacen();
	}
	
	public ArrayList<ProveedorVO> MostrarProveedor(String codigo, String proveedor) {
		Logic = new Logical();
		return Logic.MostrarProveedor(codigo, proveedor);
	}

}
