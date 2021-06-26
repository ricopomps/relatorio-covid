package ruraldevs.beans;

public class Vacina {

	private String lote;
	private VacinasEnum nomeVacina;

	public Vacina() {}

	public Vacina(String lote, VacinasEnum nomeVacina) {
		this.lote = lote;
		this.nomeVacina = nomeVacina;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public VacinasEnum getNomeVacina() {
		return this.nomeVacina;
	}

	public void setNomeVacina(VacinasEnum nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
}
