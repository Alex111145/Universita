import java.util.concurrent.ThreadLocalRandom;

public class Giocatore extends Thread {
	int identificatore;
	TavoloGioco tavolo;
	Turno turno;
	boolean flag; // dice se e` il proprio turno
	Giocatore(int n, TavoloGioco tg, Turno t){
		super("giocatore_"+n);
		identificatore=n;
		tavolo=tg;
		turno=t;
		setFlag(n==0);
	}
	private void pensa() {
		while(true) {
			System.out.println(this.getName()+": pensa ... ");
			try { 
				Thread.sleep(ThreadLocalRandom.current().nextInt(2000)); 
			} catch (InterruptedException e) { }
			if(getFlag()) {
				break;
			}
		}
		System.out.println(this.getName()+": ha finito di pensare.");
	}
	public void run() {
		while(true) {
			new Attendente(identificatore, turno, this).start();
			pensa(); // simula il tempo passato a pensare alla prossima mossa
			tavolo.mossa(identificatore, "mossa_"+identificatore);
			turno.cambiaTurno();
			setFlag(false);
		}
	}
	public synchronized void setFlag(boolean f) {
		flag=f;
	}
	public synchronized boolean getFlag() {
		return flag;
	}
}
