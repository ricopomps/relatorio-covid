package ruraldevs.gui;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import ruraldevs.beans.Endereco;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.GrupoEnum;
import ruraldevs.beans.LocalVacina;
import ruraldevs.beans.RegistroVacina;
import ruraldevs.beans.Vacina;
import ruraldevs.beans.VacinasEnum;

public class agendaController {

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
	ChoiceBox<GrupoEnum> gruposelect = new ChoiceBox<>();
	@FXML
	ChoiceBox<EstadosEnum> ufselect = new ChoiceBox<>();

	@FXML
	public void voltarpressed(ActionEvent event) {
		mainTelas.changeScreen("main");
	}

	@FXML
	private void initialize() {
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
	public void bttnagendarpressed(ActionEvent event) {
		LocalVacina localVacina = new LocalVacina();
		Endereco endereco = new Endereco();
		Vacina vacina = new Vacina();
		vacina.setNomeVacina(random());
		endereco.setEstado(ufselect.getValue());
		localVacina.setEnderecoVacina(endereco);
		LocalDate datenow = LocalDate.now().plusDays(15);
		RegistroVacina cadastro = new RegistroVacina(mainTelas.ac, vacina, datenow, localVacina, gruposelect.getValue());
		mainTelas.ab = cadastro;
		mainTelas.changeScreen("status");
	}
}
