import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Produttore extends Thread {
	private BlockingQueue<String> theQueue;
	String myName;
	String v;
	public Produttore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myName="produttore_"+id;
	}
	private void qualcosaDiUtile() {
		// metodo che simula qualche attivita` utile del produttore
		int n=0;
		System.out.println(myName+" faccio qualcosa *******************************");
		for(int i=0; i<100000; i++) {
			for(int j=0; j<100000; j++) {
				n=1-n;
			}
		}
	}
	private void scrittura() {
		boolean finito=false;
		while(!finito) {
			try {
				theQueue.add(v);
				// scrittura effettuata
				finito=true;
			} catch(IllegalStateException e) {
				// scrittura non effettuata: faccio qualcosa poi ci riprovo
				qualcosaDiUtile();				}
		}
	}
	public void run(){
		int i=0;
		for(;;){
			int tempoProd=ThreadLocalRandom.current().nextInt(100, 300);
			try {
				Thread.sleep(tempoProd); // simula il tempo per produrre
			} catch (InterruptedException e2) {	}
			v="str_"+myName+"_"+(i++);
			scrittura();
			System.out.println(myName+" wrote "+v);
		}				
	}
}

