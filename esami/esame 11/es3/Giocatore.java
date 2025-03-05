import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Giocatore extends Thread{
	Gioco ilGioco;
	int miaId;
	Random rnd;

	public Giocatore(int id) {
		rnd=new Random();
		miaId=id;
	}
	public void run()  {
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry(1099);
			ServerGiocoInterface ilGioco=(ServerGiocoInterface) reg.lookup("ServerGioco");
			while(true) {
				int n=-99;
				boolean approvata=false;
				System.out.println("Giocatore "+miaId+" aspetta turno");
				ilGioco.aspettaTurno(miaId); 
				while(!approvata) {
					try {
						Thread.sleep(ThreadLocalRandom.current().nextInt(300, 600));
					} catch (InterruptedException e) {
					}
					n=rnd.nextInt(100);
					Mossa laMossa=new Mossa(miaId, n);
					System.out.println("Giocatore "+miaId+" fa mossa "+n);
					ilGioco.mossa(miaId, laMossa);
					System.out.println("Giocatore "+miaId+" legge esito");
					approvata=ilGioco.letturaEsito();
				}
			}
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new Giocatore(1).start();
		new Giocatore(2).start();
	}
}
