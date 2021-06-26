package ruraldevs.gui;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Beans.Endereco;
import Beans.EstadosEnum;
import Beans.GrupoEnum;
import Beans.LocalVacina;
import Beans.Pessoa;
import Beans.RegistroVacina;
import Beans.Vacina;
import Beans.VacinasEnum;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class agendaController {
	
	public static int a=0;
	@FXML
	Hyperlink voltarbttn;
	@FXML
	TextField cidadefield;
	@FXML
	TextField ceptext;
	@FXML
	Button bttnagendar;

	@FXML
	ChoiceBox<GrupoEnum> gruposelect= new ChoiceBox<>();
	@FXML
	ChoiceBox<EstadosEnum> ufselect= new ChoiceBox<>();
	@FXML
	public void voltarpressed(ActionEvent event) {mainTelas.changeScreen("main"); 				}
	
	@FXML
	 private void initialize() {
		
		  gruposelect.setItems(FXCollections.observableArrayList(GrupoEnum.values()));
		  ufselect.setItems(FXCollections.observableArrayList(EstadosEnum.values()));
		  
	 }
	
	
	private static final List<VacinasEnum> VALUES =Collections.unmodifiableList(Arrays.asList(VacinasEnum.values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	
	 public static VacinasEnum random()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		    
		  }
		
	@FXML
	public void bttnagendarpressed(ActionEvent event) {
	LocalVacina b= new LocalVacina();
	Endereco c=new Endereco();
	Vacina d=new Vacina();
	d.setNomeVacina(random());
	c.setEstado(ufselect.getValue());
	b.setEnderecoVacina(c);
	LocalDate datenow = LocalDate.now().plusDays(15);
	RegistroVacina cadastro= new RegistroVacina(mainTelas.ac, d ,datenow, b, gruposelect.getValue());
	mainTelas.ab=cadastro;
	mainTelas.changeScreen("status");

	
	
	
	
	
	}
	
	
		
	
	
	
	
			
}
