import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	public void subscribe(ClientInterface c) throws RemoteException;
}
