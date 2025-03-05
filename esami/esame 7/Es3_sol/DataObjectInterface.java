import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataObjectInterface extends Remote {
	String get()  throws RemoteException;
}
