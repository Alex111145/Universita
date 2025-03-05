public class Asta {
	private Offerta offertaCorrente;
	private double rialzo_min;
	private long ultimoCambiamento;
	Asta(int ba, double rialzmin){
		this.offertaCorrente=new Offerta(ba, "nessuno", TipoEsito.Respinta);
		this.rialzo_min=rialzmin;
		ultimoCambiamento=System.currentTimeMillis();
	}
	public synchronized long leggiTempo() {
		return ultimoCambiamento;
	}
	public void aggiudica() {
		offertaCorrente.setAggiudicata();
		System.out.println("asta: "+offertaCorrente);
	}
	public synchronized Offerta leggi_offerta() {
		return offertaCorrente;
	}
	public synchronized boolean nuovaOfferta(Offerta o){
		System.out.println("asta: ricevo "+o);
		System.out.println("asta: accetto se "+o.getAmount()+">="+offertaCorrente.getAmount()*rialzo_min);
		if(o.getAmount() >= offertaCorrente.getAmount()*rialzo_min){
			offertaCorrente=new Offerta(o.getAmount(), o.getWho(), TipoEsito.Accettata);
			System.out.println("asta: accettata ");
			ultimoCambiamento=System.currentTimeMillis();
			return(true);
		} else{
			return(false);
		}
	}
}

