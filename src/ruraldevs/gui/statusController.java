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
		if (mainTelas.ab.getDose() == 0) {
			mainTelas.ab.setDose(1);
		}
		if (mainTelas.ab.getDose() == 1) {
			mainTelas.ab.setDose(2);
		}

		System.out.println(mainTelas.ac.getNome());

		nomelabel.setText("aa");

		cpflabel.setText(mainTelas.ac.getCpf());

		datanasclabel.setText(mainTelas.ac.getSenha());

		vacinalabel.setText(mainTelas.ab.getVacina().getNomeVacina().toString());

		datalabel.setText(mainTelas.ab.getPessoa().getNome() + "data");

		locallabel.setText(mainTelas.ab.getLocalVacina().getEnderecoVacina().getCidade());

		doselabel.setText(Integer.toString(mainTelas.ab.getDose()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contentSelect();
	}

	private void contentSelect() {

	}
}

