import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ClienteDati extends UnicastRemoteObject implements ClientInterface {
	private static final long serialVersionUID = 1L;
	int myId;
	DatiInterface iDati;
	boolean possoTerminare;
	ClienteDati() throws RemoteException {
		Random rnd=new Random();
		myId=rnd.nextInt(1000);
		possoTerminare=false;
		System.out.println("Client_"+myId+" created");
	}
	private void mySleep(int i1, int i2) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(i1, i2));
		} catch (InterruptedException e) { }
	}
	public void exec() {
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry(1099);
			iDati = (DatiInterface) reg.lookup("DataService");
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Client terminates. Server not found.");
		}
		try {
			if((myId%2)==1) {
				iDati.aggiungiDato("potipu", "info potipu");
				mySleep(0, 2000);
				iDati.aggiungiDato("patagarru", "info patagarru");
				mySleep(0, 2000);
				iDati.aggiungiDato("svicolone", "info svicolone");
			} else {
				String chiaveDaCercare="svicolone";
				mySleep(0, 2000);
				System.out.println("Client"+myId+": cerco "+chiaveDaCercare);
				iDati.trovaDato(this, chiaveDaCercare);
				while(!possoTerminare) {
					try {
						Thread.sleep(1000); // qui il client potrebbe fare qualcosa di utile
					} catch (InterruptedException e) {	}
				}
			}
		} catch (RemoteException e) {
			System.err.println("client runnable KO");
		}
	}
	public static void main(String[] args) throws RemoteException {
		new ClienteDati().exec();
	}
	public void notifica(String key, String info) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Client"+myId+": info di "+key+" e` "+ info);
		possoTerminare=true;
	}
}
