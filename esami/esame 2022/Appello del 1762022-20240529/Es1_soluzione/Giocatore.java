import java.util.Random;

public class Giocatore extends Thread {
	private int mioId;
	private Partita laPartita;
	private Random rnd;
	Giocatore(int id, Partita p){
		mioId=id;
		laPartita=p;
		rnd=new Random();
	}
	public void run() {
		int punti=0;
		int numMani=laPartita.numMani();
		System.out.println("Giocatore "+mioId+" inizio");
		for(int mano=0; mano<numMani; mano++) {
			System.out.println("Giocatore "+mioId+" gioco la mano "+mano);
			laPartita.giocata(mioId, rnd.nextInt(10));
			int puntiMano=laPartita.puntiMano(mano);
			System.out.println("Giocatore "+mioId+" ho fatto "+puntiMano+" punti in questa mano");
			punti+=puntiMano;
		}
		System.out.println("Giocatore "+mioId+" ho fatto "+punti+" punti in totale");
	}
}
