package ruraldevs.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ruraldevs.beans.Pessoa;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.controller.PessoaController;
import ruraldevs.controller.RegistroVacinaController;
import ruraldevs.controller.RegistrosCasosController;

public class MainTelas extends Application {
	public static RegistroVacinaController registroController = new RegistroVacinaController();
	public static Pessoa pessoaLogada = new Pessoa();
	public static RegistroVacina registroVacinaLogado = new RegistroVacina();
	public static PessoaController pessoaController = new PessoaController();
	public static RegistrosCasosController registrosCasosController = new RegistrosCasosController();
	private static Stage stg;
	private static Scene mainScene;
	private static Scene cadasScene;
	private static Scene agendaScene;
	private static Scene statusScene;
	private static Scene dadosScene;
	private static Parent fxmlStatus;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stg = primaryStage;

		Parent fxmlMain = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/fxml/sample.fxml"));
		mainScene = new Scene(fxmlMain);

		Parent fxmlCadas = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/fxml/cadassample.fxml"));
		cadasScene = new Scene(fxmlCadas);

		Parent fxmlAgenda = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/fxml/agendasample.fxml"));
		agendaScene = new Scene(fxmlAgenda);

		Parent fxmlTelaDados = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/fxml/telaDados.fxml"));
		dadosScene = new Scene(fxmlTelaDados);

		primaryStage.setScene(mainScene);

		primaryStage.setTitle("VacinaBrasil");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/ruraldevs/gui/assets/icone.png"));
		primaryStage.show();
	}

	public static void changeScreen(String scr) {
		switch (scr) {
			case "main":
				stg.setScene(mainScene);
				break;
			case "cadas":
				stg.setScene(cadasScene);
				break;
			case "agenda":
				stg.setScene(agendaScene);
				break;
			case "status":
				try {
					fxmlStatus = FXMLLoader.load(MainTelas.class.getResource("/ruraldevs/gui/fxml/statussample.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				statusScene = new Scene(fxmlStatus);
				stg.setScene(statusScene);
				break;
			case "dados":
				stg.setScene(dadosScene);
				break;
			default:
				stg.setScene(mainScene);
				break;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
