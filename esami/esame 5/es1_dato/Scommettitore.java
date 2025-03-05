import java.util.Random;

public class Scommettitore extends Thread {
	String mioNome;
	GestoreDati ilGestore;
	public Scommettitore(int i, GestoreDati gestore) {
		mioNome="scommettitore_"+i;
		ilGestore=gestore;
	}
	
	void dormitina(int a) {
		int t=200+new Random().nextInt(a);
		try {
			Thread.sleep((long) t);
		} catch (InterruptedException e) {		}
	}
	public void run() {
		Partita pt;
		String quota;
		EsitoScommessa esito=EsitoScommessa.Indeterminata;
		for(int i=0; i<3; i++) {
			for(;;) {
				pt=ilGestore.getPartitaByPosition(i+1);
				if(pt!=null) {
					break;
				} else {
					dormitina(200);
				}
			}
			quota=pt.getQuotazione();
			if(quota!=null) {
				ilGestore.piazzaScommessa(pt.getNome(), pt.getData(), mioNome, "descr. scommessa", 100+50*i);
				esito=EsitoScommessa.Indeterminata;
				while(esito==EsitoScommessa.Indeterminata) {
					esito=ilGestore.leggiEsito(pt.getNome(), pt.getData(), mioNome);
					if(esito==EsitoScommessa.Vinta) {
						System.out.println(mioNome+": Ho vinto!!!");
					} else if(esito==EsitoScommessa.Persa) {
						System.out.println(mioNome+": Ho perso!!!");
					} else {
						dormitina(100);
					}
				}
			} else {
				dormitina(500);
			}
		}

			
	}
}
