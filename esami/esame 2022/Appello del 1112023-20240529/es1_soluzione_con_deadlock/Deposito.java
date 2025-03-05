import java.util.LinkedList;
import java.util.List;

public class Deposito {
	static final int MC=10;
	List< Risorsa > DA;
	List< Risorsa > DB;
	Deposito(){
		DA = new LinkedList<Risorsa>();
		DB = new LinkedList<Risorsa>();
		for(int i=0; i<MC/2; i++) {
			DA.add(new Risorsa("A"));
			DB.add(new Risorsa("B"));
		}
	}
	public synchronized void switchElement(boolean AtoB) {
		if(AtoB) {
			while(DA.isEmpty() || DB.size()==MC) {
				try {
					wait();
				} catch (InterruptedException e) { }
			}
			Risorsa temp = DA.remove(0);
			temp.changeTypeTo("B");
			DB.add(temp);
		} else {
			while(DB.isEmpty() || DA.size()==MC) {
				try {
					wait();
				} catch (InterruptedException e) { }
			}
			Risorsa temp = DB.remove(0);
			temp.changeTypeTo("A");
			DA.add(temp);
		}
		stampaTutto();
		notify();
	}
	public synchronized void stampaTutto() {
		System.out.println("Deposito, listaA:");
		for(Risorsa rr: DA) {
			System.out.println(rr);
		}
		System.out.println("Deposito, listaB:");
		for(Risorsa rr: DB) {
			System.out.println(rr);
		}
	}
}
