package ruraldevs.gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.concurrent.Task;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
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
	private BorderPane paneBorderPane;
	@FXML
	private ComboBox<String> comboBEstados;
	@FXML
	private ComboBox<String> comboBCidades;
	@FXML
	private ComboBox<String> comboBPeriodo;
	@FXML
	private Button btnCarregar;
	@FXML
	private Button btnCheckAtt;
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
	@FXML
	private Hyperlink backButton;

	private LocalDate dataFinal = LocalDate.now();
	private LocalDate dataInicial = dataFinal.minus(7, ChronoUnit.DAYS);
	private static Scene telaSelecionarDataScene;

	private XYChart.Series<String, Number> novosCasosSeries = new XYChart.Series<String, Number>();
	private XYChart.Series<String, Number> totalCasosSeries = new XYChart.Series<String, Number>();
	private XYChart.Series<String, Number> novasMortesSeries = new XYChart.Series<String, Number>();
	private XYChart.Series<String, Number> totalMortesSeries = new XYChart.Series<String, Number>();

	@FXML
	public void backButtonPressed(ActionEvent event) {
		MainTelas.changeScreen("main");
		chartNovosCasos.getData().clear();
		chartTotalCasos.getData().clear();
		chartNovasMortes.getData().clear();
		chartTotalMortes.getData().clear();
	}


	@FXML
	public void calcularPeriodo() {
		btnCarregar.setDisable(false);
		switch (comboBPeriodo.getValue()) {
			case "1 Semana":
				dataFinal = LocalDate.now();
				dataInicial = dataFinal.minus(7, ChronoUnit.DAYS);
				break;
			case "2 Semanas":
				dataFinal = LocalDate.now();
				dataInicial = dataFinal.minus(14, ChronoUnit.DAYS);
				break;
			case "1 mês":
				dataFinal = LocalDate.now();
				dataInicial = dataFinal.minus(1, ChronoUnit.MONTHS);
				break;
			case "Todo período":
				dataFinal = LocalDate.now();
				dataInicial = LocalDate.of(2020, 02, 25);
				break;
			case "Personalizado":
				try {
					FXMLLoader fxmlTelaSelecionarData = new FXMLLoader(getClass().getResource("/ruraldevs/gui/fxml/telaSelecionarData.fxml"));
					fxmlTelaSelecionarData.setController(TelaSelecionarDataController.getInstance());
					telaSelecionarDataScene = new Scene(fxmlTelaSelecionarData.load(), 300, 200);
					Stage stage = new Stage();
					stage.setTitle("Selecione um período personalizado");
					stage.setScene(telaSelecionarDataScene);
					stage.getIcons().add(new Image("/ruraldevs/gui/assets/icone.png"));
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.showAndWait();
					dataInicial = TelaSelecionarDataController.getInstance().getDatePInicial().getValue();
					dataFinal = TelaSelecionarDataController.getInstance().getDatePFinal().getValue();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				dataInicial = dataFinal.minus(7, ChronoUnit.DAYS);
				break;
		}
	}

	@FXML
	public void carregarGraficos() {
		btnCarregar.setDisable(true);
		try {
			novosCasosSeries.getData().clear();
			totalCasosSeries.getData().clear();
			novasMortesSeries.getData().clear();
			totalMortesSeries.getData().clear();

			if (chartNovosCasos.getData().size() == 0) {
				chartNovosCasos.getData().add(novosCasosSeries);
			}
			if (chartTotalCasos.getData().size() == 0) {
				chartTotalCasos.getData().add(totalCasosSeries);
			}
			if (chartNovasMortes.getData().size() == 0) {
				chartNovasMortes.getData().add(novasMortesSeries);
			}
			if (chartTotalMortes.getData().size() == 0) {
				chartTotalMortes.getData().add(totalMortesSeries);
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY");

			List<RegistroCasos> listaRegistrosCasos = MainTelas.registrosCasosController.filtrar(comboBEstados.getValue(), comboBCidades.getValue(), dataInicial, dataFinal);

			for (RegistroCasos registroCasos : listaRegistrosCasos) {
				novosCasosSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroDeNovosCasos()));
				totalCasosSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroTotalDeCasos()));
				novasMortesSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroDeNovasMortes()));
				totalMortesSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroTotalDeMortes()));
			}

			lblLastDate.setText(String.format("Últimas informações recebidas na data: %s", formatter.format(this.getLastDate(listaRegistrosCasos))));

		} catch (DataInicialAposFinalException exception) {
			Stage stage = (Stage) paneBorderPane.getScene().getWindow();

			Alert alert = new Alert(AlertType.ERROR, "");

			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);

			alert.getDialogPane().setHeaderText("Datas inválidas!");
			alert.getDialogPane().setContentText(String.format("%s\nPor favor insira datas válidas.", exception.getMessage()));
			alert.showAndWait();
		} catch (DadosNaoEncontradosException exception) {
			Stage stage = (Stage) paneBorderPane.getScene().getWindow();

			Alert alert = new Alert(AlertType.WARNING, "");

			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);

			alert.getDialogPane().setHeaderText("Dados não encontrados!");
			alert.getDialogPane().setContentText(exception.getMessage());
			alert.showAndWait();
		} catch (NullPointerException exception) {
			Stage stage = (Stage) paneBorderPane.getScene().getWindow();

			Alert alert = new Alert(AlertType.WARNING, "");

			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);

			alert.getDialogPane().setHeaderText("Preencha os dados necessários!");
			alert.getDialogPane().setContentText("Por favor, preencha pelo menos o estado para carregar os dados.");
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

	@FXML
	public void checarAtualizacoes() {
		Stage stage = (Stage) paneBorderPane.getScene().getWindow();

		if (MainTelas.registrosCasosController.checarAtualizacoes()) {
			ButtonType sim = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
			ButtonType nao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

			Alert alert = new Alert(AlertType.WARNING, "", sim, nao);

			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);

			alert.getDialogPane().setHeaderText("Há uma nova atualização!");
			alert.getDialogPane().setContentText("Há uma nova atualização dos dados, deseja baixar?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == sim) {
				Task<Void> baixarTask = new Task<Void>() {
					@Override
					public Void call() {
						MainTelas.registrosCasosController.baixarArquivo();
						return null;
					}
				};
				Task<Void> extrairTask = new Task<Void>() {
					@Override
					public Void call() {
						MainTelas.registrosCasosController.extrairArquivo();
						return null;
					}
				};
				extrairTask.setOnSucceeded(e -> {
					Alert atualizadoAlert = new Alert(AlertType.INFORMATION, "");

					atualizadoAlert.initModality(Modality.APPLICATION_MODAL);
					atualizadoAlert.initOwner(stage);

					atualizadoAlert.getDialogPane().setHeaderText("Dados atualizados!");
					atualizadoAlert.getDialogPane().setContentText("Os dados foram atualizados, reinicie o programa para carrega-los.");

					atualizadoAlert.showAndWait();
				});
				baixarTask.setOnSucceeded(e -> {
					new Thread(extrairTask).start();
				});
				new Thread(baixarTask).start();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);
			alert.getDialogPane().setHeaderText("Não há uma nova atualização!");
			alert.getDialogPane().setContentText("Não há nenhuma nova atualização dos dados!");
			alert.showAndWait();
		}
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
		btnCarregar.setDisable(false);
		EstadosEnum estado = EstadosEnum.PE;
		if (comboBEstados.getValue().equals("Brasil")) {
			return;
		}

		for (EstadosEnum estadoE : EstadosEnum.values()) {
			if (estadoE.getNomeEstado().equals(comboBEstados.getValue())) {
				estado = estadoE;
				break;
			}
		}

		comboBCidades.getItems().clear();
		comboBCidades.setDisable(false);
		comboBCidades.getItems().add(null);
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(String.format("./src/ruraldevs/data/estados/%s.json", estado.name()))) {
			try {
				Object obj = parser.parse(reader);
				JSONObject estadoJson = (JSONObject) obj;
				JSONArray cidadesArray = (JSONArray) estadoJson.get("cidades");
				comboBCidades.getItems().addAll(cidadesArray);
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
		comboBEstados.setPromptText("Estado");
		comboBEstados.getItems().add("Brasil");
		comboBEstados.getItems().addAll(Arrays.stream(EstadosEnum.values()).map(e -> e.getNomeEstado()).collect(Collectors.toList()));

		comboBCidades.setOnAction(e -> {
			btnCarregar.setDisable(false);
		});
		comboBCidades.getItems().add("Escolha um estado");
		comboBCidades.setValue("Escolha um estado");
		comboBCidades.setDisable(true);

		String[] opcoesPeriodo = {"1 Semana", "2 Semanas", "1 mês", "Todo período", "Personalizado"};
		comboBPeriodo.getItems().addAll(opcoesPeriodo);
		comboBPeriodo.setValue("1 Semana");

		btnCarregar.setDisable(true);

		novosCasosSeries.setName("Número de casos");
		totalCasosSeries.setName("Número total de casos");
		novasMortesSeries.setName("Número de mortes");
		totalMortesSeries.setName("Número total de mortes");

		chartNovosCasos.getData().add(novosCasosSeries);
		chartTotalCasos.getData().add(totalCasosSeries);
		chartNovasMortes.getData().add(novasMortesSeries);
		chartTotalMortes.getData().add(totalMortesSeries);
	}
}
