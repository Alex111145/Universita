import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AstaServerImpl implements AstaServer {
	private static final long serialVersionUID = 1L;
	private Asta lAsta;
	public AstaServerImpl() throws RemoteException {
		super();
	}
	public void propostaOfferta(Offerta o) throws RemoteException {
		System.out.println("Server: ricevuta offerta da "+o.getWho()+" per "+o.getAmount()+" soldi");
		//   System.out.println("Server: ricevuta offerta da "+offeringClient.getName());
		Boolean esito = lAsta.nuovaOfferta(o);
		System.out.println("Server: offerta "+(esito?"":"non ")+"accettata");
	}
	public Offerta letturaOffertaCorrente() throws RemoteException {
		return lAsta.leggi_offerta();
	}
	private void exec(){
		lAsta=new Asta(1000, 1.06);
		System.out.println("server inizia con "+lAsta.leggi_offerta());
		try {
			AstaServer as=(AstaServer)UnicastRemoteObject.exportObject(this, 3939);
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("ASTA", as);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
	private void waitTermination() {
		while(System.currentTimeMillis()-lAsta.leggiTempo()<4000) {
			try {
				Thread.sleep(1000);
				System.out.println("eta`="+(System.currentTimeMillis()-lAsta.leggiTempo()));
			} catch (InterruptedException e) { 	}
		}
		System.out.println("server: vado a terminare");
		lAsta.aggiudica();
		try {
			Thread.sleep(1000);
			UnicastRemoteObject.unexportObject(this, false);
		} catch (NoSuchObjectException | InterruptedException e) {
		}
	}
	public static void main(String args[]) throws RemoteException {
		AstaServerImpl as=new AstaServerImpl();
		as.exec();
		System.out.println("server dopo exec");
		as.waitTermination();
	}
}
