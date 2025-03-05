import java.util.concurrent.ThreadLocalRandom;

public class MainClass {
	final int numClienti=12;
	private void exec() {
		Negozio negozio=new Negozio();
		Barbiere barb=new Barbiere(negozio);
		barb.start();
		Cliente[] clienti;
		clienti=new Cliente[numClienti];
		for(int ic=0; ic<numClienti; ic++){
			clienti[ic]=new Cliente("Cliente_"+ic, negozio);
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(50,120));
			} catch (InterruptedException e) { }
		}
		for(int ic=0; ic<numClienti; ic++){
			try {
				clienti[ic].join();
			} catch (InterruptedException e) {}
		}
		System.out.println("ora di chiudere");
		barb.interrupt();
	}
	public static void main(String[] args) {
		Thread.currentThread().setName("main");
		new MainClass().exec();
	}
}