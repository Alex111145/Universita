public class MultiClient {
	private void exec() {
		TavoloGioco tavolo = new TavoloGioco(0);
		Thread g1 =  new Giocatore(0, tavolo); 
		Thread g2 =  new Giocatore(1, tavolo); 
		g1.start();
		g2.start();
	}
	public static void main(String[] args) {
		new MultiClient().exec();
	}
}
