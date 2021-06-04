package Beans;

public class Endereco {
	
private String cidade;
private estados Estado;
private String cep;


public Endereco() {
}

public Endereco(String cidade, estados estado, String cep) {
	this.cidade = cidade;
	Estado = estado;
	this.cep = cep;
}
public String getCidade() {
	return cidade;
}
public void setCidade(String cidade) {
	this.cidade = cidade;
}
public estados getEstado() {
	return Estado;
}
public void setEstado(estados estado) {
	Estado = estado;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}



}
