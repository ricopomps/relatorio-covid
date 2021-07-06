package ruraldevs.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistroVacina implements Serializable {
	private static final long serialVersionUID = -5028429473421539244L;
	private Pessoa pessoa;
	private Vacina vacina;
	private int dose;
	private LocalDate dataDaVacina;
	private LocalVacina localVacina;
	private GrupoEnum grupo;

	public RegistroVacina(Pessoa pessoa, Vacina vacina, LocalDate dataDaVacina, LocalVacina localVacina, GrupoEnum grupo) {
		this.pessoa = pessoa;
		this.vacina = vacina;
		this.dataDaVacina = dataDaVacina;
		this.localVacina = localVacina;
		this.grupo = grupo;
	}

	public RegistroVacina() {}

	@Override
	public String toString() {
		return String.format("[Pessoa = %s, Vacina = %s, Dose = %d,Data = %s, LocalVacina = %s, Grupo = %s]", pessoa, vacina, dose, dataDaVacina, localVacina, grupo);
	}

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

	public GrupoEnum getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEnum grupo) {
		this.grupo = grupo;
	}
}
