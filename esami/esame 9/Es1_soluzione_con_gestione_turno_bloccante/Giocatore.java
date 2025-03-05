import java.util.Random;

public class Giocatore extends Thread {
	int identificatore;
	TavoloGioco tavolo;
	Turno turno;
	Giocatore(int n, TavoloGioco tg, Turno t){
		identificatore=n;
		tavolo=tg;
		turno=t;
		this.setName("giocatore_"+n);
	}
	private void pensa() {
		int a=0;
		Random rnd = new Random();
		int c1=1+rnd.nextInt(30);
		int c2=1+rnd.nextInt(30);
		System.out.println(this.getName()+": pensa ... "+c1+" "+c2);
		for(int i=0; i<c1*10000000; i++) {
			for(int j=0; j<c2*10000000; j++) {
				a=1-a;
			}
		}
		System.out.println(this.getName()+": ha finito di pensare.");
	}
	public void run() {
		while(true) {
			pensa(); // simula il tempo passato a pensare alla prossima mossa
			turno.aspettaTurno(identificatore);
			tavolo.mossa(identificatore, "mossa_"+identificatore);
			turno.cambiaTurno();
		}
	}
}
