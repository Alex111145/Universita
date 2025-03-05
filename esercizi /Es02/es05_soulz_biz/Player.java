import java.util.concurrent.ThreadLocalRandom;

public class Player extends Thread {
	int myId;
	Table myTable;
	public Player(int id, Table t){
		this.myId=id;
		this.myTable=t;
		this.start();
	}
	public void run(){
		boolean continua=false;
		int dado1;
		int dado2;
		while(!myTable.finita()){
			myTable.aspettoTurno(myId);
			if(myTable.finita()){
				break;
			}
			continua=true;
			while(continua && !myTable.finita()){
				dado1=1+(int)(6*Math.random());
				dado2=1+(int)(6*Math.random());
				continua=(dado1==dado2);
				myTable.mossa(myId, dado1, dado2);
				if(myTable.finita()) {
					System.out.println("Giocatore "+myId+": ho vinto :-D");
					return;
				}
			}
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
			} catch (InterruptedException e1) {	}
		}
		System.out.println("Giocatore "+myId+": ho perso :-(");
	}
}

