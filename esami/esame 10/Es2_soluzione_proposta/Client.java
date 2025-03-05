import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {
	int mioId;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Client() {
	}
	public void exec() throws IOException, ClassNotFoundException {
	    int i = 0;
		InetAddress addr = InetAddress.getByName(null);
		Socket mioSocket=new Socket(addr, 8999);
		out = new ObjectOutputStream(mioSocket.getOutputStream());
		in = new ObjectInputStream(mioSocket.getInputStream());
		System.out.println("Client: inizio");
	    ChildThread childThread=new ChildThread();
		while(true){
			String str=(String) in.readObject();
			System.out.println("Client received "+str+"; staring child_"+i);
	        new Thread(childThread, "child_"+(i++)).start();
		}
	}
	public static void main(String args[]) {
		try {
			new Client().exec();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("client KO");
		}
	}
}
