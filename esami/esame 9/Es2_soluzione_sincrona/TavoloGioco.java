public class TavoloGioco {
	int aChiTocca;
	TavoloGioco(int n){
		aChiTocca=n;
	}
	public synchronized void aspettaTurno(int chiMuove) {
		while(aChiTocca!=chiMuove) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
	}
	public synchronized int chiDiTurno() {
		return aChiTocca;
	}
	public synchronized void mossa(int chiMuove, String m) {
		if(aChiTocca==chiMuove) {
			System.out.println("Giocatore "+chiMuove+" ha fatto mossa "+m);
			aChiTocca=1-aChiTocca;
			notifyAll();
		}
	}
}
