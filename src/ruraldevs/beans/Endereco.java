package ruraldevs.beans;

public class Endereco {

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
