
public class TavoloGioco {
	int aChiTocca;
	TavoloGioco(int n){
		aChiTocca=n;
	}
	public synchronized void aspettaTurno(int chiMuove) {
		System.out.println("tavolo: aspetta "+ chiMuove);
		while(aChiTocca!=chiMuove) {
			try {
				System.out.println("tavolo: in attesa "+ chiMuove);
				wait();
			} catch (InterruptedException e) { }
		}
		System.out.println("tavolo: fine attesa "+ chiMuove);
	}
	public synchronized int chiDiTurno() {
		return aChiTocca;
	}
	public synchronized void mossa(int chiMuove, String m) {
		if(aChiTocca==chiMuove) {
			System.out.println("***************** Giocatore "+chiMuove+" ha fatto mossa "+m);
			aChiTocca=1-aChiTocca;
			notifyAll();
		}
	}
}
