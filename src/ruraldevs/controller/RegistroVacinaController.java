package ruraldevs.controller;

import java.util.List;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.service.RegistroVacinaService;

public class RegistroVacinaController {
	private RegistroVacinaService service;

	public RegistroVacinaController() {
		this.service = new RegistroVacinaService();
	}

	public void addRegistroVacina(RegistroVacina registroVacina) {
		this.service.addRegistroVacina(registroVacina);
	}

	public List<RegistroVacina> getRegistrosVacinas() {
		return this.service.getRegistrosVacinas();
	}

	public void deleteRegistroVacina(RegistroVacina registroVacina) {
		this.service.deleteRegistroVacina(registroVacina);
	}

	public void updateRegistroVacina(RegistroVacina registroVacina) {
		this.service.updateRegistroVacina(registroVacina);
	}
}
