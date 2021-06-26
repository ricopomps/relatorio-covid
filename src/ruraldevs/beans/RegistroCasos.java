package ruraldevs.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroCasos implements Serializable {
    private EstadosEnum estado;
    private String cidade;
    private LocalDate ultimaData;
    private LocalDate data;
    private boolean isLast;
    private boolean isState;
    private boolean isRepeated;
    private long numeroDeNovosCasos;
    private long numeroTotalDeCasos;
    private long numeroDeNovasMortes;
    private long numeroTotalDeMortes;

    public RegistroCasos(EstadosEnum estado, String cidade, LocalDate data, LocalDate ultimaData, boolean isState, long numeroDeNovosCasos, long numeroTotalDeCasos, long numeroDeNovasMortes, long numeroTotalDeMortes, boolean isLast,
            boolean isRepeated) {
        setEstado(estado);
        setCidade(cidade);
        setData(data);
        setUltimaData(ultimaData);
        setIsState(isState);
        setNumeroDeNovosCasos(numeroDeNovosCasos);
        setNumeroTotalDeCasos(numeroTotalDeCasos);
        setNumeroDeNovasMortes(numeroDeNovasMortes);
        setNumeroTotalDeMortes(numeroTotalDeMortes);
        setIsLast(isLast);
        setIsRepeated(isRepeated);
    }

    public RegistroCasos(EstadosEnum estado, LocalDate data, LocalDate ultimaData, boolean isState, long numeroDeNovosCasos, long numeroTotalDeCasos, long numeroDeNovasMortes, long numeroTotalDeMortes, boolean isLast, boolean isRepeated) {
        setEstado(estado);
        setData(data);
        setUltimaData(ultimaData);
        setIsState(isState);
        setNumeroDeNovosCasos(numeroDeNovosCasos);
        setNumeroTotalDeCasos(numeroTotalDeCasos);
        setNumeroDeNovasMortes(numeroDeNovasMortes);
        setNumeroTotalDeMortes(numeroTotalDeMortes);
        setIsLast(isLast);
        setIsRepeated(isRepeated);
    }

    @Override
    public String toString() {
        String simOuNao;
        if (isLast) {
            simOuNao = "Sim";
        } else {
            simOuNao = "Não";
        }
        if (isState) {
            return String.format("[%s] - Ultima informção? [%s] [%s] - Estado: %s, Novos casos: %d, Total de casos: %d, Novas Mortes: %d, Total de Mortes: %d", DateTimeFormatter.ofPattern("dd/MM/YYYY").format(this.data), simOuNao,
                    DateTimeFormatter.ofPattern("dd/MM/YYYY").format(this.ultimaData), this.estado.getNomeEstado(), this.numeroDeNovosCasos, this.numeroTotalDeCasos, this.numeroDeNovasMortes, this.numeroTotalDeMortes);
        }
        return String.format("[%s] - Ultima informção? [%s] [%s] - Estado: %s, Cidade: %s, Novos casos: %d, Total de casos: %d, Novas Mortes: %d, Total de Mortes: %d", DateTimeFormatter.ofPattern("dd/MM/YYYY").format(this.data), simOuNao,
                DateTimeFormatter.ofPattern("dd/MM/YYYY").format(this.ultimaData), this.estado.getNomeEstado(), this.cidade, this.numeroDeNovosCasos, this.numeroTotalDeCasos, this.numeroDeNovasMortes, this.numeroTotalDeMortes);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public long getNumeroTotalDeCasos() {
        return numeroTotalDeCasos;
    }

    public void setNumeroTotalDeCasos(long numeroTotalDeCasos) {
        this.numeroTotalDeCasos = numeroTotalDeCasos;
    }

    public boolean isIsState() {
        return isState;
    }

    public void setIsState(boolean isState) {
        this.isState = isState;
    }

    public long getNumeroDeNovosCasos() {
        return numeroDeNovosCasos;
    }

    public void setNumeroDeNovosCasos(long numeroDeNovosCasos) {
        this.numeroDeNovosCasos = numeroDeNovosCasos;
    }

    public long getNumeroDeNovasMortes() {
        return numeroDeNovasMortes;
    }

    public void setNumeroDeNovasMortes(long numeroDeNovasMortes) {
        this.numeroDeNovasMortes = numeroDeNovasMortes;
    }

    public long getNumeroTotalDeMortes() {
        return numeroTotalDeMortes;
    }

    public void setNumeroTotalDeMortes(long numeroTotalDeMortes) {
        this.numeroTotalDeMortes = numeroTotalDeMortes;
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

    public LocalDate getUltimaData() {
        return ultimaData;
    }

    public void setUltimaData(LocalDate ultimaData) {
        this.ultimaData = ultimaData;
    }

    public boolean isIsLast() {
        return isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public boolean isIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }
}
