package ruraldevs.beans;

import java.util.ArrayList;


public enum CentrosEnum {
	COMPAZ("Compaz Ariano Suassuna (Cordeiro)"),
	DNCOS("DNCOS (Tamarineira"),
	GERALDAO("Geraldao (Imbiribeira)"),
	PQMAC("Parque da Macaxeira (Macaxeira)"),
	PQANI("Parque de Exposição (Cordeiro)"),
	UCIS("UCIS (Hipódromo)"),
	UNICAP("UNICAP (Boa Vista)"),
	UFRPE("UFRPE (Dois Irmãos)"),
	UPA("UPA-E (Ibura)");

	private String nomeCentro;

	CentrosEnum(String string) {
		nomeCentro = string;
	}

	@Override
	public String toString() {
		return nomeCentro;
	}

	public String getNomeCentro() {
		return nomeCentro;
	}

	public static ArrayList<String> names() {
		ArrayList<String> states = new ArrayList<String>();
		CentrosEnum arr[] = CentrosEnum.values();
		for (CentrosEnum centrosenum : arr) {

			states.add(centrosenum.getNomeCentro());
		}
		return states;
	}



}
