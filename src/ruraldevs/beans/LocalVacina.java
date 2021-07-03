package ruraldevs.beans;

import java.io.Serializable;

public class LocalVacina implements Serializable {
	private String postoVacinacao;
	private Endereco enderecoVacina;
	private boolean isDriveThru;
	private static final long serialVersionUID = 5494810606247939645L;

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
