
public class UtenteDato extends Thread {
	Dato ilDato;
	UtenteDato(int i, Dato d){
		super("utente_"+i);	
		ilDato=d;
		start();
	}
	private void elaboro() {
		int a=0;
		System.out.println(this.getName()+": elaboro ... ");
		for(int i=0; i<1000000; i++) {
			for(int j=0; j<10000000; j++) {
				a=1-a;
			}
		}
	}
	public void run() {
		String str;
		while(true){
			elaboro();
			// modificare: il dato va letto solo se e` cambiato
			str=ilDato.getIlDato();
			System.out.println(this.getName()+": letto "+str);
		}
	}
}
