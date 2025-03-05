import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Consumatore extends Thread {
	private BlockingQueue<String> theQueue;
	String myName;
	String v;
	public Consumatore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myName="consumer_"+id;
	}
	private void qualcosaDiUtile() {
		// metodo che simula qualche attivita` utile del consumatore
		int n=0;
		System.out.println(myName+" faccio qualcosa ***************************");
		for(int i=0; i<100000; i++) {
			for(int j=0; j<100000; j++) {
				n=1-n;
			}
		}
	}
	private void lettura() {
		boolean finito=false;
		while(!finito) {
			try {
				v=theQueue.remove();
				finito=true;
			} catch (NoSuchElementException e) {
				// la lettura non ha avuto successo: faccio qualcosa, poi ci riprovero`
				qualcosaDiUtile();
			}
		}
	}
	public void run(){
		for(;;){
			lettura();
			System.out.println(myName+" read "+v);
			int tempoCons=ThreadLocalRandom.current().nextInt(100, 300);
			try {
				Thread.sleep(tempoCons); // simula il tempo per il consumo
			} catch (InterruptedException e2) {	} 
		}				
	}
}

