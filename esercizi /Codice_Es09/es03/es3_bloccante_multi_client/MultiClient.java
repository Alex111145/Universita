import java.io.*;

public class MultiClient {
	static final int MAX_THREADS = 4;
	static final int CLIENT_THREADS_TOT = 8;
	public static void main(String[] args) throws IOException, InterruptedException {
		int i=0;
		while (i<CLIENT_THREADS_TOT) {
			if (WarnClientImpl.threadCount() < MAX_THREADS){
				new WarnClientImpl(i++);
			} else {
				Thread.sleep(200);
			}
		}
	}
}
