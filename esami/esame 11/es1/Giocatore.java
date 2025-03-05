import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Giocatore extends Thread {
	int miaId;
	Gioco ilGioco=null;
	Random rnd=new Random();
	public Giocatore(int i, Gioco g) {
		miaId=i;
		ilGioco=g;
		start();
	}
	public void run() {
		while(true) {  // la partita non ha fine
			int n=-99;
			boolean approvata=false;
			System.out.println("Giocatore "+miaId+" aspetta turno");
			ilGioco.aspettaTurno(miaId);  // chiamata sospensiva: si rientre dalla chiamata quando e` il proprio turno
			while(!approvata) {
				n=ThreadLocalRandom.current().nextInt(100);
				Mossa laMossa=new Mossa(miaId, n);
				System.out.println("Giocatore "+miaId+" fa mossa "+n);
				ilGioco.mossa(miaId, laMossa);
				System.out.println("Giocatore "+miaId+" legge esito");
				approvata=ilGioco.letturaEsito(); // chiamata sospensiva: si rientre dalla chiamata quando l'arbitro ha valutato la mossa
			}
		}
	}
}
