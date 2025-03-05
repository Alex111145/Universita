import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerGioco extends UnicastRemoteObject implements ServerGiocoInterface {
	private static final int SERVER_PORT = 1099;
	private static final long serialVersionUID = 1L;
	Gioco ilGioco;
	protected ServerGioco() throws RemoteException {
		super();
		ilGioco=new Gioco();
	}

	public void aspettaTurno(int idGiocatore) {
		ilGioco.aspettaTurno(idGiocatore);
	}
	public void mossa(int idGiocatore, Mossa m) {
		ilGioco.mossa(idGiocatore, m);
	}
	
	public void attesaMossa() {
		ilGioco.attesaMossa();
	}
	public boolean letturaEsito() {
		return(ilGioco.letturaEsito());
	}

	public Mossa letturaMossaCorrente() {
		return ilGioco.mossaCorrente;
	}

	public synchronized void approva(boolean mossaOK) {
		ilGioco.approva(mossaOK);
	}
	public static void main(String[] args) throws RemoteException {
		ServerGioco serverImpl;
		try {
			serverImpl = new ServerGioco();
			Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
			registry.rebind("ServerGioco", serverImpl);
			System.out.println("server ready");
		} catch (RemoteException e) {
			System.out.println("Server could not start");
			System.exit(0);
		}
	}
}
