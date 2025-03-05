import java.util.Random;

public class Partita {
	static final int NUM_MANI=4;
	Random rnd;
	int[] giocate;
	int numGiocate;
	int numGiocatori;
	int totaleMano;
	boolean manoFinita;
	boolean manoIniziata;
	Partita(int n){
		numGiocatori=n;
		giocate= new int[numGiocatori];
		reset();
		rnd=new Random();
	}
	private void reset() {
		System.out.println("Partita: reset");
		numGiocate=0;
		for(int i=0; i<numGiocatori; i++) {
			giocate[i]=-1;
		}
		manoIniziata=true;
		manoFinita=false;
	}
	private void calcola() {
		totaleMano=0;
		for(int n: giocate) {
			totaleMano+=n;
		}
	}
	private void mostraGiocate() {
		System.out.print("[ ");
		for(int n: giocate) {
			System.out.print(n+" ");
		}
		System.out.println("]");
	}
	public synchronized int numMani() {
		return NUM_MANI;
	}
	public synchronized void giocata(int idGiocatore, int carta) {
		while(!manoIniziata) {
			try { wait(); } catch (InterruptedException e) { }
		}
		if(giocate[idGiocatore]<0) {
			giocate[idGiocatore]=carta;
			System.out.println("Giocatore "+idGiocatore+" ha giocato "+carta);
			numGiocate++;
			if(numGiocate==numGiocatori) {
				manoFinita=true;
				mostraGiocate();
				calcola();
				notifyAll();
			}
		} 
	}
	public synchronized int puntiMano(int id) {
		while(!manoFinita) {
			try { wait(); } catch (InterruptedException e) { }
		}
		int tot=rnd.nextInt(Math.max(1, totaleMano));
		numGiocate--;
		if(numGiocate==0) {
			reset();
			manoIniziata=true;
			notifyAll();
		}
		return tot;
	}
}
