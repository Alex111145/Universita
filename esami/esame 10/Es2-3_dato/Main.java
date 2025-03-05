import java.util.concurrent.ThreadLocalRandom;

public class Main {
  public static void main(String[] args) {
    ChildThread childThread=new ChildThread();
    int i = 0;
    while(true) {
        new Thread(childThread, "child_"+(i++)).start();
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
		} catch (InterruptedException e) {}
    }
  }
}
