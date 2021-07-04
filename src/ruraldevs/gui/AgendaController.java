package ruraldevs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import ruraldevs.beans.Endereco;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.GrupoEnum;
import ruraldevs.beans.LocalVacina;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.beans.Vacina;
import ruraldevs.beans.VacinasEnum;

public class AgendaController implements Initializable {
	public static int a = 0;
	@FXML
	Hyperlink voltarBttn;
	@FXML
	TextField cidadeField;
	@FXML
	TextField cepText;
	@FXML
	Button bttnAgendar;
	@FXML
	DatePicker dtVac;
	@FXML
	ChoiceBox<GrupoEnum> grupoSelect = new ChoiceBox<>();
	@FXML
	ChoiceBox<EstadosEnum> ufSelect = new ChoiceBox<>();

	@FXML
	public void voltarpressed(ActionEvent event) {
		MainTelas.changeScreen("main");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (item.isBefore(LocalDate.now().plusDays(1)) || item.isAfter(LocalDate.now().plusMonths(4))) {
					setStyle("-fx-background-color: #EBEBEB; -fx-text-fill: darkgray;");
					setDisable(true);
				}
			}
		};
		dtVac.setDayCellFactory(dayCellFactory);

		grupoSelect.setItems(FXCollections.observableArrayList(GrupoEnum.values()));
		ufSelect.setItems(FXCollections.observableArrayList(EstadosEnum.values()));
	}

	private static final List<VacinasEnum> VALUES = Collections.unmodifiableList(Arrays.asList(VacinasEnum.values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static VacinasEnum random() {
		return VALUES.get(RANDOM.nextInt(SIZE));

	}

	@FXML
	public void bttnagendarpressed(ActionEvent event) throws Exception {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Dados incompletos");
		alert.setHeaderText("Dados vazios ou incorretos");
		alert.setContentText("Por favor, preencha os campos novamente com as informações corretas.");
		try {
			if (cidadeField.getText().isEmpty() || cepText.getText().isEmpty() || dtVac.getValue().equals(null) || grupoSelect.getValue().equals(null) || ufSelect.getValue().equals(null)) {
				alert.showAndWait();
				return;
			}
		} catch (NullPointerException e) {
			alert.showAndWait();
			return;
		}

		LocalVacina LocalVac = new LocalVacina();
		Endereco EndVac = new Endereco();
		Vacina Vac = new Vacina();
		Vac.setNomeVacina(random());
		EndVac.setCidade(cidadeField.getText());
		EndVac.setEstado(ufSelect.getValue());
		LocalVac.setEnderecoVacina(EndVac);
		RegistroVacina cadastro = new RegistroVacina(MainTelas.pessoaLogada, Vac, dtVac.getValue(), LocalVac, grupoSelect.getValue());
		MainTelas.registroVacinaLogado = cadastro;
		MainTelas.registroController.addRegistroVacina(cadastro);
		MainTelas.registroVacinaLogado.setDose(0);
		MainTelas.registroController.salvarArquivo();

		MainTelas.changeScreen("status");
		System.out.println(cadastro);
	}
}
