package ruraldevs.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class mainTelas extends Application {
	private static Stage stg;
	private static Scene mainScene;
	private static Scene cadasScene;
	private static Scene agendaScene;
	private static Scene aLoginScene;
	private static Scene dadosScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stg = primaryStage;

		Parent fxmlMain = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/sample.fxml"));
		mainScene = new Scene(fxmlMain);

		Parent fxmlCadas = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/cadassample.fxml"));
		cadasScene = new Scene(fxmlCadas);

		Parent fxmlAgenda = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/agendasample.fxml"));
		agendaScene = new Scene(fxmlAgenda);

		Parent fxmlAfterLogin = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/aloginsample.fxml"));
		aLoginScene = new Scene(fxmlAfterLogin);

		Parent fxmlTelaDados = FXMLLoader.load(getClass().getResource("/ruraldevs/gui/telaDados.fxml"));
		dadosScene = new Scene(fxmlTelaDados);

		primaryStage.setScene(mainScene);

		primaryStage.setTitle("VacinaBrasil");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/ruraldevs/gui/icone.png"));
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
			case "alogin":
				stg.setScene(aLoginScene);
				break;
			case "dados":
				stg.setScene(dadosScene);
				break;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

