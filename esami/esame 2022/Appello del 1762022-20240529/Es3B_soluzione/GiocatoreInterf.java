import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GiocatoreInterf extends Remote {
	public void notificaEstratto(int e) throws RemoteException;
}
