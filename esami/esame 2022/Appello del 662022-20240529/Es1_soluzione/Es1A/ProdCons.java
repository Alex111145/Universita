
public class ProdCons {
	private static final int bufsize=4;
	private static final int numIterations=8;
	private static final int numConsumatori=2;
	private static final int numProduttori=2;
	private static final int numLettori=2;
	private void exec() {
		Produttore[] iProduttori=new Produttore[numProduttori];
		Consumatore[] iConsumatori=new Consumatore[numConsumatori];
		Lettore[] iLettori=new Lettore[numLettori];
		Coda buffer=new Coda(bufsize);
		for(int j=0; j<numProduttori; j++) {
			iProduttori[j]=new Produttore("prod_"+j, buffer, numIterations);
		}
		for(int j=0; j<numConsumatori; j++) {
			iConsumatori[j]=new Consumatore("cons_"+j, buffer, numIterations);
		}
		for(int j=0; j<numLettori; j++) {
			iLettori[j]=new Lettore("lett_"+j, buffer, numIterations);
		}
		for(int j=0; j<numProduttori; j++) {
			iProduttori[j].start();
		}
		for(int j=0; j<numConsumatori; j++) {
			iConsumatori[j].start();
		}
		for(int j=0; j<numLettori; j++) {
			iLettori[j].start();
		}
		try {
			for(int j=0; j<numProduttori; j++) {
				iProduttori[j].join();
			}
			for(int j=0; j<numConsumatori; j++) {
				iConsumatori[j].join();
			}
		} catch (InterruptedException e) { }
		System.out.println("main: termino");
		System.exit(0); // esce, terminando eventuali lettori ancora attivi.
	}
	public static void main(String[] args) {
		new ProdCons().exec();
	}
}