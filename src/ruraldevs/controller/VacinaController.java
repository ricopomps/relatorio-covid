package ruraldevs.controller;

import java.util.List;

import ruraldevs.beans.Vacina;
import ruraldevs.service.VacinaService;

public class VacinaController {
	private VacinaService service;

	public VacinaController() {
		this.service = new VacinaService();
	}

	public void addVacina(Vacina vacina) {
		this.service.addVacina(vacina);
	}

	public List<Vacina> getVacinas() {
		return this.service.getVacinas();
	}

	public void deleteVacina(Vacina vacina) {
		this.service.deleteVacina(vacina);
	}

	public void updateVacina(Vacina vacina) {
		this.service.updateVacina(vacina);
	}
}
