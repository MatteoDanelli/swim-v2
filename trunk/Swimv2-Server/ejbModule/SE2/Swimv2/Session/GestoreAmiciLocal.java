package SE2.Swimv2.Session;

import javax.ejb.Local;

@Local
public interface GestoreAmiciLocal {
	public Boolean verificaAmicizia(long idUser1,long idUser2);
}
