import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Giocatore {
	private Random rnd;
	private int mioId;
	Giocatore() throws RemoteException{
		rnd=new Random();
		mioId=rnd.nextInt(1000);
	}
	private void exec() throws RemoteException, NotBoundException {
		int estratto;
		DatoScommessa miaScommessa;
		int numIterations=2+rnd.nextInt(8);
		Registry reg;
		reg=LocateRegistry.getRegistry(1099);
		ServerInterf ilGioco = (ServerInterf) reg.lookup("GIOCO");
		for(int i=0; i<numIterations; i++) {
			miaScommessa=new DatoScommessa(rnd.nextInt(Gioco.MAX_NUM), rnd.nextInt(10));
			boolean fatto=false;
			while(!fatto) {
				fatto=ilGioco.piazzaScommessa(mioId, miaScommessa);
				if(!fatto) {
					try { Thread.sleep(250); } catch (InterruptedException e) { }
				}
			}
			System.out.println("giocatore_"+mioId+": faccio "+miaScommessa);
			estratto=ilGioco.leggiEstratto();
			if(estratto==miaScommessa.getNumero()) {
				System.out.println("giocatore_"+mioId+": ho vinto!");
			} else {
				System.out.println("giocatore_"+mioId+": ho perso!");
			}
		}
		System.out.println("giocatore_"+mioId+": smetto");
	}
	public static void main(String[] args) {
		try {
			new Giocatore().exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("giocatore KO");
		}
	}
}
