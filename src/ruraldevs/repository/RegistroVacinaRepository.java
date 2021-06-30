package ruraldevs.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ruraldevs.beans.RegistroVacina;

public class RegistroVacinaRepository extends GenericRepository<RegistroVacina> {
  
	private static final long serialVersionUID = -8133803673216485605L;
	private static RegistroVacinaRepository instance=null;

	public RegistroVacinaRepository() {}
	
	public static RegistroVacinaRepository getInstance() {
		if (instance == null) {
			instance = lerDoArquivo();
		}
return instance;
	}
	
	public void addRegistro(RegistroVacina registro) {
		this.addDado(registro);
		lerDoArquivo();
	}

	public List<RegistroVacina> getRegistros() {
		return this.getDados();
	}

	public void deleteRegistro(RegistroVacina registro) {
		this.deleteDado(registro);
		
	}

	public void updateRegistro(RegistroVacina registro) {
		this.updateDado(registro);
		
	}
	
	private static RegistroVacinaRepository lerDoArquivo() {
		RegistroVacinaRepository instanciaLocal = null;

    File in = new File("registro.dat");
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(in);
      ois = new ObjectInputStream(fis);
      Object o = ois.readObject();
      instanciaLocal = (RegistroVacinaRepository) o;
    } catch (Exception e) {
      instanciaLocal = new RegistroVacinaRepository();
    } finally {
      if (ois != null) {
        try {
          ois.close();
        } catch (IOException e) {
        }
      }} return instanciaLocal;
    }

  public void salvarArquivo() {
	    if (instance == null) {
	      return;
	    }
	    File out = new File("registro.dat");
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
	    }}
}

