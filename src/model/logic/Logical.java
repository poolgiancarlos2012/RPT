package model.logic;

import java.util.ArrayList;

import controller.Manager;

import model.vo.UsuarioVO;
import model.dao.UsuarioDAO;
import model.vo.AgenciaVO;
import model.dao.AgenciaDAO;
import model.dao.ArticuloDAO;
import model.vo.ClienteVO;
import model.dao.ClienteDAO;
import model.vo.ArticuloVO;
import model.dao.TipMovDAO;
import model.vo.TipMovVO;
import model.dao.AlmacenDAO;
import model.vo.AlmacenVO;
import model.dao.ProveedorDAO;
import model.vo.ProveedorVO;


public class Logical {

	private Manager mgr;

	public static boolean consultaUsuario = false;
	public static boolean consultaAgen0002 = false;
	public static boolean consultaAgen = false;
	public static boolean consultaCliente = false;
	public static boolean consultaArticulo = false;
	public static boolean consultatipmov = false;
	public static boolean consultaAlmacen = false;
	public static boolean consultaProveedor = false;

	public void setManager(Manager mgr) {
		this.mgr = mgr;
	}

	public UsuarioVO ValidarAcceso(String user, String pass) {
		UsuarioDAO usuariodao;

		try {
			consultaUsuario = true;

			usuariodao = new UsuarioDAO();

			return usuariodao.ValidarAcceso(user, pass);

		} catch (Exception e) {
			consultaUsuario = false;
		}

		return null;
	}
	
	public ArrayList<AgenciaVO>  ConsultarAgenciaCAISAC() {
		AgenciaDAO agenciadao;
		try {
			consultaAgen0002 = true;
			agenciadao = new AgenciaDAO();
			return agenciadao.ConsultarAgenciaCAISAC();
		} catch (Exception ex) {
			consultaAgen0002 = false;
		}

		return null;
	}	
	
	public ArrayList<AgenciaVO>  ConsultarAgenciaANDEX() {
		AgenciaDAO agenciadao;
		try {
			consultaAgen = true;
			agenciadao = new AgenciaDAO();
			return agenciadao.ConsultarAgenciaANDEX();
		} catch (Exception ex) {
			consultaAgen = false;
		}

		return null;
	}
	
	public ArrayList<AgenciaVO>  ConsultarAgenciaSEMILLAS() {
		AgenciaDAO agenciadao;
		try {
			consultaAgen = true;
			agenciadao = new AgenciaDAO();
			return agenciadao.ConsultarAgenciaSEMILLAS();
		} catch (Exception ex) {
			consultaAgen = false;
		}

		return null;
	}
	
	public ArrayList<AgenciaVO>  ConsultarAgenciaSUNNY() {
		AgenciaDAO agenciadao;
		try {
			consultaAgen = true;
			agenciadao = new AgenciaDAO();
			return agenciadao.ConsultarAgenciaSUNNY();
		} catch (Exception ex) {
			consultaAgen = false;
		}

		return null;
	}
	
	public ArrayList<ClienteVO> MostrarCliente(String cod_cliente, String razon_social){
		ClienteDAO clientedo;
		
		try {
			consultaCliente = true;
			clientedo = new ClienteDAO();
			return clientedo.DAO_MostrarClientes(cod_cliente, razon_social);
		} catch (Exception e) {
			consultaCliente = false;
		}
		
		return null;
		
	}
	
	public boolean ActualizarDirectoryDownload(String dni, String ruta){
		UsuarioDAO usuariodao;
				
		try {
			usuariodao = new UsuarioDAO();
			return usuariodao.ActualizarDirectoryDownload(dni, ruta);
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean ActualizarContrasenia(String xalias,String NewPass){
		UsuarioDAO usuariodao;
				
		try {
			usuariodao = new UsuarioDAO();
			return usuariodao.ActualizarContrasenia(xalias, NewPass);
		} catch (Exception e) {
			return false;
		}
	}
	
	public ArrayList<ArticuloVO> MostrarArticulos(String codprod, String prod){
		ArticuloDAO articulosdo;
		
		try {
			consultaArticulo = true;
			articulosdo = new ArticuloDAO();
			return articulosdo.DAO_MostrarArticulos(codprod, prod);
		} catch (Exception e) {
			consultaArticulo = false;
		}
		
		return null;
		
	}
        
                public ArrayList<TipMovVO> MostrarTipMov(String codigo, String descripcion){
		TipMovDAO tipmovdao;
		
		try {
			consultatipmov = true;
			tipmovdao = new TipMovDAO();
			return tipmovdao.DAO_MostrarTipMov(codigo, descripcion);
		} catch (Exception e) {
			consultatipmov = false;
		}
		
		return null;
		
	}
				
	public ArrayList<AlmacenVO>  ConsultarAlmacen() {
		AlmacenDAO almacendao;
		try {
			consultaAlmacen = true;
			almacendao = new AlmacenDAO();
			return almacendao.ConsultarAlmacen();
		} catch (Exception ex) {
			consultaAlmacen = false;
		}

		return null;
	}	
	
	public ArrayList<ProveedorVO> MostrarProveedor(String codigo, String proveedor){
		ProveedorDAO proveedordao;
		
		try {
			consultaProveedor = true;
			proveedordao = new ProveedorDAO();
			return proveedordao.DAO_MostrarProveedor(codigo, proveedor);
		} catch (Exception e) {
			consultaProveedor = false;
		}
		
		return null;
		
	}
	

}
