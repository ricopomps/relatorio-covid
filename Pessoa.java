package Beans;

import java.time.LocalDate;

public class Pessoa {

	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String senha;
	
	public Pessoa(String nome, String cpf, LocalDate dataNascimento, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
	}
	
	public Pessoa() {
		super();
	}
	
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
	
	
	
	
	
}
