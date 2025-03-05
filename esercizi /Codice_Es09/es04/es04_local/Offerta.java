import java.io.Serializable;

public class Offerta implements Serializable {
  private static final long serialVersionUID = 1;
  private int value;
  private String proposer;
  private TipoEsito esito;
  public Offerta(int v, String w, TipoEsito e){
    this.value=v;
    this.proposer=w;
    this.esito= e;
  }
  public void setAggiudicata(){
	  esito=TipoEsito.Aggiudicata;
	  }
  public int getAmount(){
    return value;
  }
  public String getWho(){
    return proposer;
  }
  public TipoEsito getEsito() {
	  return esito;
  }
  public String toString() {
	  return "offerta da "+proposer+" per "+value+" ("+esito+")"; 
  }
}

