import java.util.concurrent.*;
public class Consumer implements Runnable {
	private BlockingQueue<Message> queue;
	public Consumer (BlockingQueue<Message> q) {
		this.queue=q;
	}
	public void run() {
		Message msg ;
		String mioNome=Thread.currentThread().getName();
		try {
			boolean finito=false;
			while(!finito) {
				msg = queue.take();
				System.out.println(mioNome+" consumed "+msg.getMsg());
				Thread.sleep(1000);
				finito=ThreadLocalRandom.current().nextBoolean();
			}
			System.out.println(mioNome+" finished");
		} catch (InterruptedException e) { }
	}
}
