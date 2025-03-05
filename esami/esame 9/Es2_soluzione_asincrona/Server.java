import java.io.*;
import java.net.*;

public class Server {

	public static final int PORT = 2345;

	public static void main(String[] args) {
		TavoloGioco tavolo = new TavoloGioco(0);
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server ready");
			for(int n=0; n<2; n++) {
				Socket cliSocket = ss.accept();
				new Thread(new ServerThread(cliSocket, tavolo)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
