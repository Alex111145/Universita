import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

class GiocatoreImpl extends UnicastRemoteObject implements Giocatore {
	private static final long serialVersionUID = 1L;
	String name;
	ServerGioco gestoreGioco;
	GiocatoreImpl() throws RemoteException {
		super();
		long now=System.currentTimeMillis();
		name="Giocatore_"+(now%100000);
	}
	private void termina() {
		try {
			gestoreGioco.removeClient(this);
			UnicastRemoteObject.unexportObject(this, true);
		} catch (RemoteException e1) {
			System.exit(0);
		}	
	}
	public void exec() {
		Registry reg;
		String dest = null;
		boolean destOK=false;
		try {
			reg = LocateRegistry.getRegistry();
			gestoreGioco=	(ServerGioco)	reg.lookup("ServizioGioco");
			gestoreGioco.addClient(this);
			for(int j=0; j<10; j++) {
				// try {
					// Thread.sleep(ThreadLocalRandom.current().nextInt(200,300));
				// } catch (InterruptedException e) { }
				// manda messaggio
				String mossa="mossa_"+ThreadLocalRandom.current().nextInt(1, 10);
				System.out.println(name+" faccio mossa "+mossa);	
				gestoreGioco.mossa(mossa);
				System.out.println(name+" scrittura completata");	
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			termina();
		}
		termina();
	}

	public void notificaSituazione(String s) throws RemoteException {
		System.out.println(name+" ho ricevuto nuova situazione: "+s);	
	}
	public static void main(String args[]) throws RemoteException {
		new GiocatoreImpl().exec();
		System.out.println("Client: ohibo`, termina main");
	}
}
