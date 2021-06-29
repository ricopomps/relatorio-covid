package ruraldevs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class TelaSelecionarDataController implements Initializable {
	@FXML
	private Button btnOk;
	@FXML
	private DatePicker datePInicial;
	@FXML
	private DatePicker datePFinal;

	private final static TelaSelecionarDataController instance = new TelaSelecionarDataController();

	private TelaSelecionarDataController() {}

	public static TelaSelecionarDataController getInstance() {
		return instance;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		datePInicial.setValue(LocalDate.of(2020, 02, 25));
		datePFinal.setValue(LocalDate.now());
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDate dataInicial = datePInicial.getValue();
				LocalDate dataFinal = datePFinal.getValue();
				btnOk.fireEvent(new DatasSelecionadasEvent(DatasSelecionadasEvent.SALVAR_DATAS, dataInicial, dataFinal));
				Stage stage = (Stage) btnOk.getScene().getWindow();
				stage.hide();
			}
		});
	}

	public DatePicker getDatePInicial() {
		return datePInicial;
	}

	public DatePicker getDatePFinal() {
		return datePFinal;
	}
}
