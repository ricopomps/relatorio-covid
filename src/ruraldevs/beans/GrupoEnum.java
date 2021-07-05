package ruraldevs.beans;

public enum GrupoEnum {
	PESSOAS_37_OU_MAIS("Pesssoas com 37 anos ou mais"),
	TRABALHADORES_INDUSTRIAIS("Trabalhadores Industriais"),
	TRABALHADORES_A_S("Trabalhadores da assistência social"),
	CAMINHONEIROS("Caminhoneiros"),
	FORCAS_SEG_SALV("Forças de segurança e salvamento"),
	TRABALHADORES_LIMPEZA("Trabalhadores da Limpeza Urbana e Manejo de Resíduos Sólidos"),
	PESSOAS_DE_RUA("Pessoas em Situação de Rua"),
	TRANSPORTE_COLETIVO_RODOVIARIO("Trabalhadores de Transporte Coletivo Rodoviário Passageiros Urbano e de Longo Curso"),
	TRANSPORTE_METRO_FERROVIARIO("Trabalhadores de Transporte Metroviário e Ferroviário"),
	TRANSPORTE_AEREO("Trabalhadores de Transporte Aéreo"),
	PORTUARIOS("Trabalhadores Portuários"),
	TRANSPORTE_AQUAVIARIO("Trabalhadores de Transporte Aquaviário"),
	GRAVIDAS("Grávidas e Puérperas"),
	DEFICIENTES("Pessoas com deficiência"),
	COMORBIDADES("Pessoas com comorbidades"),
	EDUCACAO("Trabalhadores da Educação"),
	SAUDE("Trabalhadores da Saúde");

	private String nomeGrupo;

	GrupoEnum(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	@Override
	public String toString() {
		return nomeGrupo;
	}
}
