package ruraldevs.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ruraldevs.beans.Pessoa;

public class PessoaRepository extends GenericRepository<Pessoa> {
	private static final long serialVersionUID = -170031939952062163L;
	private static PessoaRepository instance = null;

	private PessoaRepository() {}

	public static PessoaRepository getInstance() {
		if (instance == null) {
			instance = lerDoArquivo();
		}
		return instance;
	}

	public void addPessoa(Pessoa pessoa) {
		this.addDado(pessoa);
		lerDoArquivo();
	}

	public List<Pessoa> getPessoas() {
		return this.getDados();
	}

	public void deletePessoa(Pessoa pessoa) {
		this.deleteDado(pessoa);
	}

	public void updatePessoa(Pessoa pessoa) {
		this.updateDado(pessoa);

	}

	private static PessoaRepository lerDoArquivo() {
		PessoaRepository instanciaLocal = null;

		File in = new File("pessoas.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (PessoaRepository) o;
		} catch (Exception e) {
			instanciaLocal = new PessoaRepository();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
				}
			}
		}
		return instanciaLocal;
	}

	public void salvarArquivo() {
		if (instance == null) {
			return;
		}
		File out = new File("pessoas.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(out);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
				}
			}
		}
	}
}

