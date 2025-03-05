import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerGioco extends UnicastRemoteObject implements ServerInterf {
	private static final long serialVersionUID = 1L;
	private Gioco ilGioco;
	private List<GiocatoreInterf> iGiocatori;
	ServerGioco() throws RemoteException{
		super();
		ilGioco=new Gioco();
		iGiocatori=new ArrayList<GiocatoreInterf>();
	}
	private void exec() throws RemoteException {
		for(;;) {
			ilGioco.aperturaScommesse();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
			ilGioco.faiEstrazione();
			for(GiocatoreInterf g:iGiocatori) {
				System.out.println("server: notifico");
				g.notificaEstratto(ilGioco.leggiEstratto());
			}
			// try { Thread.sleep(300); } catch (InterruptedException e) { }
		}
	}
	public int leggiEstratto() throws RemoteException {
		return ilGioco.leggiEstratto();
	}
	public boolean piazzaScommessa(GiocatoreInterf g, DatoScommessa sc) throws RemoteException {
		int n=iGiocatori.indexOf(g);
		return ilGioco.piazzaScommessa(n, sc);
	}
	public void aggiungi(GiocatoreInterf g) throws RemoteException {
		System.out.println("server: aggiungo");
		iGiocatori.add(g);
	}
	public void togli(GiocatoreInterf g) throws RemoteException {
		System.out.println("server: rimuovo");
		iGiocatori.remove(g);		
	}
	public static void main(String[] args) {
		ServerGioco sg=null;
		try {
			sg=new ServerGioco();
			Registry reg=LocateRegistry.createRegistry(1099);
			reg.rebind("GIOCO", sg);
			System.out.println("Server attivo");
			sg.exec();
		} catch (RemoteException e) {
			System.out.println("Fallimento server");
			System.exit(0);
		}
	}
}
