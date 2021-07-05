package beans;
import java.util.ArrayList;


public enum CentrosEnum { compaz("Compaz Ariano Suassuna(Cordeiro)"),dncos("DNCOS(Tamarineira"),geraldao("Geraldao(Imbiribeira)"),pqMac("Parque da Macaxeira(Macaxeira)")
	,pqAni("Parque de Exposição(Cordeiro)"),ucis("UCIS(Hipódromo)"),unicap("UNICAP(Boa Vista)"),ufrpe("UFRPE(Dois Irmãos)"),upa("UPA-E(Ibura)");
	private String nomeCentro;
	
	
	
	CentrosEnum(String string) {
		nomeCentro=string;
	}

	
	@Override
    public String toString() {
        return nomeCentro;
	}

	public String getNomeCentro() {
		return nomeCentro;
	}

	public static ArrayList<String> names() {
	    
		ArrayList<String> states= new ArrayList<String>();
		CentrosEnum arr[] = CentrosEnum.values();
		for (CentrosEnum centrosenum : arr) {
	
		states.add(centrosenum.getNomeCentro());
}
	    

	    return states;
	}

	



}
