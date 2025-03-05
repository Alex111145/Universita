
public class Arbitro extends Thread {
	Gioco ilGioco;
	Arbitro(Gioco g){
		ilGioco=g;	
		start();
	}
	public void run() {
		while(true) {
			System.out.println("Arbitro aspetta");
			ilGioco.attesaMossa(); // chiamata sospensiva: si rientra dalla chiamata quando c'e` una mossa da valutare
			System.out.println("Arbitro legge mossa corrente");
			Mossa m = ilGioco.letturaMossaCorrente();
			boolean mossaOK=m.getValore()<60;
			System.out.println("Arbitro decide "+(mossaOK?" OK":"KO"));
			ilGioco.approva(mossaOK);
		}
	}
}
