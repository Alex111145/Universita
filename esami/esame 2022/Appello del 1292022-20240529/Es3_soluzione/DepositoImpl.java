import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DepositoImpl extends UnicastRemoteObject implements DepositoInterf {
	private static final long serialVersionUID = 1L;
	Deposito depositoIn;
	Deposito depositoOut;
	protected DepositoImpl() throws RemoteException {
		super();
		depositoIn = new Deposito();
		depositoOut = new Deposito();
	}

	public void AggiungiRichiesta(RichiestaRisposta rr) throws RemoteException{
		depositoIn.Aggiungi(rr);
	}
	public void AggiungiRisposta(RichiestaRisposta rr) throws RemoteException{
		depositoOut.Aggiungi(rr);
	}
	public RichiestaRisposta LeggiRichiesta() throws RemoteException{
		return depositoIn.LeggiQualunque();
	}
	public RichiestaRisposta LeggiRisposta(int idx) throws RemoteException{
		return depositoOut.LeggiIdx(idx);
	}
	public static void main(String[] args) {
		try {
			DepositoImpl deposito=new DepositoImpl();
			Registry reg=LocateRegistry.createRegistry(1099);
			reg.rebind("DEPOSITO", deposito);
			System.out.println("server deposito pronto");
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("server main failed");
		}
	}
}
