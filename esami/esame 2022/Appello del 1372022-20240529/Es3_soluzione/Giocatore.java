import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Giocatore {
	Partita laPartita;
	int mioId;
	Random rnd;
	
	public Giocatore() {
		rnd=new Random();
	}
	public void exec(String id) throws RemoteException, NotBoundException {
		mioId=Integer.parseInt(id);
		Registry reg=LocateRegistry.getRegistry(1099);
		ServerPartitaInterface laPartita=(ServerPartitaInterface) reg.lookup("ServerPartita");
		int numMani=laPartita.numMani();
		int miaMossa;
		System.out.println("Giocatore "+mioId+": inizio");
		for(int j=0; j<numMani; j++) {
			System.out.println("Giocatore "+mioId+": aspetto");
			laPartita.aspettaTurno(mioId);
			SituazionePartita statoPartita=laPartita.leggiSituazione();
			// la mossa dovrebbe tenere conto della situazione, ma semplifichiamo...
			miaMossa=1+rnd.nextInt(9);
			System.out.println("Giocatore "+mioId+": gioco "+miaMossa);
			laPartita.giocata(mioId, miaMossa);
		}
	}
	public static void main(String args[]) {
		try {
			new Giocatore().exec(args[0]);
		} catch (RemoteException | NotBoundException e) {System.out.println("Giocatore KO");
		}
	}
}
