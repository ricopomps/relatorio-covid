package ruraldevs.repository;

import ruraldevs.beans.RegistroVacina;

public class RegistroVacinaRepository extends GenericRepository<RegistroVacina> {

	private static RegistroVacinaRepository instance;

	private RegistroVacinaRepository() {
		super();
	}

	public static RegistroVacinaRepository getInstance() {
		if (instance == null) {
			instance = new RegistroVacinaRepository();
		}
		return instance;
	}
}
