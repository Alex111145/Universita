public class Giocatore extends Thread {
	int identificatore;
	TavoloGioco tavolo;
	Thread avversario;
	Giocatore(int n, TavoloGioco t){
		identificatore=n;
		tavolo=t;
		this.setName("giocatore_"+n);
	}
	public void setAvversario(Thread t) {
		avversario=t;
	}
	private void pensa() {
		int a=0;
		System.out.println(this.getName()+": pensa ... ");
		while(true) {
			if(Thread.interrupted()) {
				break;
			}
			for(int i=0; i<100000000; i++) {
				a=1-a;
			}
		}
		System.out.println(this.getName()+": ha finito di pensare.");
	}
	public void run() {
		if(identificatore==1) {
			avversario.interrupt();	
		}
		while(true) {
			pensa(); // simula il tempo passato a pensare alla prossima mossa
			tavolo.mossa(identificatore, "mossa_"+identificatore);
			avversario.interrupt();
		}
	}
}
