public class MultiClient {
	private void exec() {
		Thread g1 =  new Giocatore(0); 
		Thread g2 =  new Giocatore(1); 
		g1.start();
		g2.start();
	}
	public static void main(String[] args) {
		new MultiClient().exec();
	}
}
