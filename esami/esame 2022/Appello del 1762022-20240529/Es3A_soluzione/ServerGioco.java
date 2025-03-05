import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerGioco extends UnicastRemoteObject implements ServerInterf {
	private static final long serialVersionUID = 1L;
	private Gioco ilGioco;
	ServerGioco() throws RemoteException{
		super();
		ilGioco=new Gioco();
	}
	private void exec() {
		for(;;) {
			ilGioco.aperturaScommesse();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
			ilGioco.faiEstrazione();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) { }
		}
	}
	public static void main(String[] args) {
		ServerGioco sg=null;
		try {
			sg=new ServerGioco();
			Registry reg=LocateRegistry.createRegistry(1099);
			reg.rebind("GIOCO", sg);
			System.out.println("Server attivo");
		} catch (RemoteException e) {
			System.out.println("Fallimento server");
			System.exit(0);
		}
		sg.exec();
	}
	public int leggiEstratto() throws RemoteException {
		return ilGioco.leggiEstratto();
	}
	public boolean piazzaScommessa(int id, DatoScommessa sc) throws RemoteException {
		return ilGioco.piazzaScommessa(id, sc);
	}
}
