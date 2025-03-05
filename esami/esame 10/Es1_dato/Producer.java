import java.util.concurrent.*;
public class Producer implements Runnable {
	private BlockingQueue<Message> queue;
	public Producer(BlockingQueue<Message> q) {
		this.queue=q; 
	}
	public void run(){
		int i=0;
		String mioNome=Thread.currentThread().getName();
		boolean finito=false;
		while(!finito) {
			Message msg = new Message (mioNome+"_" + i++);
			try {
				Thread.sleep(1000);
				queue.put(msg);
				System.out.println(mioNome+" produced " + msg.getMsg());
			} catch (InterruptedException e) { }
			finito=ThreadLocalRandom.current().nextBoolean();
		}
		System.out.println(mioNome+" finished");
	}
}
