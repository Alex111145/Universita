import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPartita extends UnicastRemoteObject implements ServerPartitaInterface {
	private static final long serialVersionUID = 1L;
	static final int numGiocatori=2;
	Partita laPartita;
	protected ServerPartita() throws RemoteException {
		super();
		laPartita=new Partita(numGiocatori);
	}
	public void aspettaTurno(int id) throws RemoteException {
		laPartita.aspettaTurno(id);
	}
	public void giocata(int id, int mossa) throws RemoteException {
		laPartita.giocata(id, mossa);
	}
	public SituazionePartita leggiSituazione() throws RemoteException {
		return laPartita.leggiSituazione();
	}
	public static void main(String args[]) throws RemoteException {
		ServerPartita questoServer=new ServerPartita();
		Registry reg=LocateRegistry.createRegistry(1099);
		reg.rebind("ServerPartita", questoServer);
	}
	public int numMani() throws RemoteException {
		return laPartita.numMani();
	}
}
