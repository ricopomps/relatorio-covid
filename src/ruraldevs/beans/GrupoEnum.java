package ruraldevs.beans;

public enum GrupoEnum {
	pessoas43mais("Pesssoas com 43 anos ou mais"),
	trabalhadores_industriais("Trabalhadores Industriais"),
	trabalhadores_a_s("Trabalhadores da assistência social"),
	caminhoneiros("Caminhoneiros"),
	forcas_seg_salv("Forças de segurança e salvamento"),
	trabalhadores_limpeza("Trabalhadores da Limpeza Urbana e Manejo de Resíduos Sólidos"), pessoas_de_rua("Pessoas em Situação de Rua"),
	transporte_coletivo_rodoviario("Trabalhadores de Transporte Coletivo Rodoviário Passageiros Urbano e de Longo Curso"), transporte_metro_ferroviario("Trabalhadores de Transporte Metroviário e Ferroviário"),
	transporte_aereo("Trabalhadores de Transporte Aéreo"),
	portuarios("Trabalhadores Portuários"),
	transporte_aquaviario("Trabalhadores de Transporte Aquaviário"),
	gravidas("Grávidas e Puérperas "),
	deficientes("Pessoas com deficiência"),
	comorbidades("Pessoas com comorbidades"),
	educacao("Trabalhadores da Educação"),
	saude("Trabalhadores da Saúde");

	private String nomeGrupo;

	GrupoEnum(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	@Override
	public String toString() {
		return nomeGrupo;
	}
}
