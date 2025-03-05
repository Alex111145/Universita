import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Produttore extends Thread {
	private BlockingQueue<String> theQueue;
	int myId;
	String v;
	public Produttore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myId=id;
	}
	private void qualcosaDiUtile() {
		// metodo che simula qualche attivita` utile del produttore
		int n=0;
		for(int i=0; i<10000; i++) {
			for(int j=0; j<10000; j++) {
				n=1-n;
			}
		}
	}
	private void scrittura() {
		try {
			boolean finito=false;
			while(!finito) {
				if(theQueue.offer(v, 70, TimeUnit.MILLISECONDS)) {
					// scrittura effettuata
					finito=true;
				} else {
					// scrittura non effettuata: faccio qualcosa poi ci riprovo
					System.out.println("Producer "+myId+" faccio qualcosa *******************************");
					qualcosaDiUtile();
				}
			}
		} catch (Exception e1) { }	
	}
	public void run(){
		int i=0;
		for(;;){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			} catch (InterruptedException e2) {	}
			v="str_"+myId+"_"+(i++);
			scrittura();
			System.out.println("Producer "+myId+" wrote "+v);
		}				
	}
}

