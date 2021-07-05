package ruraldevs.beans;

import java.io.Serializable;

public class Endereco implements Serializable { 
	
	private static final long serialVersionUID = -7072971503986928197L;
	private String cidade;
	private EstadosEnum estado;
	private String cep;


	public Endereco() {}

	public Endereco(String cidade, EstadosEnum estado, String cep) {
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
