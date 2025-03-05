import java.util.concurrent.*;

public class Main {
	static final int queueSize=4;
  public static void main(String[] args) {
    BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(queueSize);
    Producer producer=new Producer(queue);
    Consumer consumer=new Consumer(queue);
    for(int i=0; i<3; i++) {
        new Thread(producer, "prod"+i).start();
        new Thread(consumer, "cons"+i).start();
    }
  }
}
