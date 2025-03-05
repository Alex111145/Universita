import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Giocatore extends Thread {
	int identificatore;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket mySocket;
	Giocatore(int n){
		identificatore=n;
		this.setName("giocatore_"+n);
		try {
			mySocket = new Socket(InetAddress.getByName(null), Server.PORT);
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }
	}
	private void pensa() {
		int a=0;
		Random rnd = new Random();
		int c1=1+rnd.nextInt(10);
		int c2=1+rnd.nextInt(10);
		System.out.println(this.getName()+": pensa ... ");
		for(int i=0; i<c1*10000000; i++) {
			for(int j=0; j<c2*10000000; j++) {
				a=1-a;
			}
		}
		System.out.println(this.getName()+": ha finito di pensare.");
	}
	public void run() {
		String str;
		try {
			oos.writeObject(identificatore); // manda proprio id a server
			while(true) {
				pensa(); // simula il tempo passato a pensare alla prossima mossa
				str=(String)ois.readObject(); // riceve invito a muovere
				// NB: dovremmo controllare str ...
				oos.writeObject("mossa_giocatore_"+identificatore); // spedisce mossa
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("client: communication error. Terminating.");
		}
	}
}
