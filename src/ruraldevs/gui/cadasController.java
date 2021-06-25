package ruraldevs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ruraldevs.beans.Pessoa;
import ruraldevs.controller.PessoaController;

public class cadasController {
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
	}

	@FXML
	public void bttncadaspressed(ActionEvent event) throws NullPointerException {
		try {
			if (cxnome.getText().isEmpty() || cxcpf.getText().isEmpty() || datanasc.getValue().equals(null) || cxsenha.getText().isEmpty()) {
				System.out.println("preencha todos os dados");
				return;
			}
		} catch (NullPointerException e) {
			System.out.println("preencha todos os dados");
			return;
		}
		PessoaController a = new PessoaController();

		Pessoa nome = new Pessoa(cxnome.getText(), cxcpf.getText(), datanasc.getValue(), cxsenha.getText());
		a.addPessoa(nome);
		((PessoaController) a).salvar();

		System.out.println(a.getPessoas());

		mainTelas.changeScreen("agenda");
	}
}
