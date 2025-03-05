import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom; 

public class NewsClientThread extends Thread {
	private static int threadcount = 0;
	int mioId;
	NewsService mioNewsServer;
	public NewsClientThread(int id, NewsService news) {
		mioId=id;
		mioNewsServer=news;
		synchronized(NewsClientThread.class) {
			threadcount++;
		}
		start();
	}
	public void run() {
		String st="";
		for(int i=0; i<8; i++){
			try {
				st=mioNewsServer.readNews();
			} catch (RemoteException e) {
				System.out.println("client "+mioId+": connection KO");
				break;
			}
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(200, 500));
			} catch (InterruptedException e) { 	}
			System.out.println("client "+mioId+": ho letto "+st);
		}
		System.out.println("client "+mioId+": finito ");
		synchronized(NewsClientThread.class) {
			threadcount--;
		}
	}
	public static int threadCount() {
		return threadcount;
	}
}
