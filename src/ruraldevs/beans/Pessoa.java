package ruraldevs.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pessoa implements Serializable{

	private static final long serialVersionUID = -7599895100973516296L;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String senha;
	private GrupoEnum grupo;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
	}

	public Pessoa(String cpf,String senha) {
		
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public Pessoa() {}

	public int calcularIdade() {
		int idade = (int) dataNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
		return idade;
	}

	// EQUALS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	// GETTERS E SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public GrupoEnum getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEnum grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", senha=" + senha
				+ "]";
	}}

	
