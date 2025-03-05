import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DepositoInterface extends Remote {
	public void writeMessage(String message) throws RemoteException;
	public Informazione readInfo(long lastt) throws RemoteException;
}
