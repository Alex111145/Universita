package RMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerGiocoImpl  extends UnicastRemoteObject implements servergioco {
	private static final long serialVersionUID = 1L;

	 // nome classe condivisa NOME INVENTATO;
	Tavolo ilGioco; // classe condidivisa con wait e notify e il suo " soprannome"
	public ServerGiocoImpl  () throws RemoteException {
		super();
		ilGioco = new Tavolo(); // creazione del metodo servergiocoimpl  tavolo = classe condivisa con wait e notify
	}
// questi sono i metodi che trovo in server che a sua volta arrivano dal client
// nella funzione mettere il soprannome della classe condivisa . il metodo 
// se non ci fosse void allora fare return ilgioco.inziagioco();
	public void iniziaGioco(String nome, int stato) throws RemoteException {
		ilGioco.iniziaGioco(nome, stato);
	}

	public void cambiaStato(String nome, int stato) throws RemoteException {
		ilGioco.cambiaStato(nome, stato);
	}

	public boolean promozione(String nome, int t) throws RemoteException {
		System.out.println("server riceve ric. promozione da "+nome);
		boolean esito=ilGioco.promozione(nome, t);
		System.out.println("richiesta da "+nome+(esito?" OK":" fallita"));
		return esito;
	}
	
	public static void main(String args[]) {
		try {
			ServerGiocoImpl sp = new ServerGiocoImpl();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("ServizioGioco", sp);
			System.out.println("Server running");
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
}
