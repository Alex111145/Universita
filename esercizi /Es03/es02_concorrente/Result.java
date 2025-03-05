
public class Result {
	private int expectedVisits;     // numero totale di nodi da visitare
	private BinTree node;           // risultato: riferimento al nodo contenente il valore cercato
	private boolean success=false;  // successo della ricerca
	private int completedVisits=0;  // numero di nodi visitati
	public Result(int exp) {
		this.expectedVisits=exp;
		this.node=null;
		this.success=false;
		this.completedVisits=0;
	}
	public synchronized void incVisits(){
		this.completedVisits++;
	}
	public synchronized boolean isCompleted(){
		return (completedVisits==expectedVisits);
	}
	public synchronized boolean isSuccess(){
		return this.success;
	}
	public synchronized void setNode(BinTree n){
		this.node=n;		
	}
	public synchronized BinTree getNode(){
		return this.node;
	}
	public synchronized void setSuccess(){
		this.success=true;		
	}
}
