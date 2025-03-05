
public class ProdCons {
	final int BUFFSIZE=4;
	void exec(){
		Coda cella=new Coda(4);
		new Produttore("Prod1", cella).start();
		new Consumatore("Cons1", cella).start();
		new Produttore("Prod2", cella).start();
		new Consumatore("Cons2", cella).start();
	}
	public static void main(String[] args) {
		new ProdCons().exec();
	}
}
