
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Client {
	String name ;
	Random rndRandom;
	ObjectInputStream in;
	ObjectOutputStream out;
	Client() {
		rndRandom = new Random();
		this.name="Client_" + rndRandom.nextInt(100);
	}
	public void exec() throws IOException, ClassNotFoundException{
		InetAddress myAddress = InetAddress.getByName(null);
		Socket cSocket = new Socket(myAddress, 9999);
		out = new ObjectOutputStream(cSocket.getOutputStream());
		in = new ObjectInputStream(cSocket.getInputStream());
		long lastTime=System.currentTimeMillis()-1000000;
		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			Informazione lettura;
			out.writeObject("read");
			out.writeObject(lastTime);
			lettura = (Informazione)in.readObject();
			System.out.println(name+" leggo "+lettura);
			lastTime=lettura.getInfoTime();
		}

		out.writeObject("END");
		out.flush();
		cSocket.close();
	}

	public static void main(String[] args){
		try {
			new Client().exec();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Client KO");
		}
	}
}
