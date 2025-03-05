import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TavoloServer extends UnicastRemoteObject implements TavoloServerInterface {
	private static final long serialVersionUID = 1L;
	TavoloGioco tavolo;
	GiocatoreInterface giocatori[];
	public TavoloServer() throws RemoteException {
		super();
		tavolo = new TavoloGioco(0);
		giocatori=new GiocatoreInterface[2];
		giocatori[0]=null;
		giocatori[1]=null;
	}
	private void exec() {
		int chiDiTurno=0;
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("TavoloDaGioco", this);
			System.out.println("Server running");
			// aspettiamo che entrambi i giocatori siano connessi
			while(giocatori[0]==null || giocatori[1]==null) {
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
			}
			while(true) {
				tavolo.aspettaTurno(chiDiTurno);
				giocatori[chiDiTurno].puoiGiocare();
				chiDiTurno=1-chiDiTurno;
			}
		} catch (RemoteException e) {
			System.out.println("Server creation failed");
		}	
	}
	public static void main(String[] args) throws RemoteException {
		new TavoloServer().exec();
	}
	public void inizio(int chi, GiocatoreInterface g) throws RemoteException {
		System.out.println("server: ricevo inizia da "+g.name());
		if(chi==0 || chi==1) {
			giocatori[chi]=g;
			System.out.println("server: inizia giocatore "+g.name());
		}
	}
	public void mossa(int chi, GiocatoreInterface g, String m) throws RemoteException {
		tavolo.mossa(chi, m);
	}
}
