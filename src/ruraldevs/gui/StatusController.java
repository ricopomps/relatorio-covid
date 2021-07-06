package ruraldevs.gui;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StatusController implements Initializable {
    @FXML
    Label nomelabel;
    @FXML
    Label cpflabel;
    @FXML
    Label datalabel;
    @FXML
    Label datanasclabel;
    @FXML
    Label cidadelabel;
    @FXML
    Label ceplabel;
    @FXML
    Label grupolabel;
    @FXML
    Label estadolabel;
    @FXML
    Label postolabel;
    @FXML
    Label doselabel;
    @FXML
    Label vacinalabel;

    public void showData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        String data1 = MainTelas.registroVacinaLogado.getDataDaVacina().format(formatter);
        String data2 = MainTelas.pessoaLogada.getDataNascimento().format(formatter);

        nomelabel.setText(MainTelas.pessoaLogada.getNome());

        cpflabel.setText(MainTelas.pessoaLogada.getCpf());

        datanasclabel.setText(data2);

        vacinalabel.setText(MainTelas.registroVacinaLogado.getVacina().getNomeVacina().toString());

        datalabel.setText(data1);

        cidadelabel.setText(MainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getCidade());

        doselabel.setText(Integer.toString(MainTelas.registroVacinaLogado.getDose()));

        grupolabel.setText(MainTelas.pessoaLogada.getGrupo().toString());

        ceplabel.setText(MainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getCep());

        estadolabel.setText(MainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getEstado().toString());

        postolabel.setText(MainTelas.registroVacinaLogado.getLocalVacina().getPostoVacinacao());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
    }
    
      public void logoutbutton(ActionEvent event) {
	  
	  
	  
	  
  }	
public void dadosbttnpressed(ActionEvent event) {
	  
	 mainTelas.changeScreen("dados");
	  
	  
  }	
}
