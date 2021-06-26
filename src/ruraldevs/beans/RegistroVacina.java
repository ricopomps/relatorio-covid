package ruraldevs.beans;

import java.time.LocalDate;

public class RegistroVacina {
	private Pessoa pessoa;
	private Vacina vacina;
	private int dose;
	private LocalDate dataDaVacina;
	private LocalVacina localVacina;

	public RegistroVacina(Pessoa pessoa, Vacina vacina, LocalDate dataDaVacina, LocalVacina localVacina) {
		this.pessoa = pessoa;
		this.vacina = vacina;
		this.dataDaVacina = dataDaVacina;
		this.localVacina = localVacina;
	}
	public RegistroVacina() {}

	// GETTERS E SETTERS
	public LocalVacina getLocalVacina() {
		return localVacina;
	}

	public void setLocalVacina(LocalVacina localVacina) {
		this.localVacina = localVacina;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getDataDaVacina() {
		return dataDaVacina;
	}

	public void setDataDaVacina(LocalDate dataDaVacina) {
		this.dataDaVacina = dataDaVacina;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

}
