import java.util.concurrent.*;

public class Produttore extends Thread {
	Coda buffer;
	public Produttore(String s, Coda c){
		super(s);
		this.buffer=c;
	}
	public void run(){
		int i=0;
		for(;;){
			try {
				Thread.sleep(ThreadLocalRandom.current().
						nextInt(10, 100));
			} catch (InterruptedException e) {  }
			buffer.setItem(i++);
		}
	}
}


