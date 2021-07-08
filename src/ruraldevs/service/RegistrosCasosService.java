package ruraldevs.service;

import java.time.LocalDate;
import java.util.List;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.RegistroCasos;
import ruraldevs.exceptions.DadosNaoEncontradosException;
import ruraldevs.exceptions.DataInicialAposFinalException;
import ruraldevs.repository.RegistrosCasosRepository;

public class RegistrosCasosService {
    private RegistrosCasosRepository repositorio;

    public RegistrosCasosService() {
        this.repositorio = RegistrosCasosRepository.getInstance();
    }

    public void addRegistroCasos(RegistroCasos registroCasos) {
        this.repositorio.addDado(registroCasos);
    }

    public List<RegistroCasos> getRegistrosCasos() {
        return this.repositorio.getDados();
    }

    public void deleteRegistroCasos(RegistroCasos registroCasos) {
        this.repositorio.deleteDado(registroCasos);
    }

    public void updateRegistroCasos(RegistroCasos registroCasos) {
        this.repositorio.updateDado(registroCasos);
    }

    public List<RegistroCasos> filtrar(String estado, String cidade, LocalDate dataInicial, LocalDate dataFinal) throws DataInicialAposFinalException, DadosNaoEncontradosException {
        if (dataFinal.isBefore(dataInicial)) {
            throw new DataInicialAposFinalException(dataInicial, dataFinal);
        }
        EstadosEnum estadoE = EstadosEnum.PE;
        for (EstadosEnum estado1 : EstadosEnum.values()) {
            if (estado.equals(estado1.getNomeEstado())) {
                estadoE = estado1;
                break;
            }
        }
        if (cidade != null && !cidade.equals("")) {
            return this.repositorio.filtrarPorCidade(estadoE, cidade, dataInicial, dataFinal);
        } else {
            return this.repositorio.filtrarPorEstado(estadoE, dataInicial, dataFinal);
        }
    }

    public boolean checarAtualizacoes() {
        return this.repositorio.checarAtualizacoes();
    }

    public void baixarArquivo() {
        this.repositorio.baixarArquivo();
    }

    public void extrairArquivo() {
        this.repositorio.extrairArquivo();
    }

}
