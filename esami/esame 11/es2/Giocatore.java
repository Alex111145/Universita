import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Giocatore extends Thread {
	int miaId;
	ObjectOutputStream out;
	ObjectInputStream in;
	Random rnd=new Random();
	public Giocatore(int id) {
		miaId=id;
	}
	public void run() {
		InetAddress addr;
		try {
			addr = InetAddress.getByName(null);
			Socket mioSocket=new Socket(addr, 8999);
			out = new ObjectOutputStream(mioSocket.getOutputStream());
			in = new ObjectInputStream(mioSocket.getInputStream());
			System.out.println("Giocatore "+miaId+": inizio");
			while(true) {
				int n=-99;
				boolean approvata=false;
				System.out.println("Giocatore "+miaId+" aspetta turno");
				out.writeObject("aspettaTurno");
				out.writeObject(miaId);
				in.readObject();
				while(!approvata) {
					n=rnd.nextInt(100);
					Mossa laMossa=new Mossa(miaId, n);
					System.out.println("Giocatore "+miaId+" fa mossa "+n);
					out.writeObject("mossa");
					out.writeObject(miaId);
					out.writeObject(laMossa);
					in.readObject();
					System.out.println("Giocatore "+miaId+" legge esito");
					out.writeObject("letturaEsito");
					approvata=(boolean)in.readObject();
				}
				//			System.out.println("Giocatore "+miaId+" ha fatto mossa "+n);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
			new Giocatore(1).start();
			new Giocatore(2).start();
	}
}
