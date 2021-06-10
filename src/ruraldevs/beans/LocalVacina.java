package Beans;

public class LocalVacina {
	
	private String postoVacinacao;
	private EstadosEnum EstadoVacinacao;
	
	public LocalVacina() {
		
	}

	public LocalVacina(String postoVacinacao, EstadosEnum estadoVacinacao) {
		this.postoVacinacao = postoVacinacao;
		EstadoVacinacao = estadoVacinacao;
	}
	
	public String getPostoVacinacao() {
		return postoVacinacao;
	}
	public void setPostoVacinacao(String postoVacinacao) {
		this.postoVacinacao = postoVacinacao;
	}
	public EstadosEnum getEstadoVacinacao() {
		return EstadoVacinacao;
	}
	public void setEstadoVacinacao(EstadosEnum estadoVacinacao) {
		EstadoVacinacao = estadoVacinacao;
	}

}
