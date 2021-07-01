package ruraldevs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import ruraldevs.gui.mainTelas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import ruraldevs.beans.Endereco;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.GrupoEnum;
import ruraldevs.beans.LocalVacina;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.beans.Vacina;
import ruraldevs.beans.VacinasEnum;

public class agendaController implements Initializable {

	  public static int a = 0;
	  @FXML
	  Hyperlink voltarbttn;
	  @FXML
	  TextField cidadefield;
	  @FXML
	  TextField ceptext;
	  @FXML
	  Button bttnagendar;
	  @FXML
	  DatePicker dtVac;

	  @FXML
	  ChoiceBox < GrupoEnum > gruposelect = new ChoiceBox < > ();
	  @FXML
	  ChoiceBox < EstadosEnum > ufselect = new ChoiceBox < > ();
	  @FXML
	  public void voltarpressed(ActionEvent event) {
	    mainTelas.changeScreen("main");
	  }

	  @Override
	  public void initialize(URL location, ResourceBundle resources) {
	    Callback < DatePicker, DateCell > dayCellFactory = dp -> new DateCell() {
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

	    gruposelect.setItems(FXCollections.observableArrayList(GrupoEnum.values()));
	    ufselect.setItems(FXCollections.observableArrayList(EstadosEnum.values()));
	  }

	  private static final List < VacinasEnum > VALUES = Collections.unmodifiableList(Arrays.asList(VacinasEnum.values()));
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
		  try {
		      if (cidadefield.getText().isEmpty() || ceptext.getText().isEmpty() || dtVac.getValue().equals(null) || gruposelect.getValue().equals(null)
		    		 || ufselect.getValue().equals(null)) {
		    	  alert.showAndWait();
		        return;
		      }
		    } catch (NullPointerException e) {
		    	alert.showAndWait();
		      return;
		    }
		 
	    LocalVacina LocalVac = new LocalVacina();
	    Endereco EndVac = new Endereco();
	    Vacina Vac = new Vacina();
	    Vac.setNomeVacina(random());
	    EndVac.setCidade(cidadefield.getText());
	    EndVac.setEstado(ufselect.getValue());
	    LocalVac.setEnderecoVacina(EndVac);
	    RegistroVacina cadastro = new RegistroVacina(mainTelas.pessoaLogada, Vac, dtVac.getValue(), LocalVac, gruposelect.getValue());
	    mainTelas.registroVacinaLogado = cadastro;
	    mainTelas.registroController.addRegistroVacina(cadastro);
	    mainTelas.registroVacinaLogado.setDose(0);
	    mainTelas.registroController.salvarArquivo();
	    
	    

	    mainTelas.changeScreen("status");
	    System.out.println(cadastro);

	  }

	}