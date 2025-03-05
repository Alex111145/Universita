import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Updater {
	String name ;
	Random rndRandom;
	ObjectInputStream in;
	ObjectOutputStream out;
	Updater() {
		rndRandom = new Random();
		this.name="Updater_" + rndRandom.nextInt(100);
	}
	public void exec() throws IOException{
		InetAddress myAddress = InetAddress.getByName(null);
		Socket cSocket = new Socket(myAddress, 9999);
		System.out.println("Updater connected");
		out = new ObjectOutputStream(cSocket.getOutputStream());
		in = new ObjectInputStream(cSocket.getInputStream());

		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(500));
			} catch (InterruptedException e) { }
			out.writeObject("write");	
			String messageString = "Messaggio_" + rndRandom.nextInt(1000);
			out.writeObject(messageString);
			System.out.println("Updater requested to write "+ messageString);
		}

		out.writeObject("END");
		out.flush();
		cSocket.close();
	}

	public static void main(String[] args) {
		try {
			new Updater().exec();
		} catch (IOException e) {
			System.out.println("Updater KO");
		}
	}
}
