import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Giocatore extends Thread {
	int identificatore;
	boolean toccaAme;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket mySocket;
	Giocatore(int n){
		identificatore=n;
		toccaAme=false;
		this.setName("giocatore_"+n);
		try {
			mySocket = new Socket(InetAddress.getByName(null), Server.PORT);
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }
	}
	public synchronized void setFlag(boolean b) {
		toccaAme=b;
		System.out.println("settato flag "+b+" per "+identificatore);
	}
	private void pensa() {
		System.out.println(this.getName()+": inizia a pensare con flag " + toccaAme);
		while(true) {
			try { Thread.sleep(1000); } catch (InterruptedException e) {	}
			System.out.println(this.getName()+": tocca a me = "+toccaAme);
			if(toccaAme) {
				break;
			}
		}
		System.out.println(this.getName()+": ha finito di pensare.");
	}
	public void run() {
		try {
			oos.writeObject(identificatore);
			int i=0;
			while(true) {
				new Attendente(identificatore, ois, this).start();
				Thread.yield();
				pensa(); // simula il tempo passato a pensare alla prossima mossa
				setFlag(false);
				System.out.println(this.getName()+": sto per muovere");
				oos.writeObject("mossa_giocatore_"+identificatore+"_"+i);
				oos.flush();
				System.out.println(this.getName()+": mandato mossa");
				if(i<10) {
					i++;
				} else {
					oos.writeObject("FineServizio");
				}
			}
		} catch (IOException e) {
			System.err.println("client: communication error. Terminating.");
			System.exit(0); // serve per terminare anche thread Attendente
		}
	}

}
