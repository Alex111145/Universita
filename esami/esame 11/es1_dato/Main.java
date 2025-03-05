
public class Main {
	
	private void exec() {
		Gioco ilGioco = new Gioco();
		Giocatore giocatore1=new Giocatore(1, ilGioco);
		Giocatore giocatore2=new Giocatore(2, ilGioco);
		Arbitro arbitro=new Arbitro(ilGioco);
	}
	
	public static void main(String[] args) {
		new Main().exec();
	}
}
