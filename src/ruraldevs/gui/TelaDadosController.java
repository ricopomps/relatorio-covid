package ruraldevs.gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.RegistroCasos;
import ruraldevs.exceptions.DadosNaoEncontradosException;
import ruraldevs.exceptions.DataInicialAposFinalException;

public class TelaDadosController implements Initializable {
	@FXML
	private Hyperlink backbutton;
	@FXML
	private BorderPane paneBorderPane;
	@FXML
	private ChoiceBox<beans.EstadosEnum> choiceBEstados;
	@FXML
	private ChoiceBox<String> choiceBCidades;
	@FXML
	private ChoiceBox<String> choiceBPeriodo;
	@FXML
	private Button btnCarregar;
	@FXML
	private BarChart<String, Number> chartNovosCasos;
	@FXML
	private LineChart<String, Number> chartTotalCasos;
	@FXML
	private BarChart<String, Number> chartNovasMortes;
	@FXML
	private LineChart<String, Number> chartTotalMortes;
	@FXML
	private Label lblLastDate;
	private LocalDate dataFinal = LocalDate.now(), dataInicial = dataFinal.minus(7, ChronoUnit.DAYS);
	private static Scene telaSelecionarDataScene;

	public void backbttnprsd(ActionEvent event) {
		 mainTelas.changeScreen("main");
		chartNovosCasos.getData().clear();
		chartTotalCasos.getData().clear();
		chartNovasMortes.getData().clear();
		chartTotalMortes.getData().clear();
		  
	}
	
	
	
	@FXML
	public void calcularPeriodo() {
		switch (choiceBPeriodo.getValue()) {
			case "1 Semana":
				dataInicial = dataFinal.minus(7, ChronoUnit.DAYS);
				break;
			case "2 Semanas":
				dataInicial = dataFinal.minus(14, ChronoUnit.DAYS);
				break;
			case "1 mês":
				dataInicial = dataFinal.minus(1, ChronoUnit.MONTHS);
				break;
			case "Todo período":
				dataInicial = LocalDate.of(2020, 02, 25);
				break;
			case "Personalizado":
				try {
					FXMLLoader fxmlTelaSelecionarData = new FXMLLoader(getClass().getResource("/ruraldevs/gui/telaSelecionarData.fxml"));
					fxmlTelaSelecionarData.setController(TelaSelecionarDataController.getInstance());
					telaSelecionarDataScene = new Scene(fxmlTelaSelecionarData.load(), 300, 200);
					Stage stage = new Stage();
					stage.setTitle("Selecione um perioodo personalizado");
					stage.setScene(telaSelecionarDataScene);
					stage.getIcons().add(new Image("/ruraldevs/gui/icone.png"));
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				dataInicial = dataFinal.minus(7, ChronoUnit.DAYS);
				break;
		}
	}

	public void handleDatasSelecionadasEvent(DatasSelecionadasEvent event) {
		dataInicial = event.getDataInicial();
		dataFinal = event.getDataFinal();
	}

	@FXML
	public void carregarGraficos() {
		try {
			chartNovosCasos.getData().clear();
			chartTotalCasos.getData().clear();
			chartNovasMortes.getData().clear();
			chartTotalMortes.getData().clear();

			XYChart.Series<String, Number> novosCasosSeries = new XYChart.Series<String, Number>();
			novosCasosSeries.setName("Numero de casos");
			XYChart.Series<String, Number> totalCasosSeries = new XYChart.Series<String, Number>();
			totalCasosSeries.setName("Numero total de casos");
			XYChart.Series<String, Number> novasMortesSeries = new XYChart.Series<String, Number>();
			novasMortesSeries.setName("Numero de mortes");
			XYChart.Series<String, Number> totalMortesSeries = new XYChart.Series<String, Number>();
			totalMortesSeries.setName("Numero total de mortes");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY");


			List<RegistroCasos> listaRegistrosCasos = mainTelas.registrosCasosController.filtrar(choiceBEstados.getValue().getNomeEstado(), choiceBCidades.getValue(), dataInicial, dataFinal);

			for (RegistroCasos registroCasos : listaRegistrosCasos) {
				novosCasosSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroDeNovosCasos()));
				totalCasosSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroTotalDeCasos()));
				novasMortesSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroDeNovasMortes()));
				totalMortesSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroTotalDeMortes()));
			}
			chartNovosCasos.getData().add(novosCasosSeries);
			chartTotalCasos.getData().add(totalCasosSeries);
			chartNovasMortes.getData().add(novasMortesSeries);
			chartTotalMortes.getData().add(totalMortesSeries);

			lblLastDate.setText(String.format("As ultimas informacoes foram recebidas na data: %s", formatter.format(this.getLastDate(listaRegistrosCasos))));

		} catch (DataInicialAposFinalException exception) {
			Stage stage = (Stage) paneBorderPane.getScene().getWindow();

			Alert alert = new Alert(AlertType.ERROR, "haha");

			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);

			alert.getDialogPane().setContentText("Voce colocou a data inicial depois da final.");
			alert.getDialogPane().setHeaderText("Datas invalidas!");
			alert.showAndWait();
		}
	}

	public LocalDate getLastDate(List<RegistroCasos> listaRegistroCasos) {
		LocalDate lastDate = LocalDate.of(2020, 02, 01);
		for (int i = listaRegistroCasos.size() - 1; i >= 0; i--) {
			if (listaRegistroCasos.get(i).getUltimaData().isAfter(lastDate)) {
				lastDate = listaRegistroCasos.get(i).getUltimaData();
			}
		}
		return lastDate;
	}

	public LocalDate getFirstDate(List<RegistroCasos> listaRegistroCasos) {
		LocalDate firstDate = LocalDate.now();
		for (int i = listaRegistroCasos.size() - 1; i >= 0; i--) {
			if (listaRegistroCasos.get(i).getData().isBefore(firstDate)) {
				firstDate = listaRegistroCasos.get(i).getData();
			}
		}
		return firstDate;
	}

	@FXML
	public void preencherCidades(ActionEvent event) {
		beans.EstadosEnum estado = choiceBEstados.getValue();
		if (estado.equals("Brasil")) {
			return;
		}
		choiceBCidades.getItems().clear();
		choiceBCidades.setDisable(false);
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(String.format("./src/ruraldevs/data/estados/%s.json", estado.name()))) {
			try {
				Object obj = parser.parse(reader);
				JSONObject estadoJson = (JSONObject) obj;
				JSONArray cidadesArray = (JSONArray) estadoJson.get("cidades");
				choiceBCidades.getItems().addAll(cidadesArray);
			} catch (ParseException e) {
				System.out.println("PARSE EXCEPTION");
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		paneBorderPane.addEventFilter(DatasSelecionadasEvent.ANY, this::handleDatasSelecionadasEvent);
		choiceBCidades.getItems().add("Escolha um estado");
		choiceBCidades.setValue("Escolha um estado");
		choiceBCidades.setDisable(true);
		String[] opcoesPeriodo = {"1 Semana", "2 Semanas", "1 mes", "Todo periodo", "Personalizado"};
		choiceBPeriodo.getItems().addAll(opcoesPeriodo);
		choiceBPeriodo.setValue("1 Semana");
		choiceBEstados.getItems().addAll(beans.EstadosEnum.values());
	}
}
