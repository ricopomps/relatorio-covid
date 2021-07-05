package beans;

import java.util.ArrayList;

public enum DrivesEnum {bigBV("Big Bompreço(Boa Viagem)"),bigCF("Big Bompreço (Casa Forte)"),crrf("Carrefour(Torre)"),dnocs("DNOCS(Tamarineira)"),geraldao("Geraldao(Imbiribeira)"),jfpe("Fórum Justiça Federal de Pernambuco(Jiquiá)")
		,pqMac("Parque da Macaxeira(Macaxeira)"),pqAni("Parque de Exposição de Animais(Cordeiro)"),trf("Tribunal Regional Federal da 5ª Região(Bairro do Recife)"),ufpe("UFPE(Cidade Universitária)"),ufrpe("UFRPE(Dois Imrãos)")
				,unicap("UNICAP(Boa Vista)");
	private String nomeDrive;
		
		DrivesEnum(String nomeDrive) {
		this.nomeDrive = nomeDrive;}

	
	@Override
    public String toString() {
        return nomeDrive;
	}
	
public String getNomeDrive() {
		return nomeDrive;
	}

public static ArrayList<String> names() {
	    
		ArrayList<String> states= new ArrayList<String>();
		DrivesEnum arr[] = DrivesEnum.values();
		for (DrivesEnum drivessenum : arr) {
	
		states.add(drivessenum.getNomeDrive());
}
	    

	    return states;
	}

	


}

