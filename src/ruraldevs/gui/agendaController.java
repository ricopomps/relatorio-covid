package gui;






import Beans.GrupoEnum;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class agendaController {
	
	
//	ObservableList<GrupoEnum> aw= FXCollections.observableArrayList();
//	aw=Arrays.asList(GrupoEnum.values());
//	public ObservableList<GrupoEnum> carregarAtributos() {
//		(aw)Arrays.asList(GrupoEnum.values());
//		   return 
//				   
//				   
//		}
	
	@FXML
	ChoiceBox<GrupoEnum> gruposelect= new ChoiceBox<>();
	
	@FXML
	 private void initialize() {
		
		  gruposelect.setItems(FXCollections.observableArrayList( GrupoEnum.values()));
		
		
	 }

	
	public void draggrupo(ActionEvent event) {
		
		  
				 
		 } 
		  
	
	    
		
			
}