import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGiocoInterface extends Remote {
	void aspettaTurno(int idGiocatore)  throws RemoteException;
	void mossa(int idGiocatore, Mossa m) throws RemoteException;
	void attesaMossa()  throws RemoteException;
	boolean letturaEsito()  throws RemoteException;
	Mossa letturaMossaCorrente() throws RemoteException;
	void approva(boolean mossaOK) throws RemoteException;
}
