package ruraldevs.repository;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> {
	private List<T> dados;

	GenericRepository() {
		this.dados = new ArrayList<T>();
	}

	public List<T> getDados() {
		return dados;
	}

	public void addDado(T dado) {
		if (!this.dados.contains(dado)) {
			this.dados.add(dado);
		}
	}
	
	public void deleteDado(T dado) {
		if (this.dados.contains(dado)) {
			this.dados.remove(dado);
		}
	}
	
	public void updateDado(T dado) {
		if (this.dados.contains(dado)) {
			this.dados.set(this.dados.indexOf(dado), dado);
		}
	}

}
