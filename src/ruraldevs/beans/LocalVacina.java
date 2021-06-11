package ruraldevs.beans;

public class LocalVacina {

	private String postoVacinacao;
	private Endereco enderecoVacina;

	public LocalVacina() {

	}

	public LocalVacina(String postoVacinacao, Endereco enderecoVacina) {
		this.postoVacinacao = postoVacinacao;
		this.enderecoVacina = enderecoVacina;
	}

	public String getPostoVacinacao() {
		return postoVacinacao;
	}

	public void setPostoVacinacao(String postoVacinacao) {
		this.postoVacinacao = postoVacinacao;
	}

	public Endereco getEnderecoVacina() {
		return enderecoVacina;
	}

	public void setEnderecoVacina(Endereco enderecoVacina) {
		this.enderecoVacina = enderecoVacina;
	}

}
