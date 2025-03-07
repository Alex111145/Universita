import java.io.*;
import java.net.*;

public class SenderServer {
	private void exec() throws IOException {
		ServerSocket s = new ServerSocket(9996);
		Point p = new Point(6,11);
		Socket sc = s.accept();
		try {
			ObjectOutputStream  oos = new ObjectOutputStream(sc.getOutputStream());
			oos.writeObject(p); // scrittura dell'oggetto Punto
			oos.close();
		} finally {
			sc.close();
			s.close();
		}
	}
	public static void main(String[] args) {
		try {
			new SenderServer().exec();
		} catch (IOException e) {
			System.err.println("Server failure");
		}
	}
}
