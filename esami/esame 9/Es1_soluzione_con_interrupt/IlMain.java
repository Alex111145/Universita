public class IlMain {
	private void exec() {
		TavoloGioco tavolo=new TavoloGioco();
		Giocatore g1 =  new Giocatore(0, tavolo); 
		Giocatore g2 =  new Giocatore(1, tavolo); 
		g1.setAvversario(g2);
		g2.setAvversario(g1);
		g1.start();
		g2.start();
	}
	public static void main(String[] args) {
		new IlMain().exec();
	}
}
