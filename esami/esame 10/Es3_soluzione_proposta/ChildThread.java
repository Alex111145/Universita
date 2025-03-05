import java.util.concurrent.*;
public class ChildThread implements Runnable {
	public ChildThread () {
	}
	public void run() {
		String mioNome=Thread.currentThread().getName();
		boolean finito=false;
		try {
			while(!finito) {
				System.out.println(mioNome+" working ");
				Thread.sleep(1000);
				finito=ThreadLocalRandom.current().nextBoolean();
			}
			System.out.println(mioNome+" finished");
		} catch (InterruptedException e) { }
	}
}
