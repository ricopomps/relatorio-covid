package ruraldevs.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class statusController implements Initializable {
	@FXML
	Label nomelabel;
	@FXML
	Label cpflabel;
	@FXML
	Label datalabel;
	@FXML
	Label datanasclabel;
	@FXML
	Label locallabel;
	@FXML
	Label doselabel;
	@FXML
	Label vacinalabel;

	public void showData() {
		try {
			if (mainTelas.registroVacinaLogado.getDose() == 0) {
				mainTelas.registroVacinaLogado.setDose(1);
			}
			if (mainTelas.registroVacinaLogado.getDose() == 1) {
				mainTelas.registroVacinaLogado.setDose(2);
			}

			System.out.println(mainTelas.pessoaLogada.getNome());

			nomelabel.setText("aa");

			cpflabel.setText(mainTelas.pessoaLogada.getCpf());

			datanasclabel.setText(mainTelas.pessoaLogada.getSenha());

			vacinalabel.setText(mainTelas.registroVacinaLogado.getVacina().getNomeVacina().toString());

			datalabel.setText(mainTelas.registroVacinaLogado.getPessoa().getNome() + "data");

			locallabel.setText(mainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getCidade());

			doselabel.setText(Integer.toString(mainTelas.registroVacinaLogado.getDose()));
		
		} catch (Exception e) {
		}
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showData();
	}

}
