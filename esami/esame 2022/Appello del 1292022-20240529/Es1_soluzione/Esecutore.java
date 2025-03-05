public class Esecutore extends Thread{
	int mioId;
	Deposito depositoIn;
	Deposito depositoOut;
	public Esecutore(int id, Deposito dIn, Deposito dOut) {
		mioId=id;
		depositoIn=dIn;
		depositoOut=dOut;
		start();
	}
	public void run() {
		final int numIterazioni=3;
		String risposta;
		RichiestaRisposta ricrisp=null;
		for(int i=0; i<numIterazioni; i++) {
			ricrisp=depositoIn.LeggiQualunque();
			// System.out.println("Esecutore "+mioId+" estratto");
			try { Thread.sleep(500); } catch (InterruptedException e) { }
			risposta="risposta a "+ricrisp.getRichiesta();
			ricrisp.setRisposta(risposta);
			depositoOut.Aggiungi(ricrisp);
			System.out.println("Esecutore "+mioId+" Q: "+ricrisp.getRichiesta()+", A: "+risposta);
		}
		System.out.println("Esecutore "+mioId+" termina ");
	}
}
