package ruraldevs.beans;

import java.io.Serializable;
import java.util.List;

public class Estado implements Serializable {
    private static final long serialVersionUID = 5869234137803042807L;
    private EstadosEnum nomeEstado;
    private List<String> cidades;

    public Estado(EstadosEnum estado, List<String> cidades) {
        setNomeEstado(estado);
        setCidades(cidades);
    }

    public EstadosEnum getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(EstadosEnum estado) {
        this.nomeEstado = estado;
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
    }

    @Override
    public String toString() {
        return String.format("Estado: %s, Cidades: %s, %s, %s, %s, %s", nomeEstado, cidades.get(0), cidades.get(1), cidades.get(2), cidades.get(3), cidades.get(4));
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj != null) {
            if (this.getNomeEstado().equals(((Estado) obj).getNomeEstado())) {
                equal = true;
            }
        }
        return equal;
    }
}
