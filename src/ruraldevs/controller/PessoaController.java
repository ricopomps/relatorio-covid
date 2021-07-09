package ruraldevs.controller;

import java.util.List;
import ruraldevs.beans.Pessoa;
import ruraldevs.service.PessoaService;

public class PessoaController {
	private PessoaService service;

	public PessoaController() {
		this.service = new PessoaService();
	}

	public void addPessoa(Pessoa pessoa) throws Exception {
		this.service.addPessoa(pessoa);
	}

	public List<Pessoa> getPessoas() {
		return this.service.getPessoas();
	}

	public void deletePessoa(Pessoa pessoa) {
		this.service.deletePessoa(pessoa);
	}

	public void updatePessoa(Pessoa pessoa) {
		this.service.updatePessoa(pessoa);
	}

	public void salvar() {
		this.service.salvarService();
	}
}

