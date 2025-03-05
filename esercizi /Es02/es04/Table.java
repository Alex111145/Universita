public class Table {
	private int aChiTocca;
	public Table(int primo) {
		this.aChiTocca=primo;
	}
	public synchronized void aspettoTurno(int chi){
		while(aChiTocca!=chi){
			try {
				wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	public synchronized void fineTurno() {
		aChiTocca=1-aChiTocca;
		notifyAll();
	}
}



