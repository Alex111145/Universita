import java.util.concurrent.*;

public class Main {
	static final int queueSize=4;
	BlockingQueue<Message> queue;
	Signal synchroSignal;
	Main(){
		queue = new ArrayBlockingQueue<Message>(queueSize);
		synchroSignal=new Signal();
	}

	private void exec() {
		Producer producer=new Producer(queue, synchroSignal);
		Consumer consumer=new Consumer(queue, synchroSignal);
		int nc=0;
		int np=0;
		new Thread(producer, "prod"+np++).start();
		new Thread(consumer, "cons"+nc++).start();
		while(true) {
			ProdCons pc=synchroSignal.waitSignal();
			if(pc==ProdCons.CONSUMER) {
				new Thread(consumer, "cons"+nc++).start();
			} else {
				new Thread(producer, "prod"+np++).start();
			}
		}
	}
	public static void main(String[] args) {
		new Main().exec();
	}
}
