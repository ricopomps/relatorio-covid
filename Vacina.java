package Beans;

public class Vacina {

	private String lote;
	private vacinas NomeVacina;
	private int dose;
	
	
	public Vacina() {
	}
	public Vacina(String lote, vacinas nomeVacina, int dose) {
		this.lote = lote;
		NomeVacina = nomeVacina;
		this.dose = dose;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public vacinas getNomeVacina() {
		return NomeVacina;
	}
	public void setNomeVacina(vacinas nomeVacina) {
		NomeVacina = nomeVacina;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}

	
	
}
