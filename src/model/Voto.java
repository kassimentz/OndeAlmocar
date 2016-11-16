package model;

import java.util.Date;

public class Voto {

	private String cpf;
	private Restaurante restaurante;
	private Date data;
	
	public Voto(){
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Voto [cpf=" + cpf + ", restaurante=" + restaurante + ", data="
				+ data + "]";
	}

}
