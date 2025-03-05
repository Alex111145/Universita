import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

public class Giocatore extends UnicastRemoteObject implements GiocatoreInterface {
	private static final long serialVersionUID = 1L;
	int myId;
	TavoloServerInterface tavolo;
	Giocatore(int n) throws RemoteException {
		myId=n;
		System.out.println("Client_"+myId+" created");
	}
	private void mySleep(int i1, int i2) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(i1, i2));
		} catch (InterruptedException e) { }
	}
	public void exec() {
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry(1099);
			tavolo = (TavoloServerInterface) reg.lookup("TavoloDaGioco");
			tavolo.inizio(myId, this);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Client terminates. Server not found.");
		}
	}
	public static void main(String[] args) throws RemoteException {
		new Giocatore(0).exec();
		new Giocatore(1).exec();
	}

	public void puoiGiocare() throws RemoteException {
		mySleep(1000, 2000); // simulo riflessione su prossima mossa
		tavolo.mossa(myId, this, "mossa_"+myId);
	}
	public String name() throws RemoteException {
		return "giocatore_"+myId;
	}
}
