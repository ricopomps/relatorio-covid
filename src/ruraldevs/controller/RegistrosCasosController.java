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

    public void addEstado(RegistroCasos registroCasos) {
        this.service.addRegistroCasos(registroCasos);
    }

    public List<RegistroCasos> getEstados() {
        return this.service.getRegistrosCasos();
    }

    public void deleteRegistroCasos(RegistroCasos registroCasos) {
        this.service.deleteRegistroCasos(registroCasos);
    }

    public void updateEstado(RegistroCasos registroCasos) {
        this.service.updateRegistroCasos(registroCasos);
    }

    public List<RegistroCasos> filtrar(String estado, String cidade, LocalDate dataInicial, LocalDate dataFinal) throws DataInicialAposFinalException, DadosNaoEncontradosException {
        return this.service.filtrar(estado, cidade, dataInicial, dataFinal);
    }
}

