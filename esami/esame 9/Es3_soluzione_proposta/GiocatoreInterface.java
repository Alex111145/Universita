import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GiocatoreInterface extends Remote {
	public void puoiGiocare() throws RemoteException;
	public String name() throws RemoteException;
}
