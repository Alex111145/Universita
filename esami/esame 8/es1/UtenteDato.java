
public class UtenteDato extends Thread {
	Dato ilDato;
	UtenteDato(int i, Dato d){
		super("utente_"+i);	
		ilDato=d;
		start();
	}
	private boolean elaboro() {
		int a=0;
		System.out.println(this.getName()+": elaboro ... ");
		for(int i=0; i<100000000; i++) {
			if(Thread.interrupted()) {
				return true;
			}
			for(int j=0; j<10000000; j++) {
				a=1-a;
			}
		}
		return false;
	}
	public void run() {
		String str;
		while(true){
			if(elaboro()) {
				// il dato viene letto solo se e` cambiato
				str=ilDato.getIlDato();
				System.out.println(this.getName()+": letto "+str);
			}
		}
	}
}
