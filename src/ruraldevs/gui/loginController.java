package ruraldevs.gui;


import ruraldevs.beans.*;
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
			for (Pessoa pessoa : mainTelas.pessoaController.getPessoas()) {
				if (pessoa.getCpf().equals(cpf)) {
					if (pessoa.getSenha().equals(senha)) {
						System.out.println("entrou");
						mainTelas.pessoaLogada = pessoa;
						
						
						System.out.println(mainTelas.registroController.getRegistrosVacinas());
						for(RegistroVacina registro:mainTelas.registroController.getRegistrosVacinas()) {
							System.out.println("aa");
						if(pessoa.equals(registro.getPessoa())){
						mainTelas.registroVacinaLogado=registro;
						System.out.println("foi");
						mainTelas.changeScreen("status");}
						else {mainTelas.changeScreen("agenda");
						System.out.println("n foi");}
						
						}
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
	   // mainTelas.changeScreen("dados");

	  }

	}