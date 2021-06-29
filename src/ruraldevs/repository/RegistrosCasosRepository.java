package ruraldevs.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.RegistroCasos;

public class RegistrosCasosRepository implements Serializable {
    private static final long serialVersionUID = 7711984117587136158L;
    private static RegistrosCasosRepository instance;
    private List<RegistroCasos> dados;

    private RegistrosCasosRepository() {
        this.dados = new ArrayList<>();
    }

    public static RegistrosCasosRepository getInstance() {
        if (instance == null) {
            lerDoArquivo();
        }
        return instance;
    }

    public List<RegistroCasos> getDados() {
        return dados;
    }

    public void addDado(RegistroCasos registroCasosR) {
        this.dados.add(registroCasosR);
    }

    public void deleteDado(RegistroCasos dado) {
        if (this.dados.contains(dado)) {
            this.dados.remove(dado);
        }
    }

    public void updateDado(RegistroCasos dado) {
        if (this.dados.contains(dado)) {
            this.dados.set(this.dados.indexOf(dado), dado);
        }
    }

    private static void lerDoArquivo() {
        instance = new RegistrosCasosRepository();
        try (BufferedReader br = new BufferedReader(new FileReader("./src/ruraldevs/data/caso_full.csv"))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] dados = line.split(",");
                EstadosEnum estado = EstadosEnum.valueOf(dados[15]);
                String cidade = dados[0];
                LocalDate data = LocalDate.parse(dados[2]);
                LocalDate ultimaData = LocalDate.parse(dados[10]);
                boolean isState;
                if (dados[14].equals("state")) {
                    isState = true;
                } else {
                    isState = false;
                }
                boolean isRepeated;
                if (dados[7].equals("true")) {
                    isRepeated = true;
                } else {
                    isRepeated = false;
                }
                long numeroDeNovosCasos = Long.parseLong(dados[16]);
                long numeroTotalDeCasos = Long.parseLong(dados[8]);
                long numeroDeNovasMortes = Long.parseLong(dados[17]);
                long numeroTotalDeMortes = Long.parseLong(dados[12]);
                boolean isLast = Boolean.parseBoolean(dados[6].toLowerCase());
                if (cidade.equals("")) {
                    instance.addDado(new RegistroCasos(estado, data, ultimaData, isState, numeroDeNovosCasos, numeroTotalDeCasos, numeroDeNovasMortes, numeroTotalDeMortes, isLast, isRepeated));
                } else {
                    instance.addDado(new RegistroCasos(estado, cidade, data, ultimaData, isState, numeroDeNovosCasos, numeroTotalDeCasos, numeroDeNovasMortes, numeroTotalDeMortes, isLast, isRepeated));
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<RegistroCasos> filtrarPorEstado(EstadosEnum estado, LocalDate dataInicial, LocalDate dataFinal) {
        List<RegistroCasos> novaLista = new ArrayList<>();
        for (RegistroCasos registroCasos : this.getDados()) {
            if (registroCasos.isIsState() && registroCasos.getEstado().equals(estado) && (registroCasos.getData().isEqual(dataInicial) || registroCasos.getData().isAfter(dataInicial))
                    && (registroCasos.getData().isEqual(dataFinal) || registroCasos.getData().isBefore(dataFinal))) {
                novaLista.add(registroCasos);
            }
        }
        return novaLista;
    }

    public List<RegistroCasos> filtrarPorCidade(EstadosEnum estado, String cidade, LocalDate dataInicial, LocalDate dataFinal) {
        List<RegistroCasos> novaLista = new ArrayList<>();
        for (RegistroCasos registroCasos : this.getDados()) {
            if (!registroCasos.isIsState() && registroCasos.getEstado().equals(estado) && registroCasos.getCidade().equals(cidade) && (registroCasos.getData().isEqual(dataInicial) || registroCasos.getData().isAfter(dataInicial))
                    && (registroCasos.getData().isEqual(dataFinal) || registroCasos.getData().isBefore(dataFinal))) {
                novaLista.add(registroCasos);
            }
        }
        return novaLista;
    }
}
