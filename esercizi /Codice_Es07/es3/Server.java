import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
	public static final int PORT=9999;
	final static int BufferCapacity=8;
	public static void main(String[] args) throws IOException {
		BlockingQueue<Roba> data=new ArrayBlockingQueue<Roba>(BufferCapacity);
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server pronto");
		while(true) {
			Socket cliSocket=null;
			cliSocket=s.accept();
			System.out.println("Server: accepted "+cliSocket);
			new ServerThread(data, cliSocket);
		} // fine ciclo accettazioni
	}
}
