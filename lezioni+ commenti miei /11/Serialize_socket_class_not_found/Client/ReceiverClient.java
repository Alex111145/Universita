import java.io.*;
import java.net.*;

public class ReceiverClient {
	private void exec() throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		Socket socket = new Socket(addr, 9996);
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Object p = ois.readObject();
			System.out.print("Object is: " + p); 
		} catch(ClassNotFoundException e){
			System.out.print("Object class unknown");
		} finally {
			socket.close();
		}		
	}
	public static void main(String[] args)  {
		try {
			new ReceiverClient().exec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
