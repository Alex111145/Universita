import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Arbitro {
	ObjectOutputStream out;
	ObjectInputStream in;
	Random rnd=new Random();
	public Arbitro() {
	}
	public void exec() throws IOException, ClassNotFoundException {
	    InetAddress addr = InetAddress.getByName(null);
		Socket mioSocket=new Socket(addr, 8999);
		out = new ObjectOutputStream(mioSocket.getOutputStream());
		in = new ObjectInputStream(mioSocket.getInputStream());
		System.out.println("Arbitro: inizio");
		while(true) {
			System.out.println("Arbitro aspetta");
			out.writeObject("attesaMossa");
			in.readObject();
			System.out.println("Arbitro legge mossa corrente");
			out.writeObject("letturaMossaCorrente");
			Mossa m = (Mossa)in.readObject();
			boolean mossaOK=m.getValore()<60;
			System.out.println("Arbitro decide "+(mossaOK?" OK":"KO"));
			out.writeObject("approva");
			out.writeObject(mossaOK);
			in.readObject();
		}
	}
	public static void main(String args[]) {
		try {
			new Arbitro().exec();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("client KO");
		}
	}
}
