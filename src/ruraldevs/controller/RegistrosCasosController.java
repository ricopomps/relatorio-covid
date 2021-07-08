package ruraldevs.controller;

import java.time.LocalDate;
import java.util.List;
import ruraldevs.beans.RegistroCasos;
import ruraldevs.exceptions.DadosNaoEncontradosException;
import ruraldevs.exceptions.DataInicialAposFinalException;
import ruraldevs.service.RegistrosCasosService;

public class RegistrosCasosController {
    private RegistrosCasosService service;

    public RegistrosCasosController() {
        this.service = new RegistrosCasosService();
    }

    public void addRegistroCasos(RegistroCasos registroCasos) {
        this.service.addRegistroCasos(registroCasos);
    }

    public List<RegistroCasos> getRegistroCasos() {
        return this.service.getRegistrosCasos();
    }

    public void deleteRegistroCasos(RegistroCasos registroCasos) {
        this.service.deleteRegistroCasos(registroCasos);
    }


    public void updateRegistroCasos(RegistroCasos registroCasos) {
        this.service.updateRegistroCasos(registroCasos);
    }

    public List<RegistroCasos> filtrar(String estado, String cidade, LocalDate dataInicial, LocalDate dataFinal) throws DataInicialAposFinalException, DadosNaoEncontradosException {
        return this.service.filtrar(estado, cidade, dataInicial, dataFinal);
    }

    public boolean checarAtualizacoes() {
        return this.service.checarAtualizacoes();
    }

    public void baixarArquivo() {
        this.service.baixarArquivo();
    }

    public void extrairArquivo() {
        this.service.extrairArquivo();
    }
}

