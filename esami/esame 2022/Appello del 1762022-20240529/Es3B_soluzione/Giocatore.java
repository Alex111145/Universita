import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Giocatore extends UnicastRemoteObject implements GiocatoreInterf {
	private static final long serialVersionUID = 1L;
	private Random rnd;
	private int mioId;
	private DatoScommessa miaScommessa;
	private boolean scommessaInCorso=false;
	Giocatore() throws RemoteException{
		super();
		rnd=new Random();
		mioId=rnd.nextInt(1000);
		scommessaInCorso=false;
	}
	private void exec() throws RemoteException, NotBoundException {
		int numIterations=2+rnd.nextInt(8);
		Registry reg;
		reg=LocateRegistry.getRegistry(1099);
		ServerInterf ilGioco = (ServerInterf) reg.lookup("GIOCO");
		ilGioco.aggiungi(this);
		for(int i=0; i<numIterations; i++) {
			miaScommessa=new DatoScommessa(rnd.nextInt(Gioco.MAX_NUM), rnd.nextInt(10));
			boolean fatto=false;
			while(!fatto) {
				fatto=ilGioco.piazzaScommessa(this, miaScommessa);
				if(!fatto) {
					try { Thread.sleep(350); } catch (InterruptedException e) { }
				} else {
					scommessaInCorso=true;
					System.out.println("giocatore_"+mioId+": fatto "+miaScommessa);
				}
			}
			while(scommessaInCorso) {
				// simuliamo con una sleep il fatto che il giocatore puo` fare altro ...
				try { Thread.sleep(50); } catch (InterruptedException e) { }
			}
		}
		System.out.println("giocatore_"+mioId+": smetto");
		ilGioco.togli(this);
		try { Thread.sleep(1000); } catch (InterruptedException e) { }
		UnicastRemoteObject.unexportObject(this, true);
	}
	public void notificaEstratto(int estratto) throws RemoteException {
		if(estratto==miaScommessa.getNumero()) {
			System.out.println("giocatore_"+mioId+": ho vinto!");
		} else {
			System.out.println("giocatore_"+mioId+": ho perso!");
		}
		synchronized(this) {
			scommessaInCorso=false;
		}
	}
	public static void main(String[] args) {
		try {
			new Giocatore().exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("giocatore KO");
			System.exit(0);
		}
	}
}
