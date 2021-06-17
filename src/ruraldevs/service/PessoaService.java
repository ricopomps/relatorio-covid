package ruraldevs.service;

import java.util.List;

import ruraldevs.beans.Pessoa;
import ruraldevs.repository.PessoaRepository;

public class PessoaService {
	private PessoaRepository repositorio;

	public PessoaService() {
		this.repositorio = PessoaRepository.getInstance();
	}

	public void addPessoa(Pessoa pessoa) {
		this.repositorio.addDado(pessoa);
	}

	public List<Pessoa> getPessoas() {
		return this.repositorio.getDados();
	}

	public void deletePessoa(Pessoa pessoa) {
		this.repositorio.deleteDado(pessoa);
	}

	public void updatePessoa(Pessoa pessoa) {
		this.repositorio.updateDado(pessoa);
	}

}
