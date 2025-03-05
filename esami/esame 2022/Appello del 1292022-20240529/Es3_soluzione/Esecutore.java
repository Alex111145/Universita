import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Esecutore {
	int mioId;
	public Esecutore(int id) {
		mioId=id;
	}
	public void exec() throws RemoteException, NotBoundException {
		Registry reg=LocateRegistry.getRegistry(1099);
		DepositoInterf deposito= (DepositoInterf) reg.lookup("DEPOSITO");
		final int numIterazioni=3;
		String risposta;
		RichiestaRisposta ricrisp=null;
		for(int i=0; i<numIterazioni; i++) {
			boolean pronto=false;
			while(!pronto) {
				ricrisp=deposito.LeggiRichiesta();
				if(ricrisp==null) {
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
					System.out.println("Esecutore "+mioId+" dormita");
				} else {
					System.out.println("Esecutore "+mioId+" estratto");
					pronto=true;
				}
			}
			try { Thread.sleep(500); } catch (InterruptedException e) { }
			risposta="risposta a "+ricrisp.getRichiesta();
			ricrisp.setRisposta(risposta);
			deposito.AggiungiRisposta(ricrisp);
			System.out.println("Esecutore "+mioId+" Q: richiesta, A: "+risposta);
		}
		System.out.println("Esecutore "+mioId+" termina ");
	}
	public static void main(String[] args) {
		Random rnd=new Random();
		try {
			new Esecutore(rnd.nextInt(999999)).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Esecutore KO");
		}
	}
}
