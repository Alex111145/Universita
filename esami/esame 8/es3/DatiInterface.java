import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DatiInterface extends Remote {
	public void aggiungiDato(String key, String info) throws RemoteException;
	public void eliminaDato(String key) throws RemoteException;
	public boolean esisteDato(String key) throws RemoteException;
	public void trovaDato(ClientInterface c, String key) throws RemoteException;
}
