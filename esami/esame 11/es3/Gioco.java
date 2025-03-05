
public class Gioco {
	int turno;
	Mossa mossaCorrente;
	boolean daApprovare;
	boolean approvata;
	public Gioco() {
		turno=1;
		daApprovare=false;
		mossaCorrente=null;
		approvata=false;
	}

	public synchronized void aspettaTurno(int idGiocatore) {
		while(idGiocatore!=turno) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
	}
	public synchronized void mossa(int idGiocatore, Mossa m) {
		approvata=false;
		daApprovare=true;
		mossaCorrente=m;
		notifyAll();  // per svegliare l'arbitro
	}
	public synchronized boolean letturaEsito() {
		while(daApprovare) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		if(approvata) {
			turno=(turno%2)+1;
		}
		notifyAll();  // per svegliare l'altro giocatore
		return(approvata);
	}

	public synchronized void attesaMossa() {
		while(!daApprovare) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
	}
	public synchronized Mossa letturaMossaCorrente() {
		return mossaCorrente;
	}

	public synchronized void approva(boolean mossaOK) {
		daApprovare=false;
		approvata=mossaOK;
		notifyAll();  // per svegliare giocatore che ha fatto la mossa
	}

}
