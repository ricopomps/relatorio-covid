package ruraldevs.service;

import java.util.List;
import ruraldevs.beans.Vacina;
import ruraldevs.repository.VacinaRepository;

public class VacinaService {
	private VacinaRepository repositorio;

	public VacinaService() {
		this.repositorio = VacinaRepository.getInstance();
	}

	public void addVacina(Vacina vacina) throws Exception {
		this.repositorio.addDado(vacina);
	}

	public List<Vacina> getVacinas() {
		return this.repositorio.getDados();
	}

	public void deleteVacina(Vacina vacina) {
		this.repositorio.deleteDado(vacina);
	}

	public void updateVacina(Vacina vacina) {
		this.repositorio.updateDado(vacina);
	}
}
