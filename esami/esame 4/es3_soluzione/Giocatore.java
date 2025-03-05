import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Giocatore extends Remote {
	public void notificaSituazione(String situazione) throws RemoteException;
}
