import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;


public class Reader extends Thread {
	int miaId;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Reader(int id) {
		miaId=id;
	}
	public void run() {
		InetAddress addr;
		String s;
		try {
			addr = InetAddress.getByName(null);
			Socket mioSocket=new Socket(addr, 8999);
			out = new ObjectOutputStream(mioSocket.getOutputStream());
			in = new ObjectInputStream(mioSocket.getInputStream());
			System.out.println("Reader "+miaId+": inizio");
			while(true) {
				System.out.println("Reader "+miaId+" cerca di leggere");
				out.writeObject("get");
				s=(String)in.readObject();
				System.out.println("Reader "+miaId+" ha letto "+s);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		Random rnd=new Random();
		new Reader(rnd.nextInt(1000)).start();
	}
}
