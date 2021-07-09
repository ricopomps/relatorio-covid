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
	private static Parent fxmlCadas;
	private static Parent fxmlAgenda;
	private static Parent fxmlTelaDados;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stg = primaryStage;

		Parent fxmlMain = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/fxml/sample.fxml"));
		mainScene = new Scene(fxmlMain);

		primaryStage.setScene(mainScene);

		primaryStage.setTitle("VacinaBrasil");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/ruraldevs/gui/assets/icone.png"));
		primaryStage.show();
	}

	public static void changeScreen(String scr) {
		try {
			switch (scr) {
			case "main":
				stg.setScene(mainScene);
				break;
			case "cadas":
				Parent fxmlCadas;
				fxmlCadas = FXMLLoader.load(MainTelas.class.getResource("/ruraldevs/gui/fxml/cadassample.fxml"));

				cadasScene = new Scene(fxmlCadas);
				stg.setScene(cadasScene);
				break;
			case "agenda":
				Parent fxmlAgenda = FXMLLoader
						.load(MainTelas.class.getResource("/ruraldevs/gui/fxml/agendasample.fxml"));
				agendaScene = new Scene(fxmlAgenda);
				stg.setScene(agendaScene);
				break;
			case "status":
				fxmlStatus = FXMLLoader.load(MainTelas.class.getResource("/ruraldevs/gui/fxml/statussample.fxml"));
				statusScene = new Scene(fxmlStatus);
				stg.setScene(statusScene);
				break;
			case "dados":
				Parent fxmlTelaDados = FXMLLoader
						.load(MainTelas.class.getResource("/ruraldevs/gui/fxml/telaDados.fxml"));
				dadosScene = new Scene(fxmlTelaDados);
				stg.setScene(dadosScene);
				break;
			default:
				stg.setScene(mainScene);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
