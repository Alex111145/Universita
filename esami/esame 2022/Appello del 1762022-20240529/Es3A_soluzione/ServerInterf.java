import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterf extends Remote {
	public boolean piazzaScommessa(int id, DatoScommessa sc) throws RemoteException;
	public int leggiEstratto() throws RemoteException;
}
