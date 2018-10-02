package model.vo;

public class ClienteVO{

	private String CODIGO_CLIENTE;
	private String CLIENTE;

//	public ClienteVO(String CODIGO_CLIENTE,String  CLIENTE){
//		this.CODIGO_CLIENTE = CODIGO_CLIENTE;
//		this.CLIENTE = CLIENTE;
//	}
//	
	public String getCODIGO_CLIENTE() {
		return CODIGO_CLIENTE;
	}

	public void setCODIGO_CLIENTE(String CODIGO_CLIENTE) {
		this.CODIGO_CLIENTE = CODIGO_CLIENTE;
	}

	public String getCLIENTE() {
		return CLIENTE;
	}

	public void setCLIENTE(String CLIENTE) {
		this.CLIENTE = CLIENTE;
	}



}