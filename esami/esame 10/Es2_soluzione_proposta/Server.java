import java.net.*;
import java.io.*;

public class Server {
	static final int PORT = 8999;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server inizia");
		try {
			Socket socket = s.accept();
			System.out.println("Server accepted connection");
			new SlaveThread(socket).start();
		} finally {
			s.close();
		}
	}
}
