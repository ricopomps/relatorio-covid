package ruraldevs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.util.Callback;
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
  ComboBox<String>centros=new ComboBox< >();
  @FXML
  RadioButton driveselect;
  @FXML
  ComboBox <String> cidadeselect = new ComboBox < > ();
  @FXML
  ChoiceBox < GrupoEnum > gruposelect = new ChoiceBox < > ();
  @FXML
  ComboBox < EstadosEnum > ufselect = new ComboBox < > ();
  @FXML
  public void voltarpressed(ActionEvent event) {
	  
	  cidadeselect.setValue(null);
	  ceptext.clear();
	  dtVac.setValue(null);
	  gruposelect.setValue(null);
	  ufselect.setValue(null);
	  
    mainTelas.changeScreen("main");
    
  }
  
  public void preencherDrives(ActionEvent event) {
	  
	  if(driveselect.isSelected()) {
	  centros.getItems().clear();  
	  centros.getItems().addAll(DrivesEnum.names());}
	  
	  else {
		  
		  centros.getItems().clear();
		  centros.getItems().addAll(CentrosEnum.names());
		 }
 
	  
	  
  }
  public void preencherCidades(ActionEvent event) {
	  
	  EstadosEnum estado = ufselect.getValue();
	  if (estado.equals("Brasil")) {
		  return;
	  }
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
  
  
  public void preencherCentros(ActionEvent event) {

		try {
	 if(cidadeselect.getValue().equals("Recife")) {
		 centros.getItems().clear();
		 centros.getItems().addAll(CentrosEnum.names());
		 centros.setDisable(false);
		 driveselect.setDisable(false);
	  }  
		}catch(Exception e) {}
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
	  	alert.setContentText("Por favor, preencha os campos novamente com as informa��es corretas."); 
	  try {
	      if (cidadeselect.getValue().equals(null) || ceptext.getText().isEmpty() || dtVac.getValue().equals(null) || gruposelect.getValue().equals(null)
	    		 || ufselect.getValue().equals(null)) {
	    	  alert.showAndWait();
	        return;
	      }
	    } catch (NullPointerException e) {
	    	alert.showAndWait();
	      return;
	    }
	mainTelas.pessoaLogada.setGrupo(gruposelect.getValue()); 
    LocalVacina LocalVac = new LocalVacina();
    Endereco EndVac = new Endereco();
    Vacina Vac = new Vacina();
    Vac.setNomeVacina(random());
    EndVac.setCidade(cidadeselect.getValue());
    EndVac.setCep(ceptext.getText());
    EndVac.setEstado(ufselect.getValue());
    LocalVac.setEnderecoVacina(EndVac);
    LocalVac.setPostoVacinacao(centros.getValue());
    
    RegistroVacina cadastro = new RegistroVacina(mainTelas.pessoaLogada, Vac, dtVac.getValue(), LocalVac, gruposelect.getValue());
    mainTelas.registroVacinaLogado = cadastro;
    mainTelas.registroController.addRegistroVacina(cadastro);
    mainTelas.registroVacinaLogado.setDose(0);
    mainTelas.registroController.salvar();
    mainTelas.pessoaController.salvar();
    
    mainTelas.changeScreen("status");
    System.out.println(cadastro);

  }

}
