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

public class CadastroController implements Initializable {
	@FXML
	Hyperlink bttnVoltar;
	@FXML
	Button bttnCadas;
	@FXML
	PasswordField cxSenha;
	@FXML
	TextField cxNome;
	@FXML
	TextField cxCpf;
	@FXML
	DatePicker dataNasc;

	@FXML
	public void bttnvoltarpressed(ActionEvent event) {
		MainTelas.changeScreen("main");
		cxNome.clear();
		cxCpf.clear();
		cxSenha.clear();
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
		dataNasc.setDayCellFactory(dayCellFactory);
	}

	@FXML
	public void bttncadaspressed(ActionEvent event) throws NullPointerException {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Dados incompletos");
		alert.setHeaderText("Dados vazios ou incorretos");
		alert.setContentText("Por favor, preencha os campos novamente com as informações corretas.");

		try {
			if (cxNome.getText().isEmpty() || cxCpf.getText().isEmpty() || dataNasc.getValue().equals(null) || cxSenha.getText().isEmpty()) {
				System.out.println("preencha todos os dados");
				alert.showAndWait();
				return;
			}
		} catch (NullPointerException e) {
			alert.showAndWait();
			return;
		}
		PessoaController a = new PessoaController();

		Pessoa nome = new Pessoa(cxNome.getText(), cxCpf.getText(), dataNasc.getValue(), cxSenha.getText());
		a.addPessoa(nome);
		((PessoaController) a).salvar();
		MainTelas.pessoaLogada = nome;
		System.out.println(a.getPessoas());

		MainTelas.changeScreen("agenda");
	}
}
