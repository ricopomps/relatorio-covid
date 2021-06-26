package ruraldevs.controller;

import java.util.List;
import ruraldevs.beans.Estado;
import ruraldevs.service.EstadosService;

public class EstadosController {
    private EstadosService service;

    public EstadosController() {
        this.service = new EstadosService();
    }

    public void imprimirEstados() {
        this.service.imprimirEstados();
    }

    public void addEstado(Estado estado) {
        this.service.addEstado(estado);
    }

    public List<Estado> getEstados() {
        return this.service.getEstados();
    }

    public void deteleEstado(Estado estado) {
        this.service.deleteEstado(estado);
    }

    public void updateEstado(Estado estado) {
        this.service.updateEstado(estado);
    }

    public List<String> getCidadesPorEstado(String estado) {
        return this.service.getCidadesPorEstado(estado);
    }

    public void salvar() {
        this.service.salvarService();
    }
}