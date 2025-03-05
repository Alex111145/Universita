import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Arbitro extends Thread{
	Gioco ilGioco;

	public Arbitro() {
	}
	public void run() {
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry(1099);
			ServerGiocoInterface ilGioco=(ServerGiocoInterface) reg.lookup("ServerGioco");
			while(true) {
				System.out.println("Arbitro aspetta");
				ilGioco.attesaMossa();
				System.out.println("Arbitro legge mossa corrente");
				Mossa m = ilGioco.letturaMossaCorrente();
				boolean mossaOK=m.getValore()<60;
				System.out.println("Arbitro decide "+(mossaOK?" OK":"KO"));
				ilGioco.approva(mossaOK);
			}
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new Arbitro().start();
	}
}
