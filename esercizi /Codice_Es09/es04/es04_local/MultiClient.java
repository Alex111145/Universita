
public class MultiClient {
	static final int CLIENT_THREADS_TOT = 4;
	Asta lAsta;
	private void exec() {
		lAsta=new Asta(1000, 1.04);
		for (int i=0; i<CLIENT_THREADS_TOT; i++) {
			new AstaClientThread(i, lAsta).start();
			try { Thread.sleep(200); } catch (InterruptedException e) { }
		}
	}
	public static void main(String[] args) {
		new MultiClient().exec();
	}
}
