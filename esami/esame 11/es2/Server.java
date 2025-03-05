import java.net.*;
import java.io.*;

public class Server {
	static final int PORT = 8999;
	public void exec()throws IOException {
		Gioco ilGioco=new Gioco();
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepts connection");
				new SlaveThread(socket, ilGioco).start();
			}
		} finally {
			s.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server().exec();
	}
}
