package ruraldevs.gui;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ruraldevs.beans.Pessoa;
import ruraldevs.beans.RegistroVacina;

public class LoginController {
	@FXML
	Button bttnLogin;
	@FXML
	Button bttnDados;
	@FXML
	Hyperlink bttnCadas;
	@FXML
	TextField login;
	@FXML
	PasswordField senha;

	@FXML
	public void bttnLoginPressed(ActionEvent event) {
		if (login(login.getText(), senha.getText())) {
			return;
		}
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Usuário ou senha incorretos");
		alert.setHeaderText("Dados vazios ou incorretos");
		alert.setContentText("Por favor, preencha os campos novamente com as informações corretas.");
		alert.showAndWait();
	}

	private boolean login(String cpf, String senha) {
		for (Pessoa pessoa : MainTelas.pessoaController.getPessoas()) {
			if (pessoa.getCpf().equals(cpf)) {
				if (pessoa.getSenha().equals(senha)) {
					System.out.println("entrou");
					MainTelas.pessoaLogada = pessoa;
					System.out.println(MainTelas.registroController.getRegistrosVacinas());
					System.out.println(MainTelas.pessoaController.getPessoas());

					if (MainTelas.registroController.getRegistrosVacinas().size() == 0) {
						MainTelas.changeScreen("agenda");
						return true;
					}

					for (RegistroVacina registro : MainTelas.registroController.getRegistrosVacinas()) {
						// System.out.println("aa");
						if (pessoa.equals(registro.getPessoa())) {
							if (registro.getDataDaVacina().isAfter(LocalDate.now())) {
								MainTelas.registroVacinaLogado = registro;
								// System.out.println("foi");
								MainTelas.changeScreen("status");
								return true;
							}
						}
					}
					MainTelas.changeScreen("agenda");
					// System.out.println("n foi");
					return true;
				}
			}
		}
		return false;
	}

	@FXML
	public void bttnCadasPressed(ActionEvent event) {
		MainTelas.changeScreen("cadas");
	}

	@FXML
	public void bttnDadosPressed(ActionEvent event) {
		MainTelas.changeScreen("dados");
	}
}
