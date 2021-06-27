package ruraldevs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ruraldevs.beans.Pessoa;

public class loginController {
	@FXML
	Button bttnlogin;
	@FXML
	Button bttndados;
	@FXML
	Hyperlink bttncadas;
	@FXML
	TextField login;
	@FXML
	PasswordField senha;

	@FXML
	public void bttnlognpressed(ActionEvent event) {
		if (login(login.getText(), senha.getText())) {
			return;
		}
		System.out.println("Usuario ou senha incorretos");
	}

	private boolean login(String cpf, String senha) {
		for (Pessoa pessoa1 : mainTelas.pessoaController.getPessoas()) {
			if (pessoa1.getCpf().equals(cpf)) {
				if (pessoa1.getSenha().equals(senha)) {
					mainTelas.changeScreen("alogin");
					System.out.println("entrou");
					mainTelas.pessoaLogada = pessoa1;
					return true;
				}
			}
		}
		return false;
	}

	@FXML
	public void bttncadaspressed(ActionEvent event) {
		mainTelas.changeScreen("cadas");
		System.out.println("aa3");
	}

	@FXML
	public void bttndadospressed(ActionEvent event) {
		System.out.println("bb1");
		mainTelas.changeScreen("dados");
	}
}
