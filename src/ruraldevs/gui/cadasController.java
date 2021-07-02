package ruraldevs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import ruraldevs.beans.Pessoa;
import ruraldevs.controller.PessoaController;

public class cadasController implements Initializable {
	@FXML
	Hyperlink bttnvoltar;
	@FXML
	Button bttncadas;
	@FXML
	PasswordField cxsenha;
	@FXML
	TextField cxnome;
	@FXML
	TextField cxcpf;
	@FXML
	DatePicker datanasc;

	@FXML
	public void bttnvoltarpressed(ActionEvent event) {
		mainTelas.changeScreen("main");
		cxnome.clear();
		cxcpf.clear();
		cxsenha.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (item.isAfter(LocalDate.now())) {
					setStyle("-fx-background-color: #EBEBEB; -fx-text-fill: darkgray;");
					setDisable(true);
				}
			}
		};
		datanasc.setDayCellFactory(dayCellFactory);
	}

	@FXML
	public void bttncadaspressed(ActionEvent event) throws NullPointerException {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Dados incompletos");
		alert.setHeaderText("Dados vazios ou incorretos");
		alert.setContentText("Por favor, preencha os campos novamente com as informa��es corretas.");

		try {
			if (cxnome.getText().isEmpty() || cxcpf.getText().isEmpty() || datanasc.getValue().equals(null) || cxsenha.getText().isEmpty()) {
				System.out.println("preencha todos os dados");
				alert.showAndWait();
				return;
			}
		} catch (NullPointerException e) {

			alert.showAndWait();
			return;
		}
		PessoaController a = new PessoaController();

		Pessoa nome = new Pessoa(cxnome.getText(), cxcpf.getText(), datanasc.getValue(), cxsenha.getText());
		a.addPessoa(nome);
		((PessoaController) a).salvar();
		mainTelas.pessoaLogada = nome;
		System.out.println(a.getPessoas());

		mainTelas.changeScreen("agenda");
	}
}
