import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AstaClient extends Remote {
	public void notificaNuovaOfferta(Offerta o) throws RemoteException;
}
