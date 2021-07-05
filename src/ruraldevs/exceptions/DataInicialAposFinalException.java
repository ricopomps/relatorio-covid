package ruraldevs.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataInicialAposFinalException extends Exception {
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	public DataInicialAposFinalException(LocalDate dataInicial, LocalDate dataFinal) {
		super(String.format("Data inicial (%s) vem após a data final (%s).", DateTimeFormatter.ofPattern("dd/MM/YYYY").format(dataInicial), DateTimeFormatter.ofPattern("dd/MM/YYYY").format(dataFinal)));
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
}
