import java.util.Date;
import java.util.Random;

public class Allibratore extends Thread {
	String nome;
	GestoreDati ilGestore;
	long now;

	public Allibratore(int i, GestoreDati gestore) {
		nome="allibratore_"+i;
		ilGestore=gestore;
		now=System.currentTimeMillis();
	}
	void dormitina(int a) {
		int t=200+new Random().nextInt(a);
		try {
			Thread.sleep((long) t);
		} catch (InterruptedException e) {		}
	}
	public void run() {
		ilGestore.inserisciPartita("Milan-Inter", new Date(now+100000000));
		ilGestore.inserisciQuotazione("Milan-Inter", new Date(now+100000000), "Milan vincente 0.3, pareggio a 0.4, Inter vincente 0.3");
		ilGestore.inserisciPartita("Juve-Inter", new Date(now+150000000));
		ilGestore.inserisciQuotazione("Juve-Inter", new Date(now+150000000), "Juve vincente 0.1, pareggio a 0.4, Inter vincente 0.5");
		ilGestore.inserisciPartita("Genoa-Sampdoria", new Date(now+200000000));
		ilGestore.inserisciQuotazione("Genoa-Sampdoria", new Date(now+200000000), "Genoa vincente 0.2, pareggio a 0.5, Sampdoria vincente 0.3");
		ilGestore.inserisciPartita("Juve-Fiorentina", new Date(now+250000000));
		ilGestore.inserisciQuotazione("Juve-Fiorentina", new Date(now+250000000), "Juve vincente 0.3, pareggio a 0.4, Inter Fiorentina 0.3");
		dormitina(100);
		ilGestore.inserisciRisultato("Milan-Inter", new Date(now+100000000), "2-1");
		dormitina(100);
		ilGestore.inserisciRisultato("Juve-Inter", new Date(now+150000000), "1-1");
		dormitina(100);
		ilGestore.inserisciRisultato("Genoa-Sampdoria", new Date(now+200000000), "0-1");
		dormitina(100);
		ilGestore.inserisciRisultato("Juve-Fiorentina", new Date(now+250000000), "0-3");
	}
}
