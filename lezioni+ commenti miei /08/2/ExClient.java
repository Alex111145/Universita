import java.io.*;
import java.net.*;

public class ExClient {
	void exec() throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, 8080);
		try {
			System.out.println("Client connected: socket = " + socket); 
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			for (int i = 0; i < 10; i++) {
				System.out.println("Client: sending hello_" + i+" to server"); 
				out.println("hello_" + i);
			}
			out.println("END");
		} finally {
			System.out.println("Client: closing...");
			socket.close();
		}
	}
	public static void main(String[] args) throws IOException {
		new ExClient().exec();
	}
}