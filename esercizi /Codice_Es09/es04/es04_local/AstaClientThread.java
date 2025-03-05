import java.util.Random;

public class AstaClientThread extends Thread {

	private final double myIncrease=1.1;
	private String myName;
	private Asta lAsta;
	private Offerta OffCorrente=null;

	AstaClientThread(int i, Asta a) {
		this.myName="partecipante_"+i;
		this.lAsta=a;
	}
	public void run() {
		Random rnd=new Random();
		int maxTries = 3+(int)rnd.nextInt(4);
		int numTries=0;
		int tempoPerPensare;
		System.out.println(myName+" inizio");
		while(true){
			OffCorrente=lAsta.leggi_offerta();
			tempoPerPensare=100+rnd.nextInt(300);
			try {
				Thread.sleep(tempoPerPensare);
			} catch (InterruptedException e) {}
			TipoEsito esito=OffCorrente.getEsito();
			System.out.println(myName+" ho letto "+OffCorrente);
			int am=OffCorrente.getAmount();
			String chi=OffCorrente.getWho();
			if(esito==TipoEsito.Aggiudicata) {
				if(chi.equals(myName)) {
					System.out.println(myName+": ho vinto!");
				} else {
					System.out.println(myName+": ho perso!");
				}
				break;
			} else {
				if(numTries<maxTries) {
					if(!chi.equals(myName)) {
						numTries++;
						System.out.println(myName+" offering "+(int) (am*myIncrease));
						lAsta.nuovaOfferta(new Offerta((int) (am*myIncrease), myName, TipoEsito.Proposta));
					}
				} else { // ho finito i tentativi
					if(!chi.equals(myName)) {
						System.out.println(myName+" lascio! ");
						break;
					}
				}
			}
		}
	}
}


