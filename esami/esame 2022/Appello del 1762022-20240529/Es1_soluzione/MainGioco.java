
public class MainGioco {
	static final int numGiocatori=4;
	void exec() {
		Giocatore[] iGiocatori=new Giocatore[numGiocatori];
		Partita p=new Partita(numGiocatori);
		for(int i=0; i<numGiocatori; i++) {
			iGiocatori[i]=new Giocatore(i, p);
		}
		for(int i=0; i<numGiocatori; i++) {
			iGiocatori[i].start();
		}
		for(int i=0; i<numGiocatori; i++) {
			try {
				iGiocatori[i].join();
			} catch (InterruptedException e) {	}
		}
		System.out.println("partita terminata");
	}
	public static void main(String[] args) {
		new MainGioco().exec();
	}
}
