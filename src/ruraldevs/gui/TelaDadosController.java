package ruraldevs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.RegistroCasos;
import ruraldevs.controller.EstadosController;
import ruraldevs.exceptions.DataInicialAposFinalException;
import ruraldevs.service.RegistrosCasosService;

public class TelaDadosController implements Initializable {
    private RegistrosCasosService registrosCasosService;
    @FXML
    private BorderPane paneBorderPane;
    @FXML
    private ChoiceBox<String> cbEstados;
    @FXML
    private ChoiceBox<String> cbCidades;
    @FXML
    private DatePicker datepInicial;
    @FXML
    private DatePicker datepFinal;
    @FXML
    private Button btnCarregar;
    @FXML
    private LineChart<String, Number> chartNovosCasos;
    @FXML
    private LineChart<String, Number> chartTotalCasos;
    @FXML
    private LineChart<String, Number> chartNovasMortes;
    @FXML
    private LineChart<String, Number> chartTotalMortes;
    @FXML
    private Label lblLastDate;

    @FXML
    public void carregarGraficos() {
        try {
            chartNovosCasos.getData().clear();
            chartTotalCasos.getData().clear();
            chartNovasMortes.getData().clear();
            chartTotalMortes.getData().clear();

            XYChart.Series<String, Number> novosCasosSeries = new XYChart.Series<String, Number>();
            XYChart.Series<String, Number> totalCasosSeries = new XYChart.Series<String, Number>();
            XYChart.Series<String, Number> novasMortesSeries = new XYChart.Series<String, Number>();
            XYChart.Series<String, Number> totalMortesSeries = new XYChart.Series<String, Number>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY");

            List<RegistroCasos> lista = registrosCasosService.filtrar(cbEstados.getValue(), cbCidades.getValue(), datepInicial.getValue(), datepFinal.getValue());
            for (RegistroCasos registroCasos : lista) {
                novosCasosSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroDeNovosCasos()));
                novosCasosSeries.setName("Número de casos");
                totalCasosSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroTotalDeCasos()));
                totalCasosSeries.setName("Número total de casos");
                novasMortesSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroDeNovasMortes()));
                novasMortesSeries.setName("Número de mortes");
                totalMortesSeries.getData().add(new XYChart.Data<String, Number>(formatter.format(registroCasos.getData()), registroCasos.getNumeroTotalDeMortes()));
                totalMortesSeries.setName("Número total de mortes");
            }
            chartNovosCasos.getData().add(novosCasosSeries);
            chartTotalCasos.getData().add(totalCasosSeries);
            chartNovasMortes.getData().add(novasMortesSeries);
            chartTotalMortes.getData().add(totalMortesSeries);

            lblLastDate.setText(String.format("Última informação recebida na data: %s", formatter.format(this.getLastDate(lista))));

        } catch (DataInicialAposFinalException e) {
            Stage stage = (Stage) paneBorderPane.getScene().getWindow();

            Alert alert = new Alert(AlertType.ERROR, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Você colocou a data inicial depois da final.");
            alert.getDialogPane().setHeaderText("Datas inválidas!");
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
        String estado = cbEstados.getValue();
        if (estado.equals("Brasil")) {
            return;
        }
        cbCidades.getItems().clear();
        cbCidades.setDisable(false);
        EstadosController estadosController = new EstadosController();
        cbCidades.getItems().addAll(estadosController.getCidadesPorEstado(estado));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registrosCasosService = new RegistrosCasosService();
        datepFinal.setValue(LocalDate.now());
        datepInicial.setValue(LocalDate.of(2020, 02, 25));
        cbCidades.getItems().add("Escolha um estado");
        cbCidades.setValue("Escolha um estado");
        cbCidades.setDisable(true);
        cbEstados.getItems().add("Brasil");
        for (EstadosEnum estado : EstadosEnum.values()) {
            cbEstados.getItems().add(estado.getNomeEstado());
        }
    }
}
