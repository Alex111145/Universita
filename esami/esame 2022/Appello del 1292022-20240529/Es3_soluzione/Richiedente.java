import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Richiedente {
	int mioId;
	public Richiedente(int id) {
		mioId=id;
	}
	public void exec() throws RemoteException, NotBoundException {
		Registry reg=LocateRegistry.getRegistry(1099);
		DepositoInterf deposito= (DepositoInterf) reg.lookup("DEPOSITO");
		final int numIterazioni=3;
		String richiesta;
		RichiestaRisposta ricrisp;
		for(int i=0; i<numIterazioni; i++) {
			richiesta="Ric. "+i+" da "+mioId;
			int richId=mioId*10000+i;
			ricrisp=new RichiestaRisposta(richId, richiesta);
			System.out.println("Richiedente "+mioId+" inserisco "+richiesta);
			deposito.AggiungiRichiesta(ricrisp);
			boolean pronto=false;
			while(!pronto) {
				ricrisp=deposito.LeggiRisposta(richId);
				if(ricrisp!=null) {
					pronto=true;
					System.out.println("Richiedente "+mioId+" inserita "+richiesta);
				} else {
					System.out.println("Richiedente "+mioId+" dormita ");
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
				}
			}
			System.out.println("Richiedente "+mioId+" Q: richiesta, A: "+ricrisp.getRisposta());
		}
		System.out.println("Richiedente "+mioId+" termina ");
	}
	public static void main(String[] args) {
		Random rnd=new Random();
		try {
			new Richiedente(rnd.nextInt(999999)).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Richiedente KO");
		}
	}
}
