
public class Turno {
	int aChiTocca;
	Turno(int n){
		aChiTocca=n;
	}
	public synchronized void aspettaTurno(int chi) {
		System.out.println(Thread.currentThread().getName()+" attende turno ");
		while(aChiTocca!=chi) {
			try {
				wait();
			} catch (InterruptedException e) {	}
		}
		System.out.println(Thread.currentThread().getName()+" termina attesa ");
	}
	public synchronized int chiEdiTurno() {
		return aChiTocca;
	}
	public synchronized void cambiaTurno() {
		aChiTocca=1-aChiTocca;
		System.out.println(Thread.currentThread().getName()+" ha cambiato il turno. Ora e` "+aChiTocca);
		notifyAll();
	}
}
