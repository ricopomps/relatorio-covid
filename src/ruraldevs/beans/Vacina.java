package ruraldevs.beans;
import java.time.LocalDate;

public class Vacina {

	private String lote;
	private VacinasEnum nomeVacina;
	private int dose;
	private LocalDate dataVacina;
	private LocalVacina localvacina;


	public Vacina() {}

	public Vacina(String lote, VacinasEnum nomeVacina, int dose, LocalDate dataVacina,LocalVacina localvacina) {
		this.lote = lote;
		this.nomeVacina = nomeVacina;
		this.dose = dose;
		this.dataVacina=dataVacina;
		this.localvacina=localvacina;
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
	public LocalDate getDataVacina() {
		return dataVacina;
	}
	public void setDataVacina(LocalDate dataVacina) {
		this.dataVacina = dataVacina;
	}
	
	public LocalVacina getLocalvacina() {
		return localvacina;
	}
	public void setLocalvacina(LocalVacina localvacina) {
		this.localvacina = localvacina;
	}


}
