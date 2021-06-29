package ruraldevs.gui;

import java.time.LocalDate;
import javafx.event.Event;
import javafx.event.EventType;

public class DatasSelecionadasEvent extends Event {
    private LocalDate dataInicial, dataFinal;
    public static final EventType<DatasSelecionadasEvent> DATAS_SELECIONADAS = new EventType<>(Event.ANY, "ANY");
    public static final EventType<DatasSelecionadasEvent> ANY = DATAS_SELECIONADAS;
    public static final EventType<DatasSelecionadasEvent> SALVAR_DATAS = new EventType<>(DatasSelecionadasEvent.ANY, "SALVAR_DATAS");


    public DatasSelecionadasEvent(EventType<? extends Event> eventType, LocalDate dataInicial, LocalDate dataFinal) {
        super(eventType);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataInicial() {
        return this.dataInicial;
    }

    public LocalDate getDataFinal() {
        return this.dataFinal;
    }
}
