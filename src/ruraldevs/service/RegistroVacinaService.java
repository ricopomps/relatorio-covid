package ruraldevs.service;

import java.util.List;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.repository.RegistroVacinaRepository;

public class RegistroVacinaService {
	private RegistroVacinaRepository repositorio;

	public RegistroVacinaService() {
		this.repositorio = RegistroVacinaRepository.getInstance();
	}

	public void addRegistroVacina(RegistroVacina registroVacina) throws Exception {
		this.repositorio.addDado(registroVacina);
	}

	public List<RegistroVacina> getRegistrosVacinas() {
		return this.repositorio.getDados();
	}

	public void deleteRegistroVacina(RegistroVacina registroVacina) {
		this.repositorio.deleteDado(registroVacina);
	}

	public void updateRegistroVacina(RegistroVacina registroVacina) {
		this.repositorio.updateDado(registroVacina);
	}

	public void salvarArquivo() {
		this.repositorio.salvarArquivo();
	}
}
