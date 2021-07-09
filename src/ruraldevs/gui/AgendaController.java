package ruraldevs.gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import ruraldevs.beans.CentrosEnum;
import ruraldevs.beans.DrivesEnum;
import ruraldevs.beans.Endereco;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.GrupoEnum;
import ruraldevs.beans.LocalVacina;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.beans.Vacina;
import ruraldevs.beans.VacinasEnum;

public class AgendaController implements Initializable {
    public static int a = 0;
    @FXML
    Hyperlink voltarbttn;
    @FXML
    TextField ceptext;
    @FXML
    Button bttnagendar;
    @FXML
    DatePicker dtVac;
    @FXML
    ComboBox<String> centros = new ComboBox<>();
    @FXML
    RadioButton driveselect;
    @FXML
    ComboBox<String> cidadeselect = new ComboBox<>();
    @FXML
    ChoiceBox<GrupoEnum> gruposelect = new ChoiceBox<>();
    @FXML
    ComboBox<EstadosEnum> ufselect = new ComboBox<>();

    @FXML
    public void voltarpressed(ActionEvent event) {
       	  Alert alert = new Alert(AlertType.CONFIRMATION);
  	alert.setTitle("Deseja sair? ");
  	alert.setHeaderText("Deseja voltar para tela inicial?");
  	alert.setContentText("Sair irá interromper o processo de agendamento, sua conta ja foi registrada com sucesso"); 	 
  	alert.showAndWait().ifPresent(response -> {
  	     if (response == ButtonType.OK) {
  	    	 MainTelas.changeScreen("main");
  	    	 ufselect.getItems().add(null);
  		    cidadeselect.setValue(null);
  		    ceptext.clear();
  			dtVac.setValue(null);
  			gruposelect.setValue(null);
  			ufselect.setValue(null);
  			centros.setValue(null);
  	     }
  	 });
	  
    }

    public static void cepField(TextField textField) {
        maxField(textField, 9);
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
                    textField.setText(value);
                }
            }
        });
    }

    private static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > length)
                    textField.setText(oldValue);
            }
        });
    }

    @FXML
    public void preencherDrives(ActionEvent event) {
        if (driveselect.isSelected()) {
            centros.getItems().clear();
            centros.getItems().addAll(DrivesEnum.names());
        }

        else {
            centros.getItems().clear();
            centros.getItems().addAll(CentrosEnum.names());
        }
    }

    public void preencherCidades(ActionEvent event) {
        EstadosEnum estado = ufselect.getValue();
        cidadeselect.getItems().clear();
        cidadeselect.setDisable(false);
        cidadeselect.getItems().add(null);
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(String.format("./src/ruraldevs/data/estados/%s.json", estado.name()))) {
            try {
                Object obj = parser.parse(reader);
                JSONObject estadoJson = (JSONObject) obj;
                JSONArray cidadesArray = (JSONArray) estadoJson.get("cidades");
                cidadeselect.getItems().addAll(cidadesArray);
            } catch (ParseException e) {
                System.out.println("PARSE EXCEPTION");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }

    @FXML
    public void preencherCentros(ActionEvent event) {
        try {
            if (cidadeselect.getValue().equals("Recife")) {
                centros.getItems().clear();
                centros.getItems().addAll(CentrosEnum.names());
                centros.setDisable(false);
                driveselect.setDisable(false);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cidadeselect.getItems().add("Escolha um estado");
        cidadeselect.setValue("Escolha um estado");
        cidadeselect.setDisable(true);
        centros.getItems().add("Escolha uma cidade");
        centros.setValue("Escolha uma cidade");
        centros.setDisable(true);
        driveselect.setDisable(true);

        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(LocalDate.now().plusDays(1)) || item.isAfter(LocalDate.now().plusMonths(4))) {
                    setStyle("-fx-background-color: #EBEBEB; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
            }
        };
        dtVac.setDayCellFactory(dayCellFactory);
        cepField(ceptext);
        gruposelect.setItems(FXCollections.observableArrayList(GrupoEnum.values()));
        ufselect.setItems(FXCollections.observableArrayList(EstadosEnum.values()));
    }

    private static final List<VacinasEnum> VALUES = Collections.unmodifiableList(Arrays.asList(VacinasEnum.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static VacinasEnum random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @FXML
    public void bttnagendarpressed(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Dados incompletos");
        alert.setHeaderText("Dados vazios ou incorretos");
        alert.setContentText("Por favor, preencha os campos novamente com as informações corretas.");

        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Idade Insuficiente");
        alerta.setHeaderText("A idade informada não condiz com a idade do grupo atual");
        alerta.setContentText("Por favor, verifique as informações em relação aos grupos selecionados para a vacinação.");

        try {
            if (cidadeselect.getValue().equals(null) || ceptext.getText().isEmpty() || dtVac.getValue().equals(null) || gruposelect.getValue().equals(null) || ufselect.getValue().equals(null)) {
                alert.showAndWait();
                return;
            }
        } catch (NullPointerException e) {
            alert.showAndWait();
            return;
        }
        MainTelas.pessoaLogada.setGrupo(gruposelect.getValue());

        if (MainTelas.pessoaLogada.getGrupo().equals(GrupoEnum.PESSOAS_37_OU_MAIS) && MainTelas.pessoaLogada.calcularIdade() < 37) {
            alerta.showAndWait();
            return;
        }

        LocalVacina LocalVac = new LocalVacina();
        Endereco EndVac = new Endereco();
        Vacina Vac = new Vacina();
        Vac.setNomeVacina(random());
        EndVac.setCidade(cidadeselect.getValue());
        EndVac.setCep(ceptext.getText());
        EndVac.setEstado(ufselect.getValue());
        LocalVac.setEnderecoVacina(EndVac);
        LocalVac.setPostoVacinacao(centros.getValue());

        RegistroVacina cadastro = new RegistroVacina(MainTelas.pessoaLogada, Vac, dtVac.getValue(), LocalVac, gruposelect.getValue());
        RegistroVacina cadastro2dose = new RegistroVacina(MainTelas.pessoaLogada, Vac, (LocalDate) dtVac.getValue().plusDays(90), LocalVac, gruposelect.getValue());
        if (dtVac.getValue().isBefore(LocalDate.now()) || dtVac.getValue().isEqual(LocalDate.now())) {
            MainTelas.registroVacinaLogado = cadastro2dose;
        } else {
            MainTelas.registroVacinaLogado = cadastro;
        }
        cadastro.setDose(1);
        cadastro2dose.setDose(2);
        MainTelas.registroController.addRegistroVacina(cadastro);
        MainTelas.registroController.addRegistroVacina(cadastro2dose);
        MainTelas.registroController.salvarArquivo();
        MainTelas.pessoaController.salvar();
        MainTelas.changeScreen("status");
        System.out.println(cadastro);
    }
}
