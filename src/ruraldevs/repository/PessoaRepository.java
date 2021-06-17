package ruraldevs.repository;

import java.util.ArrayList;

import ruraldevs.beans.Pessoa;

public class PessoaRepository extends GenericRepository<Pessoa> {
	private static PessoaRepository instance;

	private PessoaRepository() {
		super();
	}

	public static PessoaRepository getInstance() {
		if (instance == null) {
			instance = new PessoaRepository();
		}
		return instance;
	}

}
