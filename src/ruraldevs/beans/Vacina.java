package ruraldevs.beans;

public class Vacina {

	private String lote;
	private VacinasEnum nomeVacina;
	private int dose;


	public Vacina() {}

	public Vacina(String lote, VacinasEnum nomeVacina, int dose) {
		this.lote = lote;
		this.nomeVacina = nomeVacina;
		this.dose = dose;
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

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}



}
