
public class Turno {
	int aChiTocca;
	Turno(int n){
		aChiTocca=n;
	}
	public synchronized void aspettaTurno(int chi) {
		while(aChiTocca!=chi) {
			try {
				wait();
			} catch (InterruptedException e) {	}
		}
	}
	public synchronized int chiEdiTurno() {
		return aChiTocca;
	}
	public synchronized void cambiaTurno() {
		aChiTocca=1-aChiTocca;
		notifyAll();
	}
}
