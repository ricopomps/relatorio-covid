package ruraldevs.beans;

public class LocalVacina {
	private String postoVacinacao;
	private Endereco enderecoVacina;
	private boolean isDriveThru;

	public LocalVacina() {

	}

	public LocalVacina(String postoVacinacao, Endereco enderecoVacina, boolean isDriveThru) {
		this.postoVacinacao = postoVacinacao;
		this.enderecoVacina = enderecoVacina;
		this.isDriveThru = isDriveThru;
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

	public boolean isIsDriveThru() {
		return isDriveThru;
	}

	public void setIsDriveThru(boolean isDriveThru) {
		this.isDriveThru = isDriveThru;
	}

}
