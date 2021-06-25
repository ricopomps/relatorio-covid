package Beans;

public enum GrupoEnum{
	
	
	pessoas43mais("Pesssoas com 43 anos ou mais"),
	trabalhadores_industriais("Trabalhadores Industriais"),
	trabalhadores_a_s("Trabalhadores da assist�ncia social"),
	caminhoneiros("Caminhoneiros"),
	forcas_seg_salv("For�as de seguran�a e salvamento"),
	trabalhadores_limpeza("Trabalhadores da Limpeza Urbana e Manejo de Res�duos S�lidos"),
	pessoas_de_rua("Pessoas em Situa��o de Rua"),
	transporte_coletivo_rodoviario("Trabalhadores de Transporte Coletivo Rodovi�rio Passageiros Urbano e de Longo Curso"),
	transporte_metro_ferroviario("Trabalhadores de Transporte Metrovi�rio e Ferrovi�rio"),
	transporte_aereo("Trabalhadores de Transporte A�reo"),
	portuarios("Trabalhadores Portu�rios"),
	transporte_aquaviario("Trabalhadores de Transporte Aquavi�rio"),
	gravidas("Gr�vidas e Pu�rperas "),
	deficientes("Pessoas com defici�ncia"),
	comorbidades("Pessoas com comorbidades"),
	educacao("Trabalhadores da Educa��o"),
	saude("Trabalhadores da Sa�de");

	private String nomeGrupo;
	GrupoEnum(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;

	}
	@Override
    public String toString() {
        return nomeGrupo;
	}


}
