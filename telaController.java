package gui;


import Beans.Pessoa;
import Repository.PessoaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class telaController {

	
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

	
		
		for (Pessoa pessoa1 : PessoaRepository.getInstance().getDados()) {
		 
			if(pessoa1.getCpf().equals(login.getText())) {
				
				if(pessoa1.getSenha().equals(senha.getText())) {
					mainTelas.changeScreen("alogin");System.out.println("entrou"); return;}				
			         
			         
			}
		}
		
		
		System.out.println("Usuario ou senha incorretos");
	}
	
	@FXML
	public void bttncadaspressed(ActionEvent event) {
		
		mainTelas.changeScreen("cadas");
		System.out.println("aa3");
		
	}
	
	@FXML
	public void bttndadospressed(ActionEvent event) {
		
		System.out.println("bb1");
		mainTelas.changeScreen("agenda");
		
		
	}

	
}
