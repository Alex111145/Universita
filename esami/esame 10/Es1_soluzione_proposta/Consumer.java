import java.util.concurrent.*;
public class Consumer implements Runnable {
	private BlockingQueue<Message> queue;
	private Signal synchSignal;
	public Consumer (BlockingQueue<Message> q, Signal s) {
		this.queue=q;
		synchSignal=s;
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
			synchSignal.incConsumers();
			System.out.println(mioNome+" finished");
		} catch (InterruptedException e) { }
	}
}
