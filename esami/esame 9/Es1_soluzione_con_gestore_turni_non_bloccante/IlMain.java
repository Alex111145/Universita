public class IlMain {
	private void exec() {
		Turno t = new Turno(0);
		TavoloGioco tavolo=new TavoloGioco();
		Thread g1 =  new Giocatore(0, tavolo, t); 
		Thread g2 =  new Giocatore(1, tavolo, t); 
		g1.start();
		g2.start();
	}
	public static void main(String[] args) {
		new IlMain().exec();
	}
}
