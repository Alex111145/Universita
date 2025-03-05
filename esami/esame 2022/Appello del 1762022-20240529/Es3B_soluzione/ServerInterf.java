import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterf extends Remote {
	public boolean piazzaScommessa(GiocatoreInterf g, DatoScommessa sc) throws RemoteException;
	public int leggiEstratto() throws RemoteException;
	public void aggiungi(GiocatoreInterf g) throws RemoteException;
	public void togli(GiocatoreInterf g) throws RemoteException;
}
