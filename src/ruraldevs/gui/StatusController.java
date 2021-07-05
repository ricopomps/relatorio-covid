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

	  
      mainTelas.registroVacinaLogado.setDose(1);
    

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
    String data1 = mainTelas.registroVacinaLogado.getDataDaVacina().format(formatter);
    String data2 = mainTelas.pessoaLogada.getDataNascimento().format(formatter);

    nomelabel.setText(mainTelas.pessoaLogada.getNome());

    cpflabel.setText(mainTelas.pessoaLogada.getCpf());

    datanasclabel.setText(data2);

    vacinalabel.setText(mainTelas.registroVacinaLogado.getVacina().getNomeVacina().toString());

    datalabel.setText(data1);

    cidadelabel.setText(mainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getCidade());

    doselabel.setText(Integer.toString(mainTelas.registroVacinaLogado.getDose()));
    
    grupolabel.setText(mainTelas.pessoaLogada.getGrupo().toString());
    
    ceplabel.setText(mainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getCep());
    
    estadolabel.setText(mainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getEstado().toString());
    
    postolabel.setText(mainTelas.registroVacinaLogado.getLocalVacina().getPostoVacinacao());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    contentSelect();
    

  }
  private void contentSelect() {
    showData();

  }

}
