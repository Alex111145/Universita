import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom;

public class NewsUpdater extends Thread {
	NewsService theNewsServer;
	int count;
	public NewsUpdater(NewsService obj) {
		theNewsServer=obj;
		count=0;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(200, 600));
			} catch (InterruptedException e) { }
			try {
				theNewsServer.updateNews("notizia_"+count++);
			} catch (RemoteException e) { }
		}
	}
}