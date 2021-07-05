package ruraldevs.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
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
        } catch (FileNotFoundException e) {
            instance.atualizarDados();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void baixarArquivo() {
        URL website;
        try (FileOutputStream fos = new FileOutputStream("./src/ruraldevs/data/caso_full.csv.gz");) {
            website = new URL("https://data.brasil.io/dataset/covid19/caso_full.csv.gz");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            System.out.println("Baixando arquivo");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SALVAR O SHA512 NUM ARQUIVO.DAT
        try (InputStream fileIS = Files.newInputStream(Paths.get("./src/ruraldevs/data/caso_full.csv.gz")); FileOutputStream fos = new FileOutputStream("./src/ruraldevs/data/dados_hash.dat"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            String sha512 = org.apache.commons.codec.digest.DigestUtils.sha512Hex(fileIS);
            oos.writeObject(sha512);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extrairArquivo() {
        byte[] buffer = new byte[1024];
        try (FileInputStream fileIn = new FileInputStream("./src/ruraldevs/data/caso_full.csv.gz");
                GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
                FileOutputStream fileOutputStream = new FileOutputStream("./src/ruraldevs/data/caso_full.csv");) {
            int bytes_read;
            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytes_read);
            }
            System.out.println("The file was decompressed successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // DELETANDO O ARQUIVO ZIPADO
        try {
            Files.deleteIfExists(Paths.get("./src/ruraldevs/data/caso_full.csv.gz"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checarAtualizacoes() {
        boolean haAtualização = true;
        URL url;
        try {
            StringBuilder result = new StringBuilder();
            url = new URL("https://data.brasil.io/dataset/covid19/SHA512SUMS");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            File in = new File("./src/ruraldevs/data/dados_hash.dat");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream())); FileInputStream fis = new FileInputStream(in); ObjectInputStream ois = new ObjectInputStream(fis);) {
                for (String line; (line = reader.readLine()) != null;) {
                    result.append(line + "\n");
                }
                String SHA512Atual = result.toString().split("\n")[2].split(" ")[0];
                Object object = ois.readObject();
                String storedSHA512 = (String) object;
                if (SHA512Atual.equals(storedSHA512)) {
                    haAtualização = false;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                baixarArquivo();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return haAtualização;
    }

    public void atualizarDados() {
        baixarArquivo();
        extrairArquivo();
        lerDoArquivo();
    }
}
