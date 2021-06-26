package ruraldevs.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ruraldevs.beans.Estado;
import ruraldevs.beans.EstadosEnum;

public class EstadosRepository implements Serializable {
    private static final long serialVersionUID = 7711984117587136158L;
    private static EstadosRepository instance;
    private List<Estado> dados;

    private EstadosRepository() {
        this.dados = new ArrayList<>();
    }

    public static EstadosRepository getInstance() {
        if (instance == null) {
            instance = lerDoArquivo();
        }
        return instance;
    }

    public List<Estado> getDados() {
        return dados;
    }

    public void addDado(Estado estadoR) {
        for (Estado estado : dados) {
            if (estado.equals(estadoR)) {
                return;
            }
        }
        this.dados.add(estadoR);
    }

    public void deleteDado(Estado dado) {
        if (this.dados.contains(dado)) {
            this.dados.remove(dado);
        }
    }

    public void updateDado(Estado dado) {
        if (this.dados.contains(dado)) {
            this.dados.set(this.dados.indexOf(dado), dado);
        }
    }

    private static EstadosRepository lerDoArquivo() {
        EstadosRepository instanciaLocal = null;

        File fileIn = new File("./data/estados.dat");

        try (FileInputStream fis = new FileInputStream(fileIn); ObjectInputStream ois = new ObjectInputStream(fis);) {
            Object object = ois.readObject();
            instanciaLocal = (EstadosRepository) object;
        } catch (Exception e) {
            // instanciaLocal = new EstadosRepository();
            criarNovoArquivo();
            instanciaLocal = lerDoArquivo();
        }
        return instanciaLocal;
    }

    private static void criarNovoArquivo() {
        EstadosRepository instanciaLocal = new EstadosRepository();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("./data/estados-cidades.json")) {
            try {
                Object obj = parser.parse(reader);
                JSONObject estadosCidadesJson = (JSONObject) obj;
                JSONArray estadosArray = (JSONArray) estadosCidadesJson.get("estados");
                for (int i = 0; i < estadosArray.size(); i++) {
                    JSONObject estadoObj = (JSONObject) estadosArray.get(i);
                    JSONArray cidadesArray = (JSONArray) estadoObj.get("cidades");
                    String estado = (String) estadoObj.get("sigla");
                    List<String> cidadesLista = new ArrayList<>();
                    for (int j = 0; j < cidadesArray.size(); j++) {
                        String cidade = (String) cidadesArray.get(j);
                        cidadesLista.add(cidade);
                    }
                    instanciaLocal.addDado(new Estado(EstadosEnum.valueOf(estado), cidadesLista));
                }
                instance = instanciaLocal;
                instance.salvarArquivo();
            } catch (ParseException e) {
                System.out.println("PARSE EXCEPTION");
            }

        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }

    public void salvarArquivo() {
        if (instance == null) {
            return;
        }

        File fileOut = new File("./data/estados.dat");

        try (FileOutputStream fos = new FileOutputStream(fileOut); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getCidadesPorEstado(EstadosEnum estadoE) {
        List<String> lista = new ArrayList<>();
        for (Estado estado : this.dados) {
            if (estado.getNomeEstado().equals(estadoE)) {
                lista = estado.getCidades();
                break;
            }
        }
        return lista;
    }
}
