import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final static int SERVER_PORT = 9999;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Deposito deposito = new Deposito();
		try {
			while (true) {
				Socket cSocket = serverSocket.accept();
				System.out.println("server accepted connection");
				new SlaveThread(cSocket, deposito);
			}
		}finally {
			serverSocket.close();
		}
	}
}
