package ruraldevs.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ruraldevs.beans.GrupoEnum;

public class agendaController {
	@FXML
	ChoiceBox<GrupoEnum> gruposelect = new ChoiceBox<>();

	@FXML
	private void initialize() {
		gruposelect.setItems(FXCollections.observableArrayList(GrupoEnum.values()));
	}
}
