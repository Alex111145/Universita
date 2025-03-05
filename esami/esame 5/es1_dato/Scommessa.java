
public class Scommessa {
	Partita laPartita;
	String nomeScommettitore;
	int importo;
	String descrizione; // ad es. "Milan vincente"
	EsitoScommessa esito; 
	
	Scommessa(Partita p, String n, int q, String s){
		laPartita=p;
		nomeScommettitore=n;
		importo=q;
		descrizione=s;
		esito=EsitoScommessa.Indeterminata;
	}
	public String toString() {
		return laPartita.getNome()+"("+laPartita.getData()+") "+descrizione+" da "+nomeScommettitore+"da euro "+importo+":"+esito;
	}
	
	public Partita getLaPartita() {
		return laPartita;
	}

	public String getNomeScommettitore() {
		return nomeScommettitore;
	}

		public int getImporto() {
		return importo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public EsitoScommessa getEsito() {
		return esito;
	}

	public void setEsito(EsitoScommessa e) {
		this.esito = e;
	}

}
