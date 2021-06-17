package ruraldevs.repository;

import ruraldevs.beans.Vacina;

public class VacinaRepository extends GenericRepository<Vacina> {

	private static VacinaRepository instance;

	private VacinaRepository() {
		super();
	}

	public static VacinaRepository getInstance() {
		if (instance == null) {
			instance = new VacinaRepository();
		}
		return instance;
	}
}
