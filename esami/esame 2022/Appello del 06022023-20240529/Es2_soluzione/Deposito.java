public class Deposito {
	private Informazione informazione;
	
	public Deposito() {
		informazione = new Informazione("default");
	}

	public synchronized void write(String message) {
		informazione = new Informazione(message);
		System.out.println("Deposito: new info is " + message);
		//aggiorno i client quando c'e` una nuova informazione
		notifyAll();
	}

	public synchronized Informazione read(long lastTime){
		while(lastTime>=informazione.getInfoTime()) {
			try {
				wait();
			} catch (InterruptedException e) {}	
		}
		return informazione;
	}
}
