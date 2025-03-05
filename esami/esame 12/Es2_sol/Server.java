import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static final int PORT = 8999;
	
	public void exec()throws IOException {
		DataObject data=new DataObject();
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server inizia");
		new SlaveProvider(data).start();
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepts connection");
				new SlaveThread(socket, data).start();
			}
		} finally {
			s.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server().exec();
	}
}
