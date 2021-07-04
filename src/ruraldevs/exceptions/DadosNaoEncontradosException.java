package ruraldevs.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ruraldevs.beans.EstadosEnum;

public class DadosNaoEncontradosException extends Exception {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private EstadosEnum estado;
    private String cidade;

    public DadosNaoEncontradosException(LocalDate dataInicial, LocalDate dataFinal, EstadosEnum estado, String cidade) {
        super(String.format("Nenhum dado encontrado para a cidade %s do estado %s no período de %s a %s.", cidade, estado.getNomeEstado(), DateTimeFormatter.ofPattern("dd/MM/YYYY").format(dataInicial),
                DateTimeFormatter.ofPattern("dd/MM/YYYY").format(dataFinal)));
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.estado = estado;
        this.cidade = cidade;
    }

    public DadosNaoEncontradosException(LocalDate dataInicial, LocalDate dataFinal, EstadosEnum estado) {
        super(String.format("Nenhum dado encontrado para o estado %s no período de %s a %s.", estado.getNomeEstado(), DateTimeFormatter.ofPattern("dd/MM/YYYY").format(dataInicial), DateTimeFormatter.ofPattern("dd/MM/YYYY").format(dataFinal)));
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.estado = estado;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public EstadosEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadosEnum estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
