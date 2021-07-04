package ruraldevs.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ruraldevs.beans.EstadosEnum;
import ruraldevs.beans.RegistroCasos;
import ruraldevs.exceptions.DadosNaoEncontradosException;

public class RegistrosCasosRepository implements Serializable {
    private static final long serialVersionUID = 7711984117587136158L;
    private static RegistrosCasosRepository instance;
    private List<RegistroCasos> dados;

    private RegistrosCasosRepository() {
        this.dados = new ArrayList<>();
    }

    private RegistrosCasosRepository(int capacidadeInicial) {
        this.dados = new ArrayList<>(capacidadeInicial);
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
        Path path = Paths.get("./src/ruraldevs/data/caso_full.csv");
        instance = new RegistrosCasosRepository();
        try (BufferedReader bReader = Files.newBufferedReader(path)) {
            int initialCapacity = (int) Files.lines(path).count();
            instance = new RegistrosCasosRepository(initialCapacity);

            Iterable<CSVRecord> registrosCasos = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(bReader);

            for (CSVRecord csvRecord : registrosCasos) {
                EstadosEnum estado = EstadosEnum.valueOf(csvRecord.get("state"));
                String cidade = csvRecord.get("city");
                LocalDate data = LocalDate.parse(csvRecord.get("date"));
                LocalDate ultimaData = LocalDate.parse(csvRecord.get("last_available_date"));
                boolean isState;
                if (csvRecord.get("place_type").equals("state")) {
                    isState = true;
                } else {
                    isState = false;
                }
                boolean isRepeated;
                if (csvRecord.get("is_repeated").equals("true")) {
                    isRepeated = true;
                } else {
                    isRepeated = false;
                }
                long numeroDeNovosCasos = Long.parseLong(csvRecord.get("new_confirmed"));
                long numeroTotalDeCasos = Long.parseLong(csvRecord.get("last_available_confirmed"));
                long numeroDeNovasMortes = Long.parseLong(csvRecord.get("new_deaths"));
                long numeroTotalDeMortes = Long.parseLong(csvRecord.get("last_available_deaths"));
                boolean isLast = Boolean.parseBoolean(csvRecord.get("is_last").toLowerCase());
                if (cidade.equals("")) {
                    instance.addDado(new RegistroCasos(estado, data, ultimaData, isState, numeroDeNovosCasos, numeroTotalDeCasos, numeroDeNovasMortes, numeroTotalDeMortes, isLast, isRepeated));
                } else {
                    instance.addDado(new RegistroCasos(estado, cidade, data, ultimaData, isState, numeroDeNovosCasos, numeroTotalDeCasos, numeroDeNovasMortes, numeroTotalDeMortes, isLast, isRepeated));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public List<RegistroCasos> filtrarPorEstado(EstadosEnum estado, LocalDate dataInicial, LocalDate dataFinal) throws DadosNaoEncontradosException {
        List<RegistroCasos> novaLista = new ArrayList<>();
        for (RegistroCasos registroCasos : this.getDados()) {
            if (registroCasos.isIsState() && registroCasos.getEstado().equals(estado) && (registroCasos.getData().isEqual(dataInicial) || registroCasos.getData().isAfter(dataInicial))
                    && (registroCasos.getData().isEqual(dataFinal) || registroCasos.getData().isBefore(dataFinal))) {
                novaLista.add(registroCasos);
            }
        }
        if (novaLista.size() == 0) {
            throw new DadosNaoEncontradosException(dataInicial, dataFinal, estado);
        }
        return novaLista;
    }

    public List<RegistroCasos> filtrarPorCidade(EstadosEnum estado, String cidade, LocalDate dataInicial, LocalDate dataFinal) throws DadosNaoEncontradosException {
        List<RegistroCasos> novaLista = new ArrayList<>();
        for (RegistroCasos registroCasos : this.getDados()) {
            if (!registroCasos.isIsState() && registroCasos.getEstado().equals(estado) && registroCasos.getCidade().equals(cidade) && (registroCasos.getData().isEqual(dataInicial) || registroCasos.getData().isAfter(dataInicial))
                    && (registroCasos.getData().isEqual(dataFinal) || registroCasos.getData().isBefore(dataFinal))) {
                novaLista.add(registroCasos);
            }
        }
        if (novaLista.size() == 0) {
            throw new DadosNaoEncontradosException(dataInicial, dataFinal, estado, cidade);
        }
        return novaLista;
    }
}
