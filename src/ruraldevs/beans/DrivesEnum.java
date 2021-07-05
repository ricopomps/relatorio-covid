package ruraldevs.beans;

import java.util.ArrayList;

public enum DrivesEnum {
	BIGBV("Big Bompreço (Boa Viagem)"),
	BIGCF("Big Bompreço (Casa Forte)"),
	CRRF("Carrefour (Torre)"),
	DNOCS("DNOCS (Tamarineira)"),
	GERALDAO("Geraldao (Imbiribeira)"),
	JFPE("Fórum Justiça Federal de Pernambuco (Jiquiá)"),
	PQMAC("Parque da Macaxeira (Macaxeira)"),
	PQANI("Parque de Exposição de Animais (Cordeiro)"),
	TRF("Tribunal Regional Federal da 5ª Região (Bairro do Recife)"),
	UFPE("UFPE (Cidade Universitária)"),
	UFRPE("UFRPE (Dois Imrãos)"),
	UNICAP("UNICAP (Boa Vista)");

	private String nomeDrive;

	DrivesEnum(String nomeDrive) {
		this.nomeDrive = nomeDrive;
	}


	@Override
	public String toString() {
		return nomeDrive;
	}

	public String getNomeDrive() {
		return nomeDrive;
	}

	public static ArrayList<String> names() {

		ArrayList<String> states = new ArrayList<String>();
		DrivesEnum arr[] = DrivesEnum.values();
		for (DrivesEnum drivessenum : arr) {

			states.add(drivessenum.getNomeDrive());
		}


		return states;
	}



}

