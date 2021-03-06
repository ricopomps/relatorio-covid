package ruraldevs.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> implements Serializable {
	private static final long serialVersionUID = 5771985947054727592L;
	private List<T> dados;

	GenericRepository() {
		this.dados = new ArrayList<T>();
	}

	public List<T> getDados() {
		return dados;
	}

	public void addDado(T dado) throws Exception {
		if (!this.dados.contains(dado)) {
			this.dados.add(dado);
		} else {
			throw new Exception();
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
