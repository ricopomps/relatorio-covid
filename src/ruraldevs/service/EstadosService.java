package ruraldevs.service;

import java.util.List;
import ruraldevs.beans.Estado;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.repository.EstadosRepository;

public class EstadosService {
    private EstadosRepository repositorio;

    public EstadosService() {
        this.repositorio = EstadosRepository.getInstance();
    }

    public void imprimirEstados() {
        for (Estado estado : repositorio.getDados()) {
            System.out.println(estado);
        }
    }

    public void addEstado(Estado estado) {
        this.repositorio.addDado(estado);
    }

    public List<Estado> getEstados() {
        return this.repositorio.getDados();
    }

    public void deleteEstado(Estado estado) {
        this.repositorio.deleteDado(estado);
    }

    public void updateEstado(Estado estado) {
        this.repositorio.updateDado(estado);
    }

    public List<String> getCidadesPorEstado(String estadoRecebido) {
        EstadosEnum estadoParaPassar = EstadosEnum.PE;
        for (EstadosEnum estadoE : EstadosEnum.values()) {
            if (estadoE.getNomeEstado().equals(estadoRecebido)) {
                estadoParaPassar = estadoE;
                break;
            }
        }
        return this.repositorio.getCidadesPorEstado(estadoParaPassar);
    }

    public void salvarService() {
        EstadosRepository.getInstance().salvarArquivo();
    }
}
