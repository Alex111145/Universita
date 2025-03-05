import java.util.concurrent.ThreadLocalRandom;

public class PingPong extends Thread {
	int myId;
	String myName;
	Table table;
	public PingPong(int id, String n, Table t){
		this.myId=id;
		this.table=t;
		this.myName=n;
	}
	public void run(){
		for(;;){
			table.aspettoTurno(myId);
			System.out.println(myName+" usa risorsa");
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300, 800));
			} catch (InterruptedException e) { e.printStackTrace(); }
			table.fineTurno();
		}
	}
}

