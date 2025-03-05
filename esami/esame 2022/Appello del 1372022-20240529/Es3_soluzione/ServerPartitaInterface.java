import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerPartitaInterface extends Remote {
	public int numMani() throws RemoteException;
	public void aspettaTurno(int id) throws RemoteException;
	public void giocata(int id, int mossa) throws RemoteException;
	public SituazionePartita leggiSituazione() throws RemoteException;
}
