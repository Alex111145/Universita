
public class Gioco {
	int turno;  // indica se tocca al giocatore 1 o al giocatore 2
	Mossa mossaCorrente; // riferimento all'ultima mossa effettuata
	boolean daApprovare;  // indica se la mossa corrente deve ancora essere approvata dall'arbitro
	boolean approvata;       //  indica se la mossa corrente e` stata giudicata valida dall'arbitro
	public Gioco() {
		turno=1;
		daApprovare=false;
		mossaCorrente=null;
		approvata=false;
	}

	public void aspettaTurno(int idGiocatore) {
         // da definire
	}
	public void mossa(int idGiocatore, Mossa m) {
		// da definire
	}
	
	public void attesaMossa() {
	    // da definire
	}
	public boolean letturaEsito() {
		// da definire
	}

	public Mossa letturaMossaCorrente() {
		// da definire
	}

	public void approva(boolean mossaOK) {
		// da definire
	}

}
