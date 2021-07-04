package ruraldevs.gui;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StatusController implements Initializable {
	@FXML
	Label nomeLabel;
	@FXML
	Label cpfLabel;
	@FXML
	Label dataLabel;
	@FXML
	Label dataNascLabel;
	@FXML
	Label localLabel;
	@FXML
	Label doseLabel;
	@FXML
	Label vacinaLabel;

	public void showData() {
		MainTelas.registroVacinaLogado.setDose(1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		String data1 = MainTelas.registroVacinaLogado.getDataDaVacina().format(formatter);
		String data2 = MainTelas.pessoaLogada.getDataNascimento().format(formatter);

		nomeLabel.setText(MainTelas.pessoaLogada.getNome());

		cpfLabel.setText(MainTelas.pessoaLogada.getCpf());

		dataNascLabel.setText(data2);

		vacinaLabel.setText(MainTelas.registroVacinaLogado.getVacina().getNomeVacina().toString());

		dataLabel.setText(data1);

		localLabel.setText(MainTelas.registroVacinaLogado.getLocalVacina().getEnderecoVacina().getCidade());

		doseLabel.setText(Integer.toString(MainTelas.registroVacinaLogado.getDose()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showData();
	}
}
