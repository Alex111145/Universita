import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TavoloServerInterface extends Remote {
	public void inizio(int chi, GiocatoreInterface g) throws RemoteException;
	public void mossa(int chi, GiocatoreInterface g, String m) throws RemoteException;
}
