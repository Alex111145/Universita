import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DepositoInterf extends Remote {
	public void AggiungiRichiesta(RichiestaRisposta rr) throws RemoteException;
	public void AggiungiRisposta(RichiestaRisposta rr) throws RemoteException;
	public RichiestaRisposta LeggiRichiesta() throws RemoteException;
	public RichiestaRisposta LeggiRisposta(int idx) throws RemoteException;
}
